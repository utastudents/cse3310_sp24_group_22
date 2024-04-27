package uta.cse3310;
import java.util.ArrayList;
// User events are sent from the webpage to the server

public class UserEvent 
{
    public String Handle;
    public int GameType;
    public int Uid;
    public String color;
    public int ready;
    public Game game;
    public ArrayList<Coordinate> letters;

}
