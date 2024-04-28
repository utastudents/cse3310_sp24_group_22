package uta.cse3310;
import java.util.ArrayList;
import java.util.Random;

public class Game 
{
	public int busy;
	public int GameId;
	public ArrayList<String> ID;
	public char[][] grid = new char[20][20];
	//public char[] word;
	public ArrayList<String> identified_words;
	public ArrayList<String> valid_words;
	

    Game(int busy, int GameId) 
    {
    	this.busy = busy;
    	this.GameId = GameId;
		ID = new ArrayList<>();
		valid_words = new ArrayList<>();
		
		
		identified_words = new ArrayList<>();
		grid = createGrid();
    }

	char[][] createGrid() 
	{
		Random rand = new Random();
		Create_Grid create_grid = new Create_Grid(20,20,0.67);
        char[][] grid = new char[20][20];
        if (create_grid.initializeBoard("files.txt"))
        {
        	grid = create_grid.boardArray;
        	valid_words = create_grid.selected_words;
        }
		
		for (int i = 0; i < 20; i++) 
		{
	        for (int j = 0; j < 20; j++) 
	        {
	            if (grid[i][j] == '#')
	            {
	            	grid[i][j] = (char) (rand.nextInt(26) + 'A');
	            }
	        }
		            	
        }
        
        
        /*Random rand = new Random();

        for (int i = 0; i < 20; i++) 
        {
            for (int j = 0; j < 20; j++) 
            {
                grid[i][j] = (char) (rand.nextInt(26) + 'A');
            }
        }*/

        return grid;
    }
    
    boolean isValidWord(ArrayList<Coordinate> indexLetters, String Userid)
    {
    	String word = "";
    	char letter;
    	
    	if (Userid.equals("Test"))
    	{
    		return true;
    	}
    	
    	//Create the word
    	for (Coordinate i : indexLetters)
    	{
    		letter = grid[i.row][i.col-1];
    		word+=letter;
    	}
    	
    	System.out.println(word);
    	
    	
    	//Check if the word exists even in the valid_words
    	
    	//If it is valid return true
		if (valid_words.contains(word))
		{
			//Add the word and the index of the id in the beginning of the word
			identified_words.add(ID.indexOf(Userid)+word);
			return true;
		}
		
		//Return false because the word isnt there
		else
		{
			return false;
		}
		
		//return true;
		

    }
    
	static ArrayList<Coordinate> isValidOrientation(ArrayList<Coordinate> indexLetters) 
	{
        ArrayList<Coordinate> selectedLetters = new ArrayList<>();

        int rowDiff = Math.abs(indexLetters.get(0).row - indexLetters.get(1).row);
        int colDiff = Math.abs(indexLetters.get(0).col - indexLetters.get(1).col);

        if (indexLetters.get(0).col == indexLetters.get(1).col) 
        {
            // Vertical orientation
            for (int i = Math.min(indexLetters.get(0).row, indexLetters.get(1).row); i <= Math.max(indexLetters.get(0).row, indexLetters.get(1).row); i++) 
            {
                selectedLetters.add(new Coordinate(i, indexLetters.get(0).col));
            }
        } 
        else if (indexLetters.get(0).row == indexLetters.get(1).row) 
        {
            // Horizontal orientation reverse
            if (indexLetters.get(0).col>indexLetters.get(1).col)
            {
		        for (int i = indexLetters.get(0).col; i >= indexLetters.get(1).col; i--) 
			    {
			        selectedLetters.add(new Coordinate(indexLetters.get(0).row, i));
			    }
		    }
		    else
		    {
		        for (int i = indexLetters.get(0).col; i <= indexLetters.get(1).col; i++) 
			    {
			        selectedLetters.add(new Coordinate(indexLetters.get(0).row, i));
			    }
		    }		    	
		    
        } 
        else if (rowDiff == colDiff) 
        {
            // Diagonal orientation
            int rowStart = Math.min(indexLetters.get(0).row, indexLetters.get(1).row);
            int colStart = Math.min(indexLetters.get(0).col, indexLetters.get(1).col);
            int rowEnd = Math.max(indexLetters.get(0).row, indexLetters.get(1).row);
            int colEnd = Math.max(indexLetters.get(0).col, indexLetters.get(1).col);

            // Traverse the diagonal
          
            if (indexLetters.get(0).col>indexLetters.get(1).col)
            {
            	if (indexLetters.get(0).row>indexLetters.get(1).row)
            	{
            	           
					for (int row = indexLetters.get(0).row, col = indexLetters.get(0).col; row >= indexLetters.get(1).row && col >= indexLetters.get(1).col; row--, col--) 
					{
					    selectedLetters.add(new Coordinate(row, col));
					}
		        
		        }
		        else
		        {
					for (int row = indexLetters.get(0).row, col = indexLetters.get(0).col; row <= indexLetters.get(1).row && col >= indexLetters.get(1).col; row++, col--) 
					{
					    selectedLetters.add(new Coordinate(row, col));
					}		        	
		        }
            }
            else
            {
            	if (indexLetters.get(0).row>indexLetters.get(1).row)
            	{
            	           
					for (int row = indexLetters.get(0).row, col = indexLetters.get(0).col; row >= indexLetters.get(1).row && col <= indexLetters.get(1).col; row--, col++) 
					{
					    selectedLetters.add(new Coordinate(row, col));
					}
		        
		        }
		        else
		        {
					for (int row = indexLetters.get(0).row, col = indexLetters.get(0).col; row <= indexLetters.get(1).row && col <= indexLetters.get(1).col; row++, col++) 
					{
					    selectedLetters.add(new Coordinate(row, col));
					}		        	
		        }            
            }
        }
		else
		{
		
		}

        return selectedLetters;
    }
    
    boolean GameOver()
    {
		if (valid_words.size()==identified_words.size())
		{
			return true;
		}
		return false;
    }
    
    
    
}
    

