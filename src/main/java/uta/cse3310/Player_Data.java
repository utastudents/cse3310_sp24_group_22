package uta.cse3310;


import java.util.Random;


public class Player_Data {
   private String handle;
   private int gameId;
   private int score;
   private int wins;
   private int losses;
   private int draws;
   private String Color;


   public Player_Data() {
       this.handle = "";
       this.gameId = 0;
       this.score = 0;
       this.wins = 0;
       this.losses = 0;
       this.draws = 0;
       this.Color = "";
   }


   public void updateStats(boolean win, boolean loss, boolean draw){
       if (win) this.wins++;
       if (loss) this.losses++;
       if (draw) this.draws++;
       updateScore();
   }


   private void updateScore(){
       int totalGames = wins + losses + draws;
       if (totalGames > 0) {
           this.score = (wins * 100) / totalGames;
       }
   }


   public String getHandle() {
       return handle;
   }
    public void setHandle(String Handle){
        this.handle = Handle;
    }

   public int getGameId() {
       return gameId;
   }


   public int getScore() {
       return score;
   }


   public int getWins() {
       return wins;
   }


   public int getLosses() {
       return losses;
   }


   public int getDraws() {
       return draws;
   }
}

