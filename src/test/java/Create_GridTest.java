package uta.cse3310;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class Create_GridTest {

    @Test
    void testInitializeBoard() {
        Create_Grid grid = new Create_Grid(20, 20, 0.67);
        assertTrue(grid.initializeBoard("files.txt"));

    }

    @Test
    void testPopulateBoardWithWords() {
        Create_Grid grid = new Create_Grid(20, 20, 0.67);
        assertTrue(grid.populateBoardWithWords());

    }

    @Test
    void testPlaceWord() {
        Create_Grid grid = new Create_Grid(20, 20, 0.67);
        assertTrue(grid.placeWord("TEST", 0, 0, 0)); // Assuming "TEST" is a valid word for testing

    }



    @Test
    void testInvalidPlaceWord() {
        Create_Grid grid = new Create_Grid(10, 10, 0.5);
        // Test placing word out of bounds horizontally
        assertFalse(grid.placeWord("TEST", 9, 9, 0));
        // Test placing word out of bounds vertically
        assertFalse(grid.placeWord("TEST", 9, 9, 1));
        assertFalse(grid.placeWord("TEST", 0, 9, 2));
        // Test placing word out of bounds diagonally
        assertFalse(grid.placeWord("TEST", 9, 9, 3));
        assertFalse(grid.placeWord("TEST", 9, 0, 4));

    }

    @Test
    void testInvalidInitializeBoard() {
        Create_Grid grid = new Create_Grid(10, 10, 0.5);
        assertFalse(grid.initializeBoard("non_existing_file.txt")); // Assuming "non_existing_file.txt" doesn't exist

    }

}

