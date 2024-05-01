package uta.cse3310;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class AppClient extends WebSocketClient {
    public String msg;

    public AppClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public AppClient(URI serverURI) throws URISyntaxException {
        super(serverURI);
    }

    public AppClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        send("Testing open");
        System.out.println("new connection opened");

    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
        msg = message;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }

    public static void main(String[] args) throws URISyntaxException {
        WebSocketClient ac = new AppClient(new URI("ws://localhost:8000"));
        ac.connect();
    }
}