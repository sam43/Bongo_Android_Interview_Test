package com.sam43.bongovideoplayer.ui

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sam43.bongovideoplayer.R
import com.sam43.bongovideoplayer.callbacks.Action
import com.sam43.bongovideoplayer.callbacks.Player
import com.sam43.bongovideoplayer.delegate.PlayerDelegate
import com.sam43.bongovideoplayer.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
open class BongoPlayerActivity : AppCompatActivity(), View.OnClickListener, Action {
    private var currentSeekPosition = 0
    private var player: Player? = null
    private val actionHandler = Handler()
    private var progressAfterSeek = 0
    private var hasPaused = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialization()
        if (savedInstanceState == null) {
            initializePlayer(0, 0)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        debug("+++onSaveInstanceState -> putting current state value: $currentSeekPosition")
        outState.putInt(PLAYBACK_TIME, currentSeekPosition)
        outState.putInt(SEEK_PROGRESS, progressAfterSeek)
        outState.putBoolean(WAS_PAUSED, hasPaused)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        debug("+++onRestoreInstanceState -> playbackTIme: ${savedInstanceState.getInt(PLAYBACK_TIME)}")
        initializePlayer(
            savedInstanceState.getInt(PLAYBACK_TIME),
            savedInstanceState.getInt(SEEK_PROGRESS)
        )
        if (savedInstanceState.getBoolean(WAS_PAUSED)) {
            pauseVideo()
        }
    }

    override fun updateAction(state: String) {
        when (state) {
            PLAY -> {
                hasPaused = false
                actionHandler.postDelayed(mediaSeekTime, SEEK_DELAY)
                videoView.start()
            }
            PAUSE -> {
                hasPaused = true
                videoView.pause()
            }
            REWIND -> if (videoView.currentPosition > JUMP_SIZE) {
                videoView.seekTo(videoView.currentPosition - JUMP_SIZE)
            } else {
                videoView.seekTo(1)
            }
            FAST_FORWARD -> {
                if (videoView.currentPosition.plus(JUMP_SIZE) >= videoView.duration) {
                    //TODO:: seek to end
                    videoView?.duration?.let {
                        videoView.seekTo(it)
                    }
                } else {
                    videoView?.duration?.let {
                        videoView.seekTo(
                            min(
                                videoView.currentPosition + JUMP_SIZE,
                                it
                            )
                        )
                    }
                }
            }
            SEEK -> videoView.seekTo(progressAfterSeek)
            else -> {
                videoView.seekTo(1)
            }
        }
    }

    override fun onClick(v: View) {
        if (null == player) {
            return
        }
        when (v.id) {
            R.id.btnRewind -> player?.actionRewind()
            R.id.btnFastForward -> player?.actionFastForward()
            R.id.btnPlay -> if (tag == PLAY_TAG) {
                playVideo()
            } else {
                pauseVideo()
            }
        }
    }

    private fun playVideo() {
        tag = PAUSE_TAG
        player?.actionPlay()
        btnPlay.changeButtonIcon(PAUSE_ICON)
    }

    private fun pauseVideo() {
        tag = PLAY_TAG
        player?.actionPause()
        btnPlay.changeButtonIcon(PLAY_ICON)
    }

    private fun initialization() {
        setupViewActions()
        tag = PAUSE_TAG
        player = PlayerDelegate(this)
        btnPlay.changeButtonIcon(PAUSE_ICON)
    }

    private fun setupViewActions() {
        btnPlay.setOnClickListener(this)
        btnFastForward.setOnClickListener(this)
        btnRewind.setOnClickListener(this)
        seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (!videoView.isPlaying) {
                        actionHandler.postDelayed(mediaSeekTime, SEEK_DELAY)
                    }
                    progressAfterSeek = progress
                    player?.actionSeek()
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private var tag: String?
        get() = btnPlay.tag as String
        set(tag) {
            btnPlay.tag = tag
        }

    override fun onStart() {
        super.onStart()
        initializePlayer(currentSeekPosition, progressAfterSeek)
    }

    private fun initializePlayer(currentSeekPosition: Int, progress: Int) {
        videoView.setVideoURI(getMedia())
        if (currentSeekPosition > 0) {
            videoView.seekTo(currentSeekPosition)
        } else {
            videoView.seekTo(FIRST_FRAME)
        }
        videoView.setOnPreparedListener {
            seekbar.progress = progress
            seekbar.max = videoView.duration
            remaining_time_tv.text = milliSecondsToTimer(
                videoView.duration.toLong()
            )
        }
        videoView.setOnErrorListener { _, _, _ -> false }
        videoView.setOnInfoListener { _, _, _ -> false }
        videoView.setOnCompletionListener {
            toast("Playback has completed.")
            afterFinishingTaskOfPlayer()
        }
        playVideo()
    }

    private fun afterFinishingTaskOfPlayer() {
        btnPlay.changeButtonIcon(PLAY_ICON)
        videoView.seekTo(1)
        seekbar.progress = 0
        updateSeekBarProgressText(true)
        tag = PLAY_TAG
        actionHandler.removeCallbacks(mediaSeekTime)
    }

    private val mediaSeekTime: Runnable = object : Runnable {
        override fun run() {
            val currentPosition = videoView.currentPosition.toLong()
            updateSeekBarProgressText(false)
            seekbar.progress = currentPosition.toInt()
            actionHandler.postDelayed(this, SEEK_DELAY)
        }
    }

    private fun updateSeekBarProgressText(isVideoFinished: Boolean) {
        if (!isVideoFinished) {
            val progressTime = videoView.currentPosition.toLong()
            val remainingTime = videoView.duration - progressTime
            progress_time_tv.text = milliSecondsToTimer(progressTime)
            remaining_time_tv.text = milliSecondsToTimer(remainingTime)
        } else {
            progress_time_tv.text = resources.getString(R.string.initial_progress_time)
            remaining_time_tv.text = milliSecondsToTimer(
                videoView.duration.toLong()
            )
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun releasePlayer() {
        videoView?.stopPlayback()
        afterFinishingTaskOfPlayer()
    }

    override fun onPause() {
        super.onPause()
        debug("+++onPause -> checking current state value: ${videoView.currentPosition} && progress: $progressAfterSeek")
        currentSeekPosition = videoView.currentPosition
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            player?.actionPause()
            btnPlay.changeButtonIcon(PLAY_ICON)
        }
    }

}
