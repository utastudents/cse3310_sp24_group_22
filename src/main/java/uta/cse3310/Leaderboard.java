package uta.cse3310;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.*;

public class Leaderboard {
    public TreeMap<String, Integer> LB;
	public List<Integer> scores;
	public List<String> names;
    public Leaderboard() {
        // Initialize the TreeMap with a custom comparator to sort by score in descending order
        LB = new TreeMap<>();
		scores = new ArrayList<>();
		names = new ArrayList<>();
    }
    // Method to add a score to the leaderboard
    public void add(String handle, int score) {
        LB.put(handle, score);
		scores.add(score);
		Collections.sort(scores);
		if(scores.indexOf(score) >= names.size())
		{
			names.add(handle);
		}
		else
		{
			names.add(scores.indexOf(score), handle);
		}
    }
    
    public void update(String handle, int score)
    {
    	int new_score = score + LB.get(handle);
        LB.put(handle, new_score);
		int i = names.indexOf(handle);
		names.remove(i);
		scores.remove(i);
		scores.add(new_score);
		Collections.sort(scores);
		if(scores.indexOf(new_score) >= names.size())
		{
			names.add(handle);
		}
		else
		{
			names.add(scores.indexOf(new_score), handle);
		}
    }
    
    public void remove(String handle)
    {
    	if (handle!= null)
    	{
            LB.remove(handle);
    	}
		int i = names.indexOf(handle);
		names.remove(i);
		scores.remove(i);
    }
}

