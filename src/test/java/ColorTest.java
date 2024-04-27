package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColorTest {
    private Color color;
    private ArrayList<String> colors_taken;
    private ArrayList<String> colors_list;

    @BeforeEach
    public void setUp() {
        color = new Color();
        colors_taken = new ArrayList<>();
        colors_list = new ArrayList<>(Arrays.asList(
            "Blue", "Green", "Red", "Yellow", "Orange", "Purple", "Pink", "Cyan", "Magenta",
            "Lime", "Teal", "Indigo", "Turquoise", "SlateGray", "Violet", "Maroon", "Olive",
            "Navy", "Chocolate", "Crimson"));
    }

    @Test
    public void testPickColor() {
        String picked_color = color.pickColor();
        assertTrue(colors_list.contains(picked_color));
        assertTrue(colors_taken.contains(picked_color));
    }

    @Test
    public void testPickColor_AfterPickingOneColor() {
        String picked_color = color.pickColor();
        assertEquals("Blue", picked_color);
        assertTrue(colors_list.contains("Green"));
        assertTrue(colors_taken.contains("Blue"));
    }

    @Test
    public void testPickColor_AfterPickingAllColors() {
        for (int i = 0; i < colors_list.size(); i++) {
            color.pickColor();
        }
        assertTrue(colors_list.isEmpty());
        assertEquals(colors_list.size(), colors_taken.size());
    }
}