package uta.cse3310;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Board_Game {
   private int gameId;
   private List<String> validWords;
   private List<String> wordsFound = new ArrayList<>();
   private int playerCount;
   private String currentWord = "";
   private int currentPlayer = 0;
   private int level; // 1 for easy, 2 for medium, 3 for hard
   private Timer timer;
   private long startTime;
   private Color_Picker colorPicker;
   private List<String> playerColors;
   private Board_Create board ;


   public Board_Game(int gameId, List<String> validWords, int playerCount, int level, Board_Create board) {
       this.gameId = gameId;
       this.validWords = validWords;
       this.playerCount = playerCount;
       this.level = level;
       this.timer = new Timer();
       this.board = board;
       colorPicker = new Color_Picker();
        playerColors = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            playerColors.add(colorPicker.assignColor());
        }
   }


   public boolean checkSelection(String letter) {
       cancelTimer();
       currentWord += letter;
       if (validWords.contains(currentWord)) {
           wordsFound.add(currentWord);
           currentWord = "";
           startTimer();
           return true;
       } else if (validWords.stream().anyMatch(word -> word.startsWith(currentWord))) {
           // make sure that the entered current word could be the start of a valid word
           startTimer();
           return true;
       } else {
           currentWord = "";
           nextPlayer();
           return false;
       }
   }


   private void nextPlayer() {
       currentPlayer = (currentPlayer + 1) % playerCount;
       // Display error message and switch to the next player
       startTimer();
   }


   public void displayBoard() {
    // Update the board with the current word
    //board.updateBoard(currentWord);
    
    // Display the board
    //board.displayBoard();
    
    // Display the remaining time
    long timeRemaining = displayTime();
    System.out.println("Time remaining: " + timeRemaining + " seconds");
}



       public boolean leaveGame() {
       Scanner scanner = new Scanner(System.in);


       System.out.println("Are you sure you want to leave the game? (yes/no)");
       String response = scanner.nextLine();
       if (response.equalsIgnoreCase("yes")) {
           System.out.println("It was so hard for us to build this game. Please play one more round. Still want to exit? (yes/no)");
           response = scanner.nextLine();
           if (response.equalsIgnoreCase("yes")) {
               // Exit the game and go to the lobby
               System.out.println("Exiting the game...");
               return true;
           }
       }


       // Return to the game
       System.out.println("Returning to the game...");
       return false;
   }
  


   public boolean handleOutstanding() {
       // this method to handle outstanding actions in the game
       return true;
   }


   public long displayTime() {
       long currentTime = System.currentTimeMillis();
       long timeElapsed = currentTime - startTime;
       long timeRemaining = getTimeLimit() * 1000 - timeElapsed;
       return timeRemaining / 1000;
       // Return time remaining in seconds
       //  this method to display the time remaining for the current player
      
   }
private long getTimeLimit() {
   switch (level) {
       case 1: return 30;
       case 2: return 15;
       case 3: return 8;
       default: return 0;
   }
}


   private void startTimer() {
       timer = new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               System.out.println("Time out!");
               nextPlayer();
           }
       }, getTimeLimit() * 1000); // convert time from millisecond to seconds
   }


   private void cancelTimer() {
       if (timer != null) {
           timer.cancel();
       }
   }


}











