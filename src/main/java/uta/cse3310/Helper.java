package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public String Handle;
    public int Game_Id;

    public Helper(String handle, int game_id) {
        Handle = handle;
        Game_Id = game_id;
    }

    public String random_word() {
        return "test";
    }

    public boolean prompt_player(List<Player> players) {
    // Check if the players have found all the words
    boolean allWordsFound = true;
    for (Player player : players) {
        if (!player.hasFoundAllWords()) {
            allWordsFound = false;
            break;
        }
    }

    // If not, get the list of players and their scores
    List<Player> playerList = new ArrayList<>();
    int highestScore = 0;
    String winnerName = "";

    if (!allWordsFound) {
        // Prompt players for end game actions
        promptPlayerForEndGame(players);

        // Find the player with the highest score
        for (Player player : players) {
            int score = player.getScore();
            if (score > highestScore) {
                highestScore = score;
                winnerName = player.getName();
            }
            playerList.add(player);
        }
    }

    // Return true if the game has ended, false otherwise
    return allWordsFound;
}

private void promptPlayerForEndGame(List<Player> players) {
    // Add code here to prompt players for end game actions
    System.out.println("Prompting players for end game actions...");

    // Print the list of players and their scores
    for (Player player : players) {
        System.out.println("Player: " + player.getName() + ", Score: " + player.getScore());
    }
 }
}