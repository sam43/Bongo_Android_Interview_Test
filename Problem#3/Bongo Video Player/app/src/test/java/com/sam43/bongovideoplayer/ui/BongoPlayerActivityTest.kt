package com.sam43.bongovideoplayer.ui

import com.sam43.bongovideoplayer.callbacks.Action
import com.sam43.bongovideoplayer.callbacks.Player
import com.sam43.bongovideoplayer.delegate.PlayerDelegate
import com.sam43.bongovideoplayer.utils.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class BongoPlayerActivityTest : Action {

    var actionState: String = ""

    @Mock
    lateinit var activity: BongoPlayerActivity

    @Mock
    lateinit var player: Player

    @Before
    fun setup() {
        activity = Mockito.mock(BongoPlayerActivity::class.java)
        player = PlayerDelegate(this)
    }

    @Test
    fun testPlayVideo() {
        player.actionPlay()
        assertEquals(true, actionState == PLAY)
        assertNotEquals(true, actionState == PAUSE)
        assertNotEquals(true, actionState == FAST_FORWARD)
        assertNotEquals(true, actionState == REWIND)
    }

    @Test
    fun testPauseActionOnVideo() {
        player.actionPause()
        assertNotEquals(true, actionState == PLAY)
        assertEquals(true, actionState == PAUSE)
        assertNotEquals(true, actionState == FAST_FORWARD)
        assertNotEquals(true, actionState == REWIND)
    }

    @Test
    fun testRewindActionOnVideo() {
        player.actionRewind()
        assertNotEquals(true, actionState == PLAY)
        assertNotEquals(true, actionState == PAUSE)
        assertNotEquals(true, actionState == FAST_FORWARD)
        assertEquals(true, actionState == REWIND)
    }

    @Test
    fun testFastForwardActionOnVideo() {
        player.actionFastForward()
        assertNotEquals(true, actionState == PLAY)
        assertNotEquals(true, actionState == PAUSE)
        assertEquals(true, actionState == FAST_FORWARD)
        assertNotEquals(true, actionState == REWIND)
    }

    @Test
    fun testSeekToSpecificPositionOfVideo() {
        player.actionSeek()
        assertNotEquals(true, actionState == PLAY)
        assertNotEquals(true, actionState == PAUSE)
        assertEquals(true, actionState == SEEK)
        assertNotEquals(true, actionState == FAST_FORWARD)
        assertNotEquals(true, actionState == REWIND)
    }


    @Test
    fun testSingleDigitSecondsWithoutRemainderSuccessfulCase() {
        assertEquals("00:09", milliSecondsToTimer(9000))
    }

    @Test
    fun testSingleDigitSecondsWithoutRemainderUnsuccessfulCase() {
        assertNotEquals("00:9", milliSecondsToTimer(9000))
    }


    @Test
    fun testSingleDigitSecondsWithRemainderSuccessfulCase() {
        assertEquals("00:10", milliSecondsToTimer(9500))
    }

    @Test
    fun testSingleDigitSecondsWithRemainderUnsuccessfulCase() {
        assertNotEquals("00:09", milliSecondsToTimer(9500))
    }

    @Test
    fun testSingleDigitMinutesWithoutRemainderSuccessfulCase() {
        assertEquals("01:55", milliSecondsToTimer(115000))
    }

    @Test
    fun testSingleDigitMinutesWithoutRemainderUnsuccessfulCase() {
        assertNotEquals("01:16", milliSecondsToTimer(115000))
    }

    @Test
    fun testSingleDigitMinutesWithRemainderSuccessfulCase() {
        assertEquals("01:56", milliSecondsToTimer(115500))
    }

    @Test
    fun testSingleDigitMinutesWithRemainderUnsuccessfulCase() {
        assertNotEquals("01:15", milliSecondsToTimer(115500))
    }

    override fun updateAction(state: String) {
        actionState = state
    }
}