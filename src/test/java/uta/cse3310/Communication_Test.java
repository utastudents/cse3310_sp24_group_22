package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Communication_Test {
    /*@Test
    public void testCommunication() {
        Communication messaging = new Communication();

        // Test valid message
        messaging.addMessage("Hello, world!", "player1");
        assertEquals(1, messaging.getMessages().size());
        assertEquals("Hello, world!", messaging.getMessages().iterator().next());

        // Test invalid message
        messaging.addMessage("Hello, world! ", "player2");
        assertEquals(1, messaging.getMessages().size());
        assertEquals("Invalid message format. Please enter alpha-numeric characters only.", messaging.getErrorMessage());
    }

    private Set<String> getMessages(Communication messaging) {
        Set<String> messages = new HashSet<>();
        for (String message : messaging.getMessages()) {
            messages.add(message);
        }
        return messages;
    }*/
}
