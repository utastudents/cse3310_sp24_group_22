package uta.cse3310;

import static org.junit.Assert.*;
import org.junit.*;

import uta.cse3310.ChatBox;
import uta.cse3310.Message;

public class ChatBoxTest {
    private ChatBox chat;

    private String bad_message;
    private String handle;
    private Message M;

    @Before
    public void setUp() {
        chat = new ChatBox();
        bad_message = "Bad Me$$age";
        handle = "player";
        M = new Message();
    }

    @Test
    public void test_valid_message() {
        M.Message = bad_message;
        chat.add(M);
        assertTrue(chat.display_message().empty());
    }
}
