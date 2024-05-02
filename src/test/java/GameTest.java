package uta.cse3310;

import org.junit.Test;

import uta.cse3310.Coordinate;
import uta.cse3310.Create_Grid;
import uta.cse3310.Game;
import uta.cse3310.GameClient;
import uta.cse3310.Leaderboard;

import java.util.HashMap;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.Arrays;

public class GameTest {
    private int MAX_CREATE_TIME = 1_000_000_000; // 1 second in nanoseconds

    @Test
    public void testConstructor() {
    	
        Game game = new Game(1, 20);
        game.test_grid = 20;
        assertNotNull(game.ID);
        assertNotNull(game.identified_words);
        assertNotNull(game.grid);
        assertEquals(game.test_grid, game.grid.length);
        assertEquals(game.test_grid, game.grid[0].length);
    }

    @Test
    public void testCreateGrid() {
        Game game = new Game(1, 20);
        game.test_grid = 20;
        char[][] grid = game.createGrid();
        assertEquals(game.test_grid, grid.length);
        assertEquals(game.test_grid, grid[0].length);
        for (int i = 0; i < game.test_grid; i++) {
            for (int j = 0; j < game.test_grid; j++) {
                assertTrue(Character.isLetter(grid[i][j]));
            }
        }
    }

    @Test
    public void testIsValidWord() {
        Game game = new Game(1, 20);
        game.test_grid = 20;
        Leaderboard lb = new Leaderboard();
        ArrayList<Coordinate> indexLetters = new ArrayList<>();
        // Add some coordinates to the list
        indexLetters.add(new Coordinate(1, 2));
        indexLetters.add(new Coordinate(1, 3));
        assertTrue(game.isValidWord(indexLetters,"Test",lb));
    }

    @Test
    public void testIsValidOrientation() {
        Game game = new Game(1, 20);
        game.test_grid = 20;
        ArrayList<Coordinate> indexLetters = new ArrayList<>();
        // Add some coordinates to the list
        indexLetters.add(new Coordinate(1, 2));
        indexLetters.add(new Coordinate(3, 2)); // Vertical orientation
        ArrayList<Coordinate> selectedLetters = game.isValidOrientation(indexLetters);
        assertEquals(3, selectedLetters.size());
        for (Coordinate coord : selectedLetters) {
            assertEquals(2, coord.col);
        }

        indexLetters.clear();
        indexLetters.add(new Coordinate(1, 2));
        indexLetters.add(new Coordinate(1, 4)); // Horizontal orientation
        selectedLetters = game.isValidOrientation(indexLetters);
        assertEquals(3, selectedLetters.size());
        for (Coordinate coord : selectedLetters) {
            assertEquals(1, coord.row);
        }

        indexLetters.clear();
        indexLetters.add(new Coordinate(1, 2));
        indexLetters.add(new Coordinate(3, 4)); // Diagonal orientation
        selectedLetters = game.isValidOrientation(indexLetters);
        assertEquals(3, selectedLetters.size());
        for (int i = 0; i < selectedLetters.size(); i++) {
            assertEquals(i + 1, selectedLetters.get(i).row);
            assertEquals(i + 2, selectedLetters.get(i).col);
        }
    }

    @Test
    public void testGameOver() {
        Game game = new Game(1, 20);
        game.test_grid = 20;
        Leaderboard lb = new Leaderboard();
        ArrayList<String> names_winners = game.GameOver(true, lb);
        assertEquals(0, names_winners.size());

        names_winners = game.GameOver(false, lb);
        assertEquals(0, names_winners.size());
    }

    @Test
    public void testAllMethods() {
        Game game = new Game(1, 20);
        game.test_grid = 20;
        Leaderboard lb = new Leaderboard();

        // Test createGrid
        char[][] grid = game.createGrid();
        assertEquals(game.test_grid, grid.length);
        assertEquals(game.test_grid, grid[0].length);

        // Test isValidWord
        ArrayList<Coordinate> indexLetters = new ArrayList<>();
        // Add some coordinates to the list
        indexLetters.add(new Coordinate(1, 2));
        indexLetters.add(new Coordinate(1, 3));
        assertTrue(game.isValidWord(indexLetters, "Test", lb));

        // Test isValidOrientation
        indexLetters.clear();
        // Add some coordinates to the list
        indexLetters.add(new Coordinate(1, 2));
        indexLetters.add(new Coordinate(3, 2)); // Vertical orientation
        ArrayList<Coordinate> selectedLetters = game.isValidOrientation(indexLetters);
        assertEquals(3, selectedLetters.size());
        for (Coordinate coord : selectedLetters) {
            assertEquals(2, coord.col);
        }

        // Test GameOver
        ArrayList<String> names_winners = game.GameOver(false, lb);
        assertEquals(0, names_winners.size());
    }

    @Test
    public void testGridCreatedInTime() {
        Game game = new Game(1, 20);
        game.test_grid = 20;


        long startTime = System.nanoTime();
        char[][] grid = game.createGrid();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        assertTrue(duration < MAX_CREATE_TIME);
    }

    @Test
    public void testReusingFillerChars() {
        // Use GameClient's version of create_grid 
        // to get a hashmap with <K: char, V: it's count>
        GameClient game = new GameClient(1, 20);
        game.test_grid = 20;
        char[][] grid = game.createGrid();
        Map<Character, Integer> letters = game.create_grid(game.grid);
        
        // Set to first item in list that way
        // we get an actual comparison 
        int min_value = letters.get('A');
        int max_value = letters.get('A');

        // iterate over keys to find the 
        // most used letters and the least
        // used letters
        for(Integer s : letters.values()) {
            if (s < min_value) {
                min_value = s;
            }
            
            if (s > max_value) {
                max_value = s;
            }
        }
        int difference = max_value - min_value;
        // uncomment to see counts of each letter
        // System.out.println(letters.entrySet());
        // System.out.println(difference);

        // difference == 0: each letter was used the exact same amount of times
        // difference == 1: a letter has been reused
        assertTrue(difference == 1 || difference == 0);
    }

    @Test
    public void testDifferentGameBoards() {
        Game game = new Game(1, 20);
        Game game2 = new Game(1, 20);

        // Assume they're the same until proven wrong
        boolean same = true;

        // compare each row and check if they're different
        for (int i = 0; i < 20; i++) {
            if (!Arrays.equals(game.grid[i], game2.grid[i])) {
                same = false;
                break;
            }
        }

        assertFalse(same);
    }
}