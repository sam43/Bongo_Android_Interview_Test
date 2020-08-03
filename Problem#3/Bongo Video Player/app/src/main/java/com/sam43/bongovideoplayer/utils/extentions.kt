package com.sam43.bongovideoplayer.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

private const val AppTAG = "APP_DEBUG"
private const val AppERROR = "APP_ERROR"
private const val AppINFO = "APP_INFO"
private const val BIG_BUNNY = "bigbunny"
const val JUMP_SIZE = 10000
const val FIRST_FRAME = 1
const val SEEK_DELAY = 500L

// Save instance constants
const val PLAYBACK_TIME = "playback_time"
const val WAS_PAUSED = "video_was_paused"
const val SEEK_PROGRESS = "seek_progress"

const val PLAY_TAG = "Play"
const val PAUSE_TAG = "Pause"
const val PLAY = "Playing"
const val PAUSE = "Paused"
const val REWIND = "Rewind"
const val FAST_FORWARD = "Forward"
const val SEEK = "Seek"
const val PLAY_ICON = android.R.drawable.ic_media_play
const val PAUSE_ICON = android.R.drawable.ic_media_pause

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun debug(msg: String) = Log.d(AppTAG, msg)

fun error(msg: String) = Log.e(AppERROR, msg)

fun info(msg: String) = Log.i(AppINFO, msg)

fun ImageButton.changeButtonIcon(imgResource: Int) {
    this.setImageResource(imgResource)
}

fun Context.getMedia(): Uri? {
    return Uri.parse(
        "android.resource://" + packageName +
                "/raw/" + BIG_BUNNY
    )
}

fun milliSecondsToTimer(millis: Long): String? {
    var milliseconds = millis
    if (milliseconds % 1000 != 0L) {
        milliseconds += (1000 - milliseconds % 1000)
    }
    var finalTimerString = ""
    val secondsString: String
    val minutesString: String

    // Convert total duration into time
    val hours = (milliseconds / (1000 * 60 * 60)).toInt()
    val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
    val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
    // Add hours if there
    if (hours > 0) {
        finalTimerString = "$hours:"
    }

    // When hours > 0, prepending 0 to minutes if it is one digit
    minutesString = if (minutes < 10) {
        "0$minutes"
    } else {
        "" + minutes
    }

    // Prepending 0 to seconds if it is one digit
    secondsString = if (seconds < 10) {
        "0$seconds"
    } else {
        "" + seconds
    }
    finalTimerString = "$finalTimerString$minutesString:$secondsString"

    // return timer string
    return finalTimerString
}