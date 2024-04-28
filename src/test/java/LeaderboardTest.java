package uta.cse3310;


import org.junit.Test;
import static org.junit.Assert.*;

public class LeaderboardTest {

    @Test
    public void testAdd() {
        Leaderboard lb = new Leaderboard();
        lb.add("John", 100);
        lb.add("Jane", 50);
        lb.add("Bob", 200);

        assertEquals(3, lb.LB.size());
        assertEquals(200, (int) lb.LB.get("Bob"));
        assertEquals(100, (int) lb.LB.get("John"));
        assertEquals(50, (int) lb.LB.get("Jane"));
    }

    @Test
    public void testRemove() {
        Leaderboard lb = new Leaderboard();
        lb.add("John", 100);
        lb.add("Jane", 50);
        lb.add("Bob", 200);

        lb.remove("Jane");
        assertEquals(2, lb.LB.size());
        assertNull(lb.LB.get("Jane"));

        lb.remove("John");
        assertEquals(1, lb.LB.size());
        assertNull(lb.LB.get("John"));
    }

    @Test
    public void testRemoveNullHandle() {
        Leaderboard lb = new Leaderboard();
        lb.add("John", 100);
        lb.remove(null);
        assertEquals(1, lb.LB.size());
        assertEquals(100, (int) lb.LB.get("John"));
    }

    @Test
    public void testAddDuplicateHandle() {
        Leaderboard lb = new Leaderboard();
        lb.add("John", 100);
        lb.add("John", 200);

        assertEquals(1, lb.LB.size());
        assertEquals(200, (int) lb.LB.get("John"));
    }
}