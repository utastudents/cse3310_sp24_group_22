package uta.cse3310;
import java.util.Comparator;
import java.util.TreeMap;

public class Leaderboard {
    public TreeMap<String, Integer> LB;

    public Leaderboard() {
        // Initialize the TreeMap with a custom comparator to sort by score in descending order
        LB = new TreeMap<>();
    }

    // Method to add a score to the leaderboard
    public void add(String handle, int score) {
        LB.put(handle, score);
    }
    
    public void remove(String handle)
    {
    	if (handle!= null)
    	{
    		LB.remove(handle);
    	}
    }
}

