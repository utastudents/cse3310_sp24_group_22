package uta.cse3310;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;

public class Game_Timer {
    private Timer timer;
    private int secondsRemaining;
    private boolean isGameEnded;
    private int playerScore;

    public Game_Timer(int seconds, int playerScore) {
        this.secondsRemaining = seconds;
        this.timer = new Timer();
        this.isGameEnded = false;
        this.playerScore = playerScore;
    }



    public void stop() {
        timer.cancel();
        System.out.println("Timer stopped.");
    }

    // Method to prompt players for end game actions
    private void promptPlayerForEndGame(List<Player_Data> players) {
        // Add code here to prompt players for end game actions
        System.out.println("Prompting players for end game actions...");

        // Print the list of players and their scores
        for (Player_Data player : players) {
            System.out.println("Player: " + player.getHandle() + ", Score: " + player.getScore());
        }
    }

    public boolean isGameEnded() {
        return isGameEnded;
    }

    // Method to declare the winner with the highest score
    private void declareWinner(List<Player_Data> players) {
        // Add code here to declare the winner with the highest score
        int highestScore = 0;
        String winnerName = "";

        // Loop through all players and find the player with the highest score
        for (Player_Data player : players) {
            int score = player.getScore();
            if (score > highestScore) {
                highestScore = score;
                winnerName = player.getHandle();
            }
        }

        // Print the winner with the highest score
        System.out.println("The winner is " + winnerName + " with a score of " + highestScore);
    }
    /*public void start() {

        timer.scheduleAtFixedRate(new TimerTask())
        
        @Override
        public void run() {
                if (secondsRemaining > 0) {
                    System.out.println("Time remaining: " + secondsRemaining + " seconds");
                    secondsRemaining--;
                } else {
                    System.out.println("Time's up!");
                    timer.cancel();
                    isGameEnded = true;
                    declareWinner();
                }
            }
    }*/

    /*public static void main(String[] args) {
        Game_Timer timer = new Game_Timer(60, 10); // 60 seconds, player score: 10
        timer.start();
    }*/
}