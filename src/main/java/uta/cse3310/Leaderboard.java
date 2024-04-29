package uta.cse3310;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.*;

public class Leaderboard {
    public TreeMap<String, Integer> LB;
    public SortedSet<Map.Entry<String, Integer>> sortedScore;
    public Leaderboard() {
        // Initialize the TreeMap with a custom comparator to sort by score in descending order
        LB = new TreeMap<>();
        SortedSet<Map.Entry<String, Integer>> sortedScore = new TreeSet<>(new Comparator<Map.Entry<String, Integer>>() {  
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {  
                return o1.getValue().compareTo(o2.getValue());  
            }  
        });  
    }

    // Method to add a score to the leaderboard
    public void add(String handle, int score) {
        LB.put(handle, score);
        sortedScore.clear();
        sortedScore.addAll(LB.entrySet());
    }
    
    public void update(String handle, int score)
    {
    	int new_score = score + LB.get(handle);
    	LB.put(handle, new_score);
        sortedScore.clear();
        sortedScore.addAll(LB.entrySet());
    }
    
    public void remove(String handle)
    {
    	if (handle!= null)
    	{
    		LB.remove(handle);
            sortedScore.clear();
            sortedScore.addAll(LB.entrySet());
    	}
    }
}

