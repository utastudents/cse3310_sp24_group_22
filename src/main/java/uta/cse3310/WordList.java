package uta.cse3310;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Fetches words from wordlist.
 * Attributes:
 * Words - an array with random word with different lengths
 */
public class WordList {
    /**
     * Attribute most likely to have words used in a Board.
     */
    public List<String> Words = new ArrayList<String>();

    /**
     * Basically a copy of words.txt in memory. Would be opening and closing the file when
     * initializing the class each time.
     */
    private List<String> WordOptions = new ArrayList<String>();
    private Path file_path = Paths.get("./words.txt");

    private int max_word_options;
    private int MIN_LETTERS = 3;
    private int MAX_LETTERS;  // Can be line length but most English words aren't more than 10 letters
    private Random rand = new Random();

    WordList(){
        MAX_LETTERS = 50;
        try {
            WordOptions = Files.readAllLines(file_path, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }
    WordList(int grid_size){
        MAX_LETTERS = grid_size;
        try {
            WordOptions = Files.readAllLines(file_path, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }

    /** Picks a word of a certain size. */
    public String word_picker(int size){
        for (String word : WordOptions) {
            if (word.length() == size)
                return word;
        }
        return "";
    }

    /** 
     * Match on regex string. Limit the size of the expression for best time.
    */
    public String find_word(String regex_string){
        for (String word : WordOptions){
            if (word.matches(regex_string))
                return word;
        }
        return "";
    }

    /**
     * Fetches a random word in WordOptions.
     */
    public String get_word() {
        return WordOptions.get(rand.nextInt(1000));
    }
}