package uta.cse3310;
import java.util.ArrayList;
public class Board_Game {
    public int Game_Id;
    public int Total_valid_words;
    public ArrayList<String> Words_found = new ArrayList<String>();
    public int Player_count;

    public boolean check_selection(){
        return true;
    }
    public int display_board(){
        return 1;
    }
    public int display_words(){
        return 1;
    }
    public boolean leave_game(){
        return true;
    }
    public boolean handle_outstanding(){
        return true;
    }
    public long display_time(){
        return 1;
    }
}
