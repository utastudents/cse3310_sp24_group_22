package uta.cse3310;

import java.util.ArrayList;
import java.util.Arrays; // Import Arrays class

public class Color {
    private ArrayList<String> colors_list;
    private ArrayList<String> colors_taken;
    
    public Color() {
        colors_list = new ArrayList<>(Arrays.asList(
            "Blue", "Green", "Red", "Yellow", "Orange", "Purple", "Pink", "Cyan", "Magenta",
            "Lime", "Teal", "Indigo", "Turquoise", "SlateGray", "Violet", "Maroon", "Olive",
            "Navy", "Chocolate", "Crimson"));
            
        colors_taken = new ArrayList<>();
    }
    
    public String pickColor() {
        String color_picked = colors_list.get(0); // Use colors_list instead of colors
        colors_taken.add(colors_list.get(0)); // Use colors_list instead of colors
        colors_list.remove(0); // Use colors_list instead of colors
        return color_picked;
    }
}

