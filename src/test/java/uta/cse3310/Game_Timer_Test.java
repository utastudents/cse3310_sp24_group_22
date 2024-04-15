package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Game_Timer_Test {
    @Test
    public void testGameTimer() {
        Game_Timer timer = new Game_Timer(60, 10);
        timer.start();

        // Wait for the timer to finish
        while (!timer.isGameEnded()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Check if the winner is declared correctly
        assertEquals("The winner is player2 with a score of 10", timer.getWinnerMessage());
    }

    @Test
    public void testStopTimer() {
        Game_Timer timer = new Game_Timer(60, 10);
        timer.start();

        // Stop the timer after 5 seconds
        timer.stop();

        // Check if the timer is stopped
        assertEquals(true, timer.isGameEnded());
    }

    private String getWinnerMessage(Game_Timer timer) {
        String winnerMessage = "";
        for (String player : timer.getPlayers()) {
            if (timer.getPlayerScore(player) > 0) {
                if (winnerMessage.equals("")) {
                    winnerMessage = "The winner is " + player + " with a score of " + timer.getPlayerScore(player);
                } else {
                    winnerMessage += " and " + player + " with a score of " + timer.getPlayerScore(player);
                }
            }
        }
        return winnerMessage;
    }
}