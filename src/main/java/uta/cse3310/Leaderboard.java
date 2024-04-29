package uta.cse3310;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.*;

public class Leaderboard {
    public TreeMap<String, Integer> LB;
    public ArrayList<Integer> valueList = new ArrayList<Integer>(LB.values());
    public ArrayList<String> keyList = new ArrayList<String>(LB.keyset());

    public Leaderboard() {
        // Initialize the TreeMap with a custom comparator to sort by score in descending order
        LB = new TreeMap<>();
    }

    // Method to add a score to the leaderboard
    public void add(String handle, int score) {
        LB.put(handle, score);
    }
    
    public void update(String handle, int score)
    {
    	int new_score = score + LB.get(handle);
    	LB.put(handle, new_score);
    }
    
    public void remove(String handle)
    {
    	if (handle!= null)
    	{
    		LB.remove(handle);
    	}
    }
}

