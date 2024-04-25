package uta.cse3310;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Game_TimerTest {
    private Game_Timer gameTimer;

    @Before
    public void setUp() {
        // Initialize the game timer with 30 seconds and a player score of 0
        gameTimer = new Game_Timer(30, 0);
    }

    @Test
    public void testStartTimer() {
        // Start the timer
        gameTimer.start();

        // Wait for a few seconds (simulating game play)
        try {
            Thread.sleep(3000); // Sleep for 3 seconds
            gameTimer.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the timer is still running
        assertFalse(gameTimer.isGameEnded());
    }

    @Test
    public void testTimerEnds() {
        // Start the timer
        gameTimer.start();

        // Wait for the timer to finish
        try {
            Thread.sleep(11000); // Sleep for 11 seconds (more than the timer duration)
            gameTimer.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the game has ended
        assertTrue(gameTimer.isGameEnded());
    }
}
