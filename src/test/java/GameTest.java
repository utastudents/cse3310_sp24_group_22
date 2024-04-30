package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class GameTest {

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
}

