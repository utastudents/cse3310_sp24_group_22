package uta.cse3310;

import java.util.LinkedList;
import java.util.Stack;

public class ChatBox {
    private Stack<Message> messages = new Stack<Message>();
    
    public Stack<Message> display_message(){
        return messages;
    }

    public void add(Message M) {
        if (validate_message(M.Message))
            messages.push(M);
        return;
    }

    /**
     * validates that messages sent use alphanumeric characters.
     * @param msg
     * @return
     */
    private boolean validate_message(String msg) {
        return msg.matches("[A-Za-z0-9 ]*");
    }
}