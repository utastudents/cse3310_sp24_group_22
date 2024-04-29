package uta.cse3310;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.*;

public class Leaderboard {
    public TreeMap<String, Integer> LB;

    public Leaderboard() {
        // Initialize the TreeMap with a custom comparator to sort by score in descending order
        LB = new TreeMap<>();
        Comparator<K> compareScore =new Comparator<K> (){
            public int compare(K k1, K k2) 
                  { 
                      int comp = map.get(k1).compareTo( 
                          map.get(k2)); 
                      if (comp == 0) 
                          return 1; 
                      else
                          return comp; 
                  } 
        };
        Map<K, V> sortedScore = new TreeMap<K, V>(compareScore); 

    }

    // Method to add a score to the leaderboard
    public void add(String handle, int score) {
        LB.put(handle, score);
        sortedScore.putAll(map);
    }
    
    public void update(String handle, int score)
    {
    	int new_score = score + LB.get(handle);
    	LB.put(handle, new_score);
        sortedScore.putAll(map);
    }
    
    public void remove(String handle)
    {
    	if (handle!= null)
    	{
    		LB.remove(handle);
    	}
    }
}

