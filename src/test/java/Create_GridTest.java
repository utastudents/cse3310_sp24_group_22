package uta.cse3310;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class Create_GridTest {

    @Test
    void testInitializeBoard() {
        Create_Grid grid = new Create_Grid(10, 10, 0.5);
        assertTrue(grid.initializeBoard("files.txt")); 
    }

    @Test
    void testPopulateBoardWithWords() {
        Create_Grid grid = new Create_Grid(10, 10, 0.5);
        assertTrue(grid.populateBoardWithWords());
        // Add more assertions as needed
    }

    @Test
    void testPlaceWord() {
        Create_Grid grid = new Create_Grid(10, 10, 0.5);
        assertTrue(grid.placeWord("TEST", 0, 0, 0)); // Assuming "TEST" is a valid word for testing
        // Add more assertions as needed
    }
}
