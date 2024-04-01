package uta.cse3310;
import java.util.ArrayList;
public class Lobby {
    public ArrayList<String> two_player_game = new ArrayList<String>();
    public ArrayList<String> three_player_game = new ArrayList<String>();
    public ArrayList<String> four_player_game = new ArrayList<String>();

    public void display_leaderboard(){

    }
    public void username(){

    }
    public boolean ready_up(){
        return true;
    }
    public boolean leave(){
        return true;

    }
    public boolean display_chat(){
        return true;

    }
    public int select_game_type(){
        return 1;

    }
    public boolean player_2_start(){
        return true;

    }
    public boolean player_3_start(){
        return true;
        
    }
    public boolean player_4_start(){
        return true;
        
    }
}
