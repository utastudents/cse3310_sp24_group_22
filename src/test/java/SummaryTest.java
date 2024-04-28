package uta.cse3310;

import java.util.ArrayList;
import java.util.TreeMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class SummaryTest {

        @Test
    public void testCreateSummary_Tie() {
        Summary summary = new Summary();
        java.util.ArrayList<Integer> userIds = new java.util.ArrayList<>();
        userIds.add(1);
        userIds.add(2);
        userIds.add(3);
        java.util.TreeMap<Integer, String> result = summary.Create_Summary(userIds, -1);
        assertEquals("tie", result.get(1));
        assertEquals("tie", result.get(2));
        assertEquals("tie", result.get(3));
    }

    @Test
    public void testCreateSummary_Winner() {
        Summary summary = new Summary();
        java.util.ArrayList<Integer> userIds = new java.util.ArrayList<>();
        userIds.add(1);
        userIds.add(2);
        userIds.add(3);
        java.util.TreeMap<Integer, String> result = summary.Create_Summary(userIds, 2);
        assertEquals("lose", result.get(1));
        assertEquals("win", result.get(2));
        assertEquals("lose", result.get(3));
    }

    @Test
    public void testClearSummary() {
        Summary summary = new Summary();
        java.util.ArrayList<Integer> userIds = new java.util.ArrayList<>();
        userIds.add(1);
        userIds.add(2);
        userIds.add(3);
        summary.Create_Summary(userIds, 2);
        summary.Clear_Summary();
        assertTrue(summary.Summary_list.isEmpty());
    }
}