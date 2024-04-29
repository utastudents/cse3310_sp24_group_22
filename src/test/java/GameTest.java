package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class GameTest {

    @Test
    public void testConstructor() {
        Game game = new Game(1, 1);
        assertNotNull(game.ID);
        assertNotNull(game.identified_words);
        assertNotNull(game.grid);
        assertEquals(20, game.grid.length);
        assertEquals(20, game.grid[0].length);
    }

    @Test
    public void testCreateGrid() {
        Game game = new Game(1, 1);
        char[][] grid = game.createGrid();
        assertEquals(20, grid.length);
        assertEquals(20, grid[0].length);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                assertTrue(Character.isLetter(grid[i][j]));
            }
        }
    }

    @Test
    public void testIsValidWord() {
        Game game = new Game(1, 1);
        Leaderboard lb = new Leaderboard();
        ArrayList<Coordinate> indexLetters = new ArrayList<>();
        // Add some coordinates to the list
        indexLetters.add(new Coordinate(1, 2));
        indexLetters.add(new Coordinate(1, 3));
        assertTrue(game.isValidWord(indexLetters,"Test",lb));
    }

    @Test
    public void testIsValidOrientation() {
        Game game = new Game(1, 1);
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
}
