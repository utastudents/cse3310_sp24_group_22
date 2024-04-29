package uta.cse3310;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.*;

public class Leaderboard {
    public TreeMap<String, Integer> LB;
	public SortedSet sortedScore;

	static <K,V> SortedSet<Map.Entry<K,V>> sortingScore(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1; // Special fix to preserve items with equal values
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public Leaderboard() {
        // Initialize the TreeMap with a custom comparator to sort by score in descending order
        LB = new TreeMap<>();
		sortedScore = new SortedSet();
    }
    // Method to add a score to the leaderboard
    public void add(String handle, int score) {
        LB.put(handle, score);
		sortedScore.clear();
        sortedScore = sortingScore(LB);
    }
    
    public void update(String handle, int score)
    {
    	int new_score = score + LB.get(handle);
        LB.put(handle, new_score);
		sortedScore.clear();
        sortedScore = sortingScores(LB);
    }
    
    public void remove(String handle)
    {
    	if (handle!= null)
    	{
            LB.remove(handle);
			sortedScore.clear();
        	sortedScore = sortingScores(LB);
    	}
    }
}

