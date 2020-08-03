package com.sam43.bongovideoplayer.delegate

import com.sam43.bongovideoplayer.callbacks.Action
import com.sam43.bongovideoplayer.callbacks.Player
import com.sam43.bongovideoplayer.utils.*

class PlayerDelegate(var action: Action) :
    Player {
    override fun actionPlay() {
        action.updateAction(PLAY)
    }

    override fun actionPause() {
        action.updateAction(PAUSE)
    }

    override fun actionFastForward() {
        action.updateAction(FAST_FORWARD)
    }

    override fun actionRewind() {
        action.updateAction(REWIND)
    }

    override fun actionSeek() {
        action.updateAction(SEEK)
    }
}