package uta.cse3310;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import uta.cse3310.Coordinate;
import uta.cse3310.Create_Grid;
import uta.cse3310.Leaderboard;

import java.util.Queue;

public class GameClient 
{
	public int busy;
	public int test_grid;
	public ArrayList<String> ID;
	public char[][] grid = new char[20][20];
	//public char[] word;
	public ArrayList<String> identified_words;
	public ArrayList<String> valid_words;
	private Queue<Character> alphabet = new LinkedList<Character>();


    GameClient(int busy, int test_grid) 
    {
    	this.busy = busy;
    	this.test_grid = test_grid;
		ID = new ArrayList<>();
		valid_words = new ArrayList<>();
		
		
		identified_words = new ArrayList<>();

		// Create alphabet and shuffle it so our
		// filler letters aren't super obvious
		List<Character> temporary = new ArrayList<Character>(26);
		for (char c: "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
			// alphabet.offer(c);
			temporary.add(c);
		}
		Collections.shuffle(temporary);
		
		for (char c: temporary) {
			alphabet.offer(c);
		}

		grid = createGrid();
    }

	char[][] createGrid() 
	{
		// Random rand = new Random();
		Create_Grid create_grid = new Create_Grid(test_grid,test_grid,0.67);
        char[][] grid = new char[test_grid][test_grid];
        if (create_grid.initializeBoard("files.txt"))
        {
        	grid = create_grid.boardArray;
        	valid_words = create_grid.selected_words;
        }

        return grid;
    }

	/** Using this for testing filler chars */
	Map<Character, Integer> create_grid(char[][] grid) {
        // Random rand = new Random();
        Map<Character, Integer> used_chars = new HashMap<Character,Integer>();
        char new_char;
        int old_value;
		for (int i = 0; i < test_grid; i++) 
		{
	        for (int j = 0; j < test_grid; j++) 
	        {
	            if (grid[i][j] == '#')
	            {
                    new_char = alphabet.poll();
                    old_value = used_chars.getOrDefault(new_char, 0);
	            	used_chars.put(new_char, old_value + 1);
					alphabet.offer(new_char);
	            }
	        }
        }
		return used_chars;
	}
    
    boolean isValidWord(ArrayList<Coordinate> indexLetters, String Userid, Leaderboard LB)
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
			LB.update(Userid,10);
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
    
	ArrayList<String> GameOver(boolean timer_done, Leaderboard LB) 
	{
		ArrayList<Integer> scores = new ArrayList<>();
		ArrayList<String> names_winners = new ArrayList<>();
		int max_count = 0;
		
		if (valid_words.size() == identified_words.size() || timer_done) {
		    for (int i = 0; i < ID.size(); i++) 
		    {
		        int temp_count = 0; // Reset temp_count for each player
		        for (String word : identified_words) 
		        {
		            if (Character.getNumericValue(word.charAt(0)) == i) 
		            {
		                temp_count++;
		            }
		        }
		        scores.add(temp_count);
		        if (temp_count > max_count) 
		        {
		            max_count = temp_count;
		        }
		    }
		    
		    for (int i = 0; i < scores.size(); i++) 
		    {
		    	//LB.update(ID.get(i),10*scores.get(i));
		        if (scores.get(i) == max_count) 
		        {
		            names_winners.add(ID.get(i));
		            LB.update(ID.get(i),50);
		            
		        }
		    }
		}
		    
		return names_winners;
	}
}