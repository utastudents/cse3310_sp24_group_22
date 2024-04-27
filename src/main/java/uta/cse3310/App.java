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

import javax.lang.model.element.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends WebSocketServer {

    //List of players
    private HashMap<String, UserEvent> Players = new HashMap<>();


    //Wait Games
    private ArrayList<String> Wait_game_2;
    private ArrayList<String> Wait_game_3;
    private ArrayList<String> Wait_game_4;
    
    //Class variables
    private Color color;
    private Leaderboard leaderboard;
    private ArrayList<Coordinate> letters;
    
	//List of available games
	private ArrayList<Game> Games;
    
    //Server id
    private int ser = 1;
    
    //Variables 
    private Game g = null;
    private ArrayList<String> Selected_Game;
    private int GId = 1;
    public String jsonString = "";
    
    //Game variables
   
  

    public App(int port) {
        super(new InetSocketAddress(port));
        initialize();
    }

    public App(InetSocketAddress address) {
        super(address);
        initialize();
    }

    public App(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.singletonList(draft));
        initialize();
    }

    private void initialize() 
    {
    	//Initialize all players
    	//Players = new ArrayList<>();
    	
    	//Initialize wait games and number allocated to it
        Wait_game_2 = new ArrayList<>();
        Wait_game_2.add("2");
        
        Wait_game_3 = new ArrayList<>();
        Wait_game_3.add("3");
        
        Wait_game_4 = new ArrayList<>();
        Wait_game_4.add("4");
        
        //Initial objects
        color = new Color();
        leaderboard = new Leaderboard();
        letters = new ArrayList<>();
        
        //Initialize available games
        Games = new ArrayList<>();
        Game game1 = new Game(0,0);
        Game game2 = new Game(0,0);
        Game game3 = new Game(0,0);
        Game game4 = new Game(0,0);
        Game game5 = new Game(0,0);
        
        Games.add(game1);
        Games.add(game2);
        Games.add(game3);
        Games.add(game4);
        Games.add(game5);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
		Gson gson = new Gson();
		//Send out the server id to each user
		conn.send(gson.toJson(ser));
		System.out.println(gson.toJson(ser));
		ser+=1;
		
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn + " has closed");
        String user = conn.getAttachment();
        leaderboard.remove(user);
        Players.remove(user);

		Gson gson = new GsonBuilder().create();
		String jsonString = gson.toJson(leaderboard);
		broadcast(jsonString);  
        
    }

    @Override
    public void onMessage(WebSocket conn, String message) 
    {
        // Deserialize the JSON message into a UserEvent object
        Gson gson = new GsonBuilder().create();
        UserEvent U = gson.fromJson(message, UserEvent.class);
        conn.setAttachment(U.Handle);
        //Just entered the game
        if (U.ready == -1)
        {
        	System.out.println("This is the player: " + U.Uid);
        	//Assign the color
        	U.color = color.pickColor();
        	
        	//Add the person to the leaderboard
        	leaderboard.add(U.Handle,0);
        	
        	//Add it to all players list
        	Players.put(U.Handle,U);
        }
        
        //Add them to respective wait list
        else if (U.ready == 1)
        {
        	//Update the players
        	Players.get(U.Handle).ready = 1;

        	
        	//Update wait list
        	if (U.GameType == 2)
        	{
        		Wait_game_2.add(U.Handle);
        		Selected_Game = Wait_game_2;
        		
        	}
        	else if (U.GameType == 3)
        	{
        		Wait_game_3.add(U.Handle);
        		Selected_Game = Wait_game_3;
        	}
        	else
        	{
        		Wait_game_4.add(U.Handle);
        		Selected_Game = Wait_game_4;
        	}
        	
        	//Check wait list if there are required number of players for a game
        	if (((Selected_Game.size()-1) != 0) && ((Selected_Game.size()-1) % U.GameType == 0))
        	{
        		//Check if any games are available
        		for (int j = 0; j < Games.size(); j++)
        		{
        			if (Games.get(j).busy == 0)
        			{
        				g = Games.get(j);        				
        			}
        		}
        		
        		
        		if (g != null)
        		{
        			g.busy = 1;
        			g.GameId = GId;
        			GId++;
        			
        			//Remove the players from wait list if there is a game
					for (int i = 0; i < U.GameType; i++)
					{
						//Add them to the Game
						g.ID.add(Players.get(Selected_Game.get(1)).Uid);
						
						//Change up ready to 0 so it means they are in a game
						Players.get(Selected_Game.get(1)).ready = 0;
						
						//Change up ready to 0 so it means they are in a game
						Players.get(Selected_Game.get(1)).game = g;						
												
						//Remove the person from waitlist
						Selected_Game.remove(1);

					}
					
        		}
        		g = null;
        	}
        }
        

		//If ready = 0, that means they are already in a game
        else
        {
        	//U.letters are the two letters selected
			if (U.letters.size() != 0)
			{
				//Check if the two letters are of valid orientation
				//Returns list of letters between two letters OR nothing in it
				letters = U.game.isValidOrientation(U.letters);
				System.out.println(letters);
				
				//Check if it is a valid word
				if (Players.get(U.Handle).game.isValidWord(letters))
				{	
					//Update the letters to be selected							
					Players.get(U.Handle).letters = letters;
					
					//update leaderboard
					leaderboard.add(U.Handle,0);				
				}
				else
				{		
					//If it isnt a valid word then clear out selected letters
					Players.get(U.Handle).letters.clear();
				}
				
				//Update the players
				
			}	
        }
        
        //Send change in state to everyone
		jsonString = gson.toJson(Wait_game_2);
		broadcast(jsonString);
		
		jsonString = gson.toJson(Wait_game_3);
		broadcast(jsonString);	   
		
		jsonString = gson.toJson(Wait_game_4);
		broadcast(jsonString);	
				
		//Send the leaderboard
		jsonString = gson.toJson(leaderboard);
		broadcast(jsonString);   	        
        
        //Send player information
		for (Map.Entry<String, UserEvent> entry : Players.entrySet()) 
		{
			
			jsonString = gson.toJson(entry.getValue());
			broadcast(jsonString);
			System.out.println(jsonString);
		}

    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) 
    {
        //System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
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
    if (WSPort!=null)
    {
      port = Integer.valueOf(WSPort);
    }

    App A = new App(port);
    A.setReuseAddr(true);
    A.start();
    System.out.println("websocket Server started on port: " + port);

  }
}


