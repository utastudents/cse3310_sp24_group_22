package uta.cse3310;
import java.util.ArrayList;
import java.util.TreeMap;

public class Summary {
	
	public TreeMap<String, String> Summary_list;
	
	public Summary()
	{
		Summary_list = new TreeMap<>();
	}
	
    void Create_Summary(ArrayList<String> UId, ArrayList<String> winner)
    {
		if (UId.size()==winner.size())
		{
			for (String k : UId)
			{
				Summary_list.put(k, "tie");
			}			
		}
		else
		{
			for (int i = 0; i < winner.size(); i++)
			{

				for (String m : UId)
				{
					if (winner.get(i).equals(m))
					{
						Summary_list.put(m, "win");
					}
					else
					{
						Summary_list.put(m, "lose");
					}
				}
				
			}
		}
		
		
    }
    
    
    
    void Clear_Summary()
    {
    	Summary_list.clear();
    }

}

