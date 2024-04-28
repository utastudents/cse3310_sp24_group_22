package uta.cse3310;
import java.util.ArrayList;
import java.util.TreeMap;

public class Summary {
	
	public TreeMap<Integer, String> Summary_list;
	
	public Summary()
	{
		Summary_list = new TreeMap<>();
	}
	
    TreeMap<Integer, String> Create_Summary(ArrayList<Integer> UId, int winner)
    {
		
		if (winner == -1)
		{
			for (int i : UId)
			{
				Summary_list.put(i, "tie");
			}			
		}
		else
		{
			for (int i : UId)
			{
				if (i == winner)
				{
					Summary_list.put(i, "win");
				}
				else
				{
					Summary_list.put(i, "lose");
				}
			}
		}
		
		return Summary_list;
		
    }
    
    void Clear_Summary()
    {
    	Summary_list.clear();
    }

}

