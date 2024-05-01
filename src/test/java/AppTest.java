package uta.cse3310;

import static org.junit.Assert.*;
import org.junit.*;

import com.google.gson.Gson;


/**
 * Unit test for simple App.
 */
public class AppTest {
    private int HTTP_PORT = 8000;
    private int WEBSOCKET_PORT = 9000;
    private int TEST_GRID ;
    private App app = null;
    private UserEvent U;
    private Gson gson;
    private String jsonString;
    
    
    @Before
    public void setUp() {
        app = new App(WEBSOCKET_PORT);
    }

    @Test
    public void test_valid_player() {
        String bad_handle = "this handle is too long";
        String good_handle = "good";

        U.Handle = bad_handle;
        U.ready = -2;

        jsonString = gson.toJson(U);

        assertTrue( false );
        // assertTrue(ac.msg == "disapproved");
    }

    public static void main(String[] args) {
        int port = 8100;
        App app = new App(port);
    }
}
