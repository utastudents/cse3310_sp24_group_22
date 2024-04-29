package uta.cse3310;

import java.util.ArrayList;
import java.util.TreeMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class SummaryTest {

    @Test
    public void testCreateSummary_Tie() {
        Summary summary = new Summary();
        java.util.ArrayList<String> userIds = new java.util.ArrayList<>();
        userIds.add("user1");
        userIds.add("user2");
        userIds.add("user3");
        
        java.util.ArrayList<String> winners = new java.util.ArrayList<>();
        winners.add("user1");
        winners.add("user2");
        winners.add("user3");
        summary.Create_Summary(userIds, winners);
        java.util.TreeMap<String, String> result = summary.Summary_list;
        assertEquals("tie", result.get("user1"));
        assertEquals("tie", result.get("user2"));
        assertEquals("tie", result.get("user3"));
    }

    @Test
    public void testCreateSummary_Winner() {
        Summary summary = new Summary();
        java.util.ArrayList<String> userIds = new java.util.ArrayList<>();
        userIds.add("user1");
        userIds.add("user2");
        userIds.add("user3");
        
        java.util.ArrayList<String> winners = new java.util.ArrayList<>();
        winners.add("user1");
       	summary.Create_Summary(userIds, winners);
        java.util.TreeMap<String, String> result = summary.Summary_list;
        assertEquals("win", result.get("user1"));
        assertEquals("lose", result.get("user2"));
        assertEquals("lose", result.get("user3"));
    }

    @Test
    public void testClearSummary() {
        Summary summary = new Summary();
        java.util.ArrayList<String> userIds = new java.util.ArrayList<>();
        userIds.add("user1");
        userIds.add("user2");
        userIds.add("user3");
        java.util.ArrayList<String> winners = new java.util.ArrayList<>();
        winners.add("user1");
       	summary.Create_Summary(userIds, winners);
        summary.Clear_Summary();
        assertTrue(summary.Summary_list.isEmpty());
    }
}
