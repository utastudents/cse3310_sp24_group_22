package uta.cse3310;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.*;

public class Leaderboard {
    public TreeMap<String, Integer> LB;
    public TreeMap<Map.Entry<String, Integer>> sortedScore = new TreeMap<>(new Comparator<Map.Entry<String, Integer>>() {  
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int res = o1.getValue().compareTo(o2.getValue());  
                if (o1.getKey().equals(o2.getKey())) {
                    return res; // Code will now handle equality properly
                } else {
                    return res != 0 ? res : 1; // While still adding all entries
                }
            }  
        }); 
    public Leaderboard() {
        // Initialize the TreeMap with a custom comparator to sort by score in descending order
        LB = new TreeMap<>();
    }

    // Method to add a score to the leaderboard
    public void add(String handle, int score) {
        LB.put(handle, score);
        sortedScore.clear();
        sortedScore.putAll(LB.entrySet());
        for (Map.Entry<String, Integer> entry : sortedScore) {  
            System.out.println(entry.getKey() + ": " + entry.getValue());  
        }  
    }
    
    public void update(String handle, int score)
    {
    	int new_score = score + LB.get(handle);
    	LB.put(handle, new_score);
        sortedScore.clear();
        sortedScore.putAll(LB.entrySet());
        for (Map.Entry<String, Integer> entry : sortedScore) {  
            System.out.println(entry.getKey() + ": " + entry.getValue());  
        }  
    }
    
    public void remove(String handle)
    {
    	if (handle!= null)
    	{
    		LB.remove(handle);
            sortedScore.clear();
            sortedScore.putAll(LB.entrySet());
            for (Map.Entry<String, Integer> entry : sortedScore) {  
                System.out.println(entry.getKey() + ": " + entry.getValue());  
            }
    	}
    }
}

