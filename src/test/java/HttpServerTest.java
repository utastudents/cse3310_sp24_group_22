package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.BindException;

public class HttpServerTest {

    @Test
    public void testConstructor() {
        HttpServer server = new HttpServer(8080, "./html");
        assertEquals(8080, server.port);
        assertEquals("./html", server.dirname);
    }

    @Test
    public void testStart() throws Exception {
        HttpServer server = new HttpServer(8080, "./html");
        try {
            server.start();
            // Verify that the server is listening on the correct port
            assertTrue(isPortListening(8080));
        } catch (IOException e) {
            // Handle the exception here
        }
    }

    private boolean isPortListening(int port) throws IOException {
        try (ServerSocket socket = new ServerSocket(port)) {
            return false;
        } catch (BindException e) {
            return true;
        }
    }
}