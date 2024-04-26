package uta.cse3310;

import java.util.Random;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Board assigns each word in Wordlist to an index in the word search.
 * Also has attributes:
 * Orientation - Enum class with all directions a word could be in (public so it can be reused in other classes)
 * Density - total density of the word search
 * Randomness - array with the density of words in each orientation (uses EnumMap for faster accessing and usability)
 * Min_Letters - idk I haven't used it yet
 * Handle - idk
 */
public class Board_Create {
    public float Density;
    public int Min_Letters;
    public String Handle; // ???

    public enum Orientation { VERT_UP, VERT_DOWN, HORIZ, DIAG_UP, DIAG_DOWN };
    
    private int DEFAULT_GRID_SIZE = 25;
    private double MIN_GRID_DENSITY = 0.67;
    private int board_size;  // assuming the board is 50x50 (rows=cols), then board_size = 50;
    private char[][] grid;  // character grid to return in order to play the game
    private int total_slots;
    private Random rand = new Random();
    private WordList words;


    Board_Create(WordList options){
        // fill grid with periods to maybe regex match better
        board_size = DEFAULT_GRID_SIZE;
        total_slots = DEFAULT_GRID_SIZE*DEFAULT_GRID_SIZE;
        grid = new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE];
        Density = 0;
        words = options;

        // fill grid
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++)
                grid[i][j] = '.';
        }

        // initialize
        create_grid();
    }

    // Might not be needed, but just in case we need a word search NOT 50x50...
    Board_Create(WordList options, int size){
        board_size = size;
        grid = new char[size][size];
        Density = 0;
        total_slots = size*size;
        words = options;

        // fill grid
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++)
                grid[i][j] = '.';
        }

        // initialize
        create_grid();
    }

    public static void main(String[] args) {
        WordList options = new WordList();
        Board_Create board = new Board_Create(options, 20);
        board.PrintBoard();
	}

    public int create_grid(){
        // Some musts:
        // Empty slots will be filled with '.' (Allows regex to find words that match params)
        // slots filled/total slots >= 0.67

        /* Algorithm Idea
        1. Fetch words of different lengths from [3..board_size]. (or to max_word_size if we wanna define that)
            ArrayList<String> options = WordList.list_words();
        
        (START LOOP)
        2. Choose an orientation.
            assign_direction();
        3. Stringify a random row/col/diag
        4a. Use WordList.find_word(stringified_row/col/diag) to find a word that can fit inside
            A/N: WordList.find_word() can return:
                a) the matching word, the index to insert it in
                b) a copy of the stringified_row/col/diag with the word inserted <-- Probably this tbh
                c) nothing and do the insertion itself
        4b. Insert word if there is a substring of all dots the same length as the word
        5. Meets density requirements?
            no) keep looping
            yes) go to next step
        (END LOOP)
        6. Find all empty spots & fill them in
        */

        int rand_row;
        int rand_col;
        int rand_num;
        String rand_word;
        Orientation rand_orientation;

        List<Orientation> char_sum = new ArrayList<>(5);
        char_sum.add(Orientation.VERT_DOWN);
        char_sum.add(Orientation.VERT_UP);
        char_sum.add(Orientation.HORIZ);
        char_sum.add(Orientation.DIAG_DOWN);
        char_sum.add(Orientation.DIAG_UP);

        // chars filled < required chars filled 
        // orientations needed > 0
        while((Density < (0.67*total_slots)) && !char_sum.isEmpty()) {
            // fetch random word
            rand_word = words.get_word();

            // fetch random index between 0 and 50.
            rand_num = rand.nextInt(board_size);

            // fetch random orientation & the string for it
            rand_orientation = char_sum.get(rand.nextInt(char_sum.size()));

            int start_ix = assign_direction(rand_orientation, rand_word, rand_num);

            // traverse grid (if diagonal, the function will generate another number)
            traverse_grid(rand_orientation, rand_num, start_ix, rand_word);

        }
        return 1;
    }

    public void PrintBoard() {
        System.out.println(Arrays.deepToString(grid).replace("], ","]\n"));
    }

    /** Assign each direction in order
        Example: previous word was given the HORIZ
        the next word should NOT be HORIZ, it CAN be any of the other orientations

        ... or maybe assign based on density
    */ 
    public int assign_direction(Orientation direction, String word, int line){
        // changed from void to String
        // fetch random int from 0 to 50
        int num = 0;
        String s = "";
        // Get whole line
        switch (direction) {
            case VERT_UP:
                // flip word and go to next case
            case VERT_DOWN:
                for (int row = 0; row < grid.length; row++) {
                    s = s + grid[row][line];
                    if (s.length() == word.length()) {
                        if (word.matches(s)) {
                            break; 
                        } else {  // move substring window
                            s = s.substring(1, s.length());
                        }  
                    }
                }
                    
                break;
            case HORIZ:
                for (int col = 0; col < grid[line].length; col++) {
                    s = s + grid[line][col];
                    if (s.length() == word.length()) {
                        if (word.matches(s)) {
                            break; 
                        } else {  // move substring window
                            s = s.substring(1, s.length());
                        }  
                    }
                }
                break;
            case DIAG_UP:
                // flip word and go to next case
            case DIAG_DOWN:
                if (line + word.length() > board_size) {
                    line = board_size - word.length();
                }
                for(int i = line, j = line; (i < board_size) || (j < board_size); i++, j++) {
                    s = s + grid[i][j];
                    if (s.length() == word.length()) {
                        if (word.matches(s)) {
                            break; 
                        } else {  // move substring window
                            s = s.substring(1, s.length());
                        }  
                    }
                }
                    
                break;
        }
        
        return num;
    }

    /**
     * Places each word in the grid
    */
    private void traverse_grid(Orientation direction, int num, int start, String word) {
        int ch = 0;
        switch (direction) {
            /**
             * Only define one code for vertical since we can reverse our
             * word string to go from VERT_UP -> VERT_DOWN 
             */
            case VERT_UP:
                //Collections.reverse(rand_arr);
            case VERT_DOWN:
                while (ch < word.length()) {
                    grid[start][num] = word.charAt(ch);
                    start++;
                    ch++;
                }
                break;
            case HORIZ:
                for (int col = start, j = 0; j < word.length(); col++, j++) {
                    grid[num][col] = word.charAt(j);
                }
                break;
            case DIAG_UP:
                /**
                 * start row < end row
                 * Examples:
                 * S . . . S . . D
                 * . D . D . . A .
                 * . . R . . S . .
                 * . O . O . . . .
                 * W . . . W . . .
                 */
                // left-to-right: col++
                // right-to-left: col--
                // assume your finding the diagonals for (1,3): 
                for (int row = num, col = start, i = 0; i < word.length(); row++, col++, i++ ) {
                    grid[row][col] = word.charAt(i);
                }
                break;
            case DIAG_DOWN:
            /**
             * start row > end row
             * . . T . . . . .
             * . E . . . S . .
             * A . . H . . A .
             * . . O . . . . D
             * . W . . . . . .
             */
            for (int row = num, col = start, i = 0; i < word.length(); row++, col++, i++ ) {
                grid[row][col] = word.charAt(i);
            }
            break;
        }
    }
    public boolean validate_word(String word, String s, char ch){
        if (s.length() == word.length()) {
            if (word.matches(s)) {
                return true; 
            } else {  // move substring window
                s = s.substring(1, s.length());
            }  
        }
        return false;
    }
    public void randomize_filter(){

    }
}