# Solution to Q#3: Bongo Video Player

Project Link: https://github.com/sam43/Bongo_Android_Interview_Test/tree/master/Problem%233/Bongo%20Video%20Player

Unit test Link: https://github.com/sam43/Bongo_Android_Interview_Test/tree/master/Problem%233/Bongo%20Video%20Player/app/src/test/java/com/sam43/bongovideoplayer/ui


## Psudo Code for Bongo Video Player Android App 


The psudo code defines the steps and algorithm behind the actual code, psudo code for the 'play', 'rewind' and 'fast forward' functionalities are written below:

1. First of all we will be taking a VideoView object as 'videoView' which is defined in our layout (xml) file.
2. We will be checking if any screen rotation occurs or not by checking the Bundle values form 'onRestoreInstanceState()' lifecycle method
3. If the bundle is null then we will init our player from start 'initializelayer(0,0)'
4. Here we are using the 'seekbar' as well to track down the progress time of the media or video playing


### InitializePlayer() ->

      PROCEDURE initializePlayer(currentPosition: Integer, progress: Integer):
        IF currentPosition > 0
      	// checking if video has already played but hasn't finished yet
      	// Can be happening with orientations changes
            videoView.seekTo(currentPosition)
        ELSE
      		videoView.seekTo(FIRST_FRAME) // 1 is the very first frame of the video
        END IF

	    videoView.setOnPreparedListener {
		... 1. here we update the seekbar
		2. update the text for both progress text and remaining time textviews 
	    }
	    videoView.setOnCompletionListener {
		// Call method to ensure what to do after finishing the video playback
	    }
	    play()

	END PROCEDURE


### play() ->

        PROCEDURE play():
                tag = PAUSE_TAG // to update the state if the player is in playing state or pause state
                player?.actionPlay() // basically starts playing the video by calling 'videoView.start()' method
                btnPlay.changeButtonIcon(PAUSE_ICON) // chaging the icon of the single play button observing the player state (play/pause)
        END PROCEDURE 



### pause() ->

        PROCEDURE play():
                tag = PLAY_TAG// to update the state if the player is in playing state or pause state
                player?.actionPause() // basically starts playing the video by calling 'videoView.start()' method
                btnPlay.changeButtonIcon(PLAY_ICON) // chaging the icon of the single play button observing the player state (play/pause)
        END PROCEDURE 


### rewind() ->

JUMP_SIZE is the seek value while rewinding the video

        PROCEDURE rewind():
                IF videoView.currentPosition > JUMP_SIZE
                     videoView.seekTo(videoView.currentPosition - JUMP_SIZE)
          ELSE
               videoView.seekTo(FIRST_FRAME) // 1 is the very first frame of the video 
        END PROCEDURE 


### fast_forward() ->

JUMP_SIZE is the seek value while rewinding the video

          PROCEDURE rewind():
                  IF videoView.currentPosition.plus(JUMP_SIZE) >= videoView.duration 
               // Checking if time after jump is greateer or equal to total duration or not
                 videoView.seekTo(videoView.duration) // seek to end of video
            ELSE
               videoView.seekTo(min of (videoView.currentPosition + JUMP_SIZE and videoView.duration)) 
               // getting the minimum value from the position value and the duration we will be seeking to
          END PROCEDURE 


### seek() -> 

          PROCEDURE seek():
            // We will be getting the position that have been seeked by the user from 'onProgressChanged()' method by using 'seekbar.setsetOnSeekBarChangeListener {}' callback
            in onProgressChanged():
              IF (videoView.isPlaying)
                handler.postDelayed(seektime, 500) // long value for the handler
              // for global purpose 'progressAfterSeek = progress'
              videoView.seekTo(progress)
          END PROCEDURE 



That's all for the psudo code for the video player application for problem#3. 

Now I will be discussing about the design pattern used in this application. I have tried MVVM archiitechtural pattern to do this t first, but I found out that is not 
feasible and may be also a overkill for this assignment in perticular. A basic "Facade Pattern" is enough for this assignment.
So, I used this pattern to develope this app. This is very simple and provides simplified interface that delegates the action which are used as action button for the video player app.
I created two different interfaces which will communicate with each other and a delegate class which will implement the UI communicatot interface and update any action by user. This delegate class is 
the abstract layer for every action. Client will interact with this delegate and the updated state will be implemented into our client end.

Let's try to understand this with the project implemented already.

First of all we create a 'Player' interface which will responsible to get interaction from user directly.

### Player.kt

          interface Player {
              fun actionPlay()
              fun actionPause()
              fun actionFastForward()
              fun actionRewind()
              fun actionSeek()
          }
          
          
Now, let's define another interface which will be implemented into client side. 


### Action.kt

            interface Action {
                fun updateAction()
            }
 Now,  we need a delegate class (also known as Facade) which will delegate the action ccording the user interaction, in that case it will be implementing 'Player' interface
 
 ### PlayerDelegate.kt
 
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
          
After setting up our facade we will be able to use the action and see the out put. In our BongoPlayerActivity class, we will be implementing the 'Action' interface to do 
what should be done according the action placed by the user, which was delegated by 'PlayerDelegate' which implements the 'Player' interface. We can use those when 
clicking upon a button in the design. Here is a sample snippet for that -


        private var player: Player? = null
        ....
        player = PlayerDelegate(this)
        ....
        fun play() {
          player.actionPlay()
          ...
        }
        fun pause() {
          player.actionPause()
          ...
        }
        fun rewind() {
          player.actionRewind()
          ...
        }
        fun fastForward() {
          player.actionFastForward()
          ...
        }
        
        ... and so on.
        
        
Reason behind using this pattern is, Facade is easy to implement and can provide simple way around to solve a complex problem. Like other patterns it also increase the 
code redability and reusability as well.

### Unit Test code for the project: BongoPlayerActivityTest.kt

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

        
This class implements the **Action** class to achieve the state from the user and with that we check if the *actionPlay()*, *actionPause()*, *actionRewind()* and *actionFastforward()* are working perfectly or not. Here is also some other test cases to check if the playback time is synchronous with the seekbar progress or not. That's all from me; thank you.
          







