
// This is example code provided to CSE3310 Fall 2022
// You are free to use as is, or changed, any of the code provided

// Please comply with the licensing requirements for the
// open source packages being used.

// This code is based upon, and derived from the this repository
//            https:/thub.com/TooTallNate/Java-WebSocket/tree/master/src/main/example

// http server include is a GPL licensed package from
//            http://www.freeutils.net/source/jlhttp/

/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package uta.cse3310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.time.Instant;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends WebSocketServer {

  // All games currently underway on this server are stored in
  // the vector ActiveGames
  // private Vector<Game> ActiveGames = new Vector<Game>();

  private int GameId = 1;

  private int connectionId = 0;

  private Instant startTime;
  //private int gamesinProgress = ActiveGames.size();

  public App(int port) {
    super(new InetSocketAddress(port));
  }

  public App(InetSocketAddress address) {
    super(address);
  }

  public App(int port, Draft_6455 draft) {
    super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
  }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
        ServerEvent event = new ServerEvent();

        // Parse the request from the client
        String request = handshake.getResourceDescriptor();
        int numPlayers = Integer.parseInt(request.substring(1)); // Assuming request is "/2", "/3", or "/4"

        // Search for a game with the desired number of players
        // Game game = null;
        // for (Game g : activeGames) {
        //     if (g.getNumPlayers() == numPlayers) {
        //         game = g;
        //         System.out.println("Found a match");
        //         break;
        //     }
        // }

        // // If no matches found, create a new game
        // if (game == null) {
        //     game = new Game();
        //     game.setGameId(GameId++);
        //     activeGames.add(game);
        //     game.setGamesInProgress(gamesInProgress);
        //     game.setNumPlayers(numPlayers); // Set the number of players for the new game
        //     System.out.println("Creating a new game for " + numPlayers + " players");
        // }

        // // Add the player to the game
        // game.addPlayer();
        // System.out.println("Player added to the game");

        // // If enough players have joined, start the game
        // if (game.getNumPlayers() == numPlayers) {
        //     game.StartGame();
        //     System.out.println("Game started");
        //     gamesInProgress++;
        //     for (Game g : activeGames) {
        //         g.setGamesInProgress(gamesInProgress);
        //         String jsonString = new Gson().toJson(g);
        //         System.out.println(jsonString);
        //         broadcast(jsonString);
        //     }
        // }

        // // Send game details to the player who just joined
        // event.setYouAre(game.getPlayers().get(game.getNumPlayers() - 1)); // Set the player's type
        // event.setGameId(game.getGameId());
        // conn.setAttachment(game);

        // Gson gson = new Gson();
        // conn.send(gson.toJson(event));

        // // Send the state of the game to everyone
        // for (Game g : activeGames) {
        //     g.setTotal(total);
        //     String jsonString = gson.toJson(g);
        //     System.out.println(jsonString);
        //     broadcast(jsonString);
        // }
    }



  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    System.out.println(conn + " has closed");
    // Retrieve the game tied to the websocket connection
    //Game G = conn.getAttachment();
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    
    // ActiveGames.removeElement(G);
    // gamesinProgress-=1;
    // if (gamesinProgress <0)
    // {
    //     gamesinProgress = 0;
    // }
    // for (Game i : ActiveGames)
    // {
    //   i.gamesinProgress = gamesinProgress;
     
    //   String jsonString;
    //   jsonString = gson.toJson(i);

    //   System.out.println(jsonString);
    //   broadcast(jsonString);
    // }
    // G =null;
    
    }

   @Override
  public void onMessage(WebSocket conn, String message) {
    System.out.println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "-" + " " + escape(message));

    // Bring in the data from the webpage
    // A UserEvent is all that is allowed at this point
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    UserEvent U = gson.fromJson(message, UserEvent.class);

    // Update the running time
    //stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

    // Get our Game Object
    // Game G = conn.getAttachment();
    // G.Update(U);

    // send out the game state every time
    // to everyone
    String jsonString;
    jsonString = gson.toJson(G);

    System.out
        .println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
    broadcast(jsonString); 
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message) {
    System.out.println(conn + ": " + message);
  }

  @Override
  public void onError(WebSocket conn, Exception ex) {
    ex.printStackTrace();
    if (conn != null) {
      // some errors like port binding failed may not be assignable to a specific
      // websocket
    }
  }

  @Override
  public void onStart() {
    setConnectionLostTimeout(0);
    //stats = new Statistics();
    startTime = Instant.now();
  }

  private String escape(String S) {
    // turns " into \"
    String retval = new String();
    // this routine is very slow.
    // but it is not called very often
    for (int i = 0; i < S.length(); i++) {
      Character ch = S.charAt(i);
      if (ch == '\"') {
        retval = retval + '\\';
      }
      retval = retval + ch;
    }
    return retval;
  }
  public static void main(String[] args) {

    String HttpPort = System.getenv("HTTP_PORT");
    int port = 9022;
    if (HttpPort!=null) {
      port = Integer.valueOf(HttpPort);
    }

    // Set up the http server

    HttpServer H = new HttpServer(port, "./html");
    H.start();
    System.out.println("http Server started on port: " + port);

    // create and start the websocket server

    port = 9122;
    String WSPort = System.getenv("WEBSOCKET_PORT");
    if (WSPort!=null) {
      port = Integer.valueOf(WSPort);
    }

    App A = new App(port);
    A.setReuseAddr(true);
    A.start();
    System.out.println("websocket Server started on port: " + port);

  }
}
