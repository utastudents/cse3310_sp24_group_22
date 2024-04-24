package uta.cse3310;


import java.util.*;


public class Leaderboard {
   private Map<String, Player_Data> players = new HashMap<>();


   public boolean addPlayer(Player_Data player) {
       if (players.containsKey(handle)) {
           return false; // player already exists
       }


       players.put(player.getHandle(), player);
       return true;
   }


   public Player_Data getPlayer(String handle) {
       return players.get(handle);
   }


   public int getPlayerWins(String handle) {
       Player_Data playerData = players.get(handle);
       return playerData != null ? playerData.getWins() : 0;
   }


   public void displayLeaderboard() {
       List<Player_Data> playerDataList = new ArrayList<>(players.values());
       playerDataList.sort(Comparator.comparingInt(Player_Data::getScore).reversed());


       for (Player_Data playerData : playerDataList) {
           int totalGames = playerData.getWins() + playerData.getLosses() + playerData.getDraws();
           System.out.println("Handle: " + playerData.getHandle() + ", Score: " + playerData.getScore() +
               ", Wins: " + playerData.getWins() + ", Losses: " + playerData.getLosses() +
               ", Draws: " + playerData.getDraws() + ", Total games: " + totalGames);
       }
   }
}

