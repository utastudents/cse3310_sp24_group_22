package uta.cse3310;

import java.util.LinkedList;
import java.util.Queue;

public class ChatBox {
    private Queue<Message> messages = new LinkedList<Message>();
    
    public Queue<Message> display_message(){
        return messages;
    }

    public void add(Message M) {
        messages.offer(M);
        return;
    }
}