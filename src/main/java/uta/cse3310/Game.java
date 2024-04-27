package uta.cse3310;
import java.util.ArrayList;
import java.util.Random;

public class Game 
{
	public int busy;
	public int GameId;
	public ArrayList<Integer> ID;
	public char[][] grid = new char[20][20];
	public char[] word;
	public ArrayList<String> identified_words;

    Game(int busy, int GameId) 
    {
    	this.busy = busy;
    	this.GameId = GameId;
		ID = new ArrayList<>();
		identified_words = new ArrayList<>();
		grid = createGrid();
    }

	char[][] createGrid() 
	{
        char[][] grid = new char[20][20];
        Random rand = new Random();

        for (int i = 0; i < 20; i++) 
        {
            for (int j = 0; j < 20; j++) 
            {
                grid[i][j] = (char) (rand.nextInt(26) + 'A');
            }
        }

        return grid;
    }
    
    boolean isValidWord(ArrayList<Coordinate> indexLetters)
    {
    	return true;
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
            // Horizontal orientation
            for (int i = Math.min(indexLetters.get(0).col, indexLetters.get(1).col); i <= Math.max(indexLetters.get(0).col, indexLetters.get(1).col); i++) 
            {
                selectedLetters.add(new Coordinate(indexLetters.get(0).row, i));
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
            for (int row = rowStart, col = colStart; row <= rowEnd && col <= colEnd; row++, col++) 
            {
                selectedLetters.add(new Coordinate(row, col));
            }
        }
        else
        {
        	
        }

        return selectedLetters;
    }
}
    

