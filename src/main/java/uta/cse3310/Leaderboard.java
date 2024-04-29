package uta.cse3310;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.*;

public class Leaderboard {
    public TreeMap<String, Integer> LB;
    public Leaderboard() {
        // Initialize the TreeMap with a custom comparator to sort by score in descending order
        LB = new TreeMap<>();
    List<Map.Entry<String, Integer>> list = new ArrayList<>(treeMap.entrySet());  
        // Sort the list by values using a custom comparator  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {  
                return o1.getValue().compareTo(o2.getValue());  
            }  
        });  
        // Create a new TreeMap using the sorted list  
        TreeMap<String, Integer> sortedScore = new TreeMap<>();  

    }

    // Method to add a score to the leaderboard
    public void add(String handle, int score) {
        LB.put(handle, score);
        for (Map.Entry<String, Integer> entry : list) {  
            sortedScore.put(entry.getKey(), entry.getValue());  
        }  
    }
    
    public void update(String handle, int score)
    {
    	int new_score = score + LB.get(handle);
    	LB.put(handle, new_score);
        for (Map.Entry<String, Integer> entry : list) {  
            sortedScore.put(entry.getKey(), entry.getValue());  
        }  
    }
    
    public void remove(String handle)
    {
    	if (handle!= null)
    	{
    		LB.remove(handle);
    	}
    }
}

