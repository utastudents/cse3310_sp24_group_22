package uta.cse3310;

import static org.junit.Assert.*;
import org.junit.*;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class AppTest {

    @Test
    public void test_invalid_player() throws URISyntaxException, InterruptedException {
        // Create a sample UserEvent object
        UserEvent U = new UserEvent();
        U.Handle = "this handle is too long";
        U.ready = -2;

        // Convert the UserEvent object to JSON string
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(U);

        // Create an instance of AppClient
        AppClient ac = new AppClient();

        // Simulate receiving the message from the server
        String message = ac.onMessage(jsonString);

        // Should disapprove a name longer than 10 letters
        assertEquals("disapproved", message);
    }
    
    @Test
    public void test_valid_player() throws URISyntaxException, InterruptedException {
        // Create a sample UserEvent object
        UserEvent U1 = new UserEvent();
        U1.Handle = "Test123";
        U1.ready = -1;
        U1.GameType = 2;
        
        UserEvent U2 = new UserEvent();
        U2.Handle = "TEST1234";
        U2.ready = -1;
        U2.GameType = 2;        

        // Convert the UserEvent object to JSON string
        Gson gson = new GsonBuilder().create();
        String jsonString;
        AppClient ac = new AppClient();        
        String message1;
        String message2;
        
        // Convert the UserEvent object to JSON string
        jsonString = gson.toJson(U1);

        message1 = ac.onMessage(jsonString);
        assertEquals("[\"2\"]",message1);
        
        jsonString = gson.toJson(U2);
        assertEquals("[\"2\"]",message1);
        
        //User1 is ready
        U1.ready = 1;
        jsonString = gson.toJson(U1);
        message1 = ac.onMessage(jsonString);
		assertEquals("[\"2\",\"Test123\"]",message1);
		
    }    
}

