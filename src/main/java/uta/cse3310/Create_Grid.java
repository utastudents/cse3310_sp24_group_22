package uta.cse3310;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Create_Grid {
    public char[][] boardArray;
    private int boardLength;
    private int boardWidth;
    private double density;
    public ArrayList<String> validWords;
    public ArrayList<String> selected_words;

    public Create_Grid(int boardLength, int boardWidth, double density) {
        this.boardLength = boardLength;
        this.boardWidth = boardWidth;
        this.density = density;
        this.boardArray = new char[boardLength][boardWidth];
        this.validWords = new ArrayList<>();
        selected_words = new ArrayList<>();
    }

    public boolean initializeBoard(String filePath) {
        // Initialize board with #
        for (int i = 0; i < boardLength; i++) {
            Arrays.fill(boardArray[i], '#');
        }

        // Read words from file and populate validWords list
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = br.readLine()) != null) {
                validWords.add(word.trim().toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Populate board with words
        populateBoardWithWords();
        return true;
    }

    private boolean populateBoardWithWords() {
        Random random = new Random();
        int wordsPlaced = 0;
        double totalCells = boardLength * boardWidth;
        double currentDensity = 0;

        while (currentDensity < density && !validWords.isEmpty()) {
            String word = validWords.remove(random.nextInt(validWords.size()));
            int startX = random.nextInt(boardLength);
            int startY = random.nextInt(boardWidth);
            int orientation = random.nextInt(5); // 0: horizontal, 1: vertical up, 2: vertical down, 3: diagonal down, 4: diagonal up

            // Place word based on orientation
            boolean placed = placeWord(word, startX, startY, orientation);
            if (placed) {
                wordsPlaced++;
                currentDensity = (double) (wordsPlaced * word.length()) / totalCells;
                selected_words.add(word);
            }
        }return true;
        
    }

    private boolean placeWord(String word, int startX, int startY, int orientation) {
        int wordLength = word.length();
        int deltaX = 0, deltaY = 0;

        switch (orientation) {
            case 0: // Horizontal
                if (startX + wordLength > boardLength) {
                    return false;
                }
                deltaX = 1;
                break;
            case 1: // Vertical up
                if (startY - wordLength + 1 < 0) {
                    return false;
                }
                deltaY = -1;
                break;
            case 2: // Vertical down
                if (startY + wordLength > boardWidth) {
                    return false;
                }
                deltaY = 1;
                break;
            case 3: // Diagonal down
                if (startX + wordLength > boardLength || startY + wordLength > boardWidth) {
                    return false;
                }
                deltaX = 1;
                deltaY = 1;
                break;
            case 4: // Diagonal up
                if (startX + wordLength > boardLength || startY - wordLength + 1 < 0) {
                    return false;
                }
                deltaX = 1;
                deltaY = -1;
                break;
        }

        // Check if word clashes with existing word
        for (int i = 0; i < wordLength; i++) {
            int x = startX + i * deltaX;
            int y = startY + i * deltaY;
            if (boardArray[x][y] != '#' && boardArray[x][y] != word.charAt(i)) {
                return false;
            }
        }

        // Place the word in the board
        for (int i = 0; i < wordLength; i++) {
            int x = startX + i * deltaX;
            int y = startY + i * deltaY;
            boardArray[x][y] = word.charAt(i);
        }

        return true;
    }




}
