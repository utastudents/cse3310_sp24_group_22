package uta.cse3310;
import static org.junit.Assert.*;
import org.junit.*;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class AppTest {

    @Test
    public void test_valid_player() throws URISyntaxException, InterruptedException {
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

        // Check if the received message indicates disapproval
        assertEquals("disapproved", message);
    }
}

