package uta.cse3310;

import java.util.ArrayList;
import java.util.Collections;

public class Color_Picker {
    ArrayList<String> colorPresets = new ArrayList<String>();
    ArrayList<String> colorPicked = new ArrayList<String>();

    public Color_Picker() {
        colorPresets.add("Red");
        colorPresets.add("Blue");
        colorPresets.add("Green");
        colorPresets.add("Yellow");
        colorPresets.add("Purple");
        colorPresets.add("Orange");
        colorPresets.add("Pink");
        colorPresets.add("Brown");
        colorPresets.add("Black");
        colorPresets.add("navy");
        colorPresets.add("gray");
        colorPresets.add("white");
        colorPresets.add("cyan");
        colorPresets.add("magenta");
        colorPresets.add("teal");
        colorPresets.add("lavender");
        colorPresets.add("maroon");
        colorPresets.add("olive");
        colorPresets.add("coral");
        colorPresets.add("gold");
        colorPresets.add("salmon");
        colorPresets.add("khaki");
        colorPresets.add("plum");
        colorPresets.add("indigo");
    }

    public String assignColor() {
        if (colorPresets.isEmpty()) {
            System.out.println("No more colors available");
            return null;
        }

        // choosing a random color from the presets and removing the color from the list
        Collections.shuffle(colorPresets);
        String color = colorPresets.remove(colorPresets.size() - 1);
        colorPicked.add(color);

        return color;
    }
}