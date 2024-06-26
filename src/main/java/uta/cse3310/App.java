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
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

public class App extends WebSocketServer {

    //List of players
    private HashMap<String, UserEvent> Players = new HashMap<>();


    //Wait Games
    private ArrayList<String> Wait_game_2;
    private ArrayList<String> Wait_game_3;
    private ArrayList<String> Wait_game_4;

    
    //Chat
    private ChatBox Chat;

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
    public static int TEST_GRID;
    public static String VERSION;
    
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
        Game game1 = new Game(0,TEST_GRID);
        Game game2 = new Game(0,TEST_GRID);
        Game game3 = new Game(0,TEST_GRID);
        Game game4 = new Game(0,TEST_GRID);
        Game game5 = new Game(0,TEST_GRID);
        
        Games.add(game1);
        Games.add(game2);
        Games.add(game3);
        Games.add(game4);
        Games.add(game5);

        // Initialize chatbox
        Chat = new ChatBox();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
		Gson gson = new Gson();
		//Send out the server id to each user
		conn.send(gson.toJson(ser));
		conn.send(gson.toJson(TEST_GRID));
		conn.send(gson.toJson(VERSION));
		
		System.out.println(gson.toJson(ser));
		ser+=1;
		
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn + " has closed");
        String user = conn.getAttachment();
        System.out.println(user);
        leaderboard.remove(user);
		//Update wait list
		if (!Players.isEmpty())
		{
			if (Players.get(user).GameType == 2)
			{
				Wait_game_2.remove(user);
				Selected_Game = Wait_game_2;
				
			}
			else if (Players.get(user).GameType == 3)
			{
				Wait_game_3.remove(user);
				Selected_Game = Wait_game_3;
			}
			else
			{
				Wait_game_4.remove(user);
				Selected_Game = Wait_game_4;
			}

		    Players.remove(user);
		}

		Gson gson = new GsonBuilder().create();
		String jsonString = gson.toJson(leaderboard);
		broadcast(jsonString);  
		jsonString = gson.toJson(Selected_Game);
		broadcast(jsonString);        
    }

    @Override
    public void onMessage(WebSocket conn, String message) 
    {
        // Deserialize the JSON message into a UserEvent object
        Gson gson = new GsonBuilder().create();
        
        if (message.contains("Message")) {
            System.out.println("Received chat: " + message);
            Message M = gson.fromJson(message, Message.class);
            Chat.add(M);

            // TBH we can just send the message back to everyone
            if (Chat.display_message().peek() == M)
                broadcast(gson.toJson(M));
            return;
        }
        
        UserEvent U = gson.fromJson(message, UserEvent.class);
        if (U.ready == -2)
        {
        	if (!Players.containsKey(U.Handle) && U.Handle.length() <= 10)
        	{
        		conn.send(gson.toJson("approved"));
        	}
        	else
        	{
        		conn.send(gson.toJson("disapproved"));
        	}
        	return;
        }
        //username approved
        else if (U.ready == -1)
        {
        	System.out.println("This is the player: " + U.Uid);
        	//Assign the color
        	U.color = color.pickColor();
        	
        	//Add the person to the leaderboard
        	if (!leaderboard.LB.containsKey(U.Handle))
        	{
        		leaderboard.add(U.Handle,0);
        		Players.put(U.Handle,U);
        	}
        	//Add it to all players list
        	
        	
        	conn.setAttachment(U.Handle);
        	
			if (Players.get(U.Handle).GameType == 2)
			{
				Wait_game_2.remove(U.Handle);
				Selected_Game = Wait_game_2;
				
			}
			else if (Players.get(U.Handle).GameType == 3)
			{
				Wait_game_3.remove(U.Handle);
				Selected_Game = Wait_game_3;
			}
			else
			{
				Wait_game_4.remove(U.Handle);
				Selected_Game = Wait_game_4;
			}

            
            //Send them the chatbox
            System.out.println(gson.toJson(Chat));
            conn.send(gson.toJson(Chat));
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
        			g.test_grid = TEST_GRID;

        			
        			//Remove the players from wait list if there is a game
					for (int i = 0; i < U.GameType; i++)
					{
						//Add them to the Game
						g.ID.add(Players.get(Selected_Game.get(1)).Handle);
						
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
        

		//moving back to lobby
		else if (U.ready == 2)
		{
        	Game game = new Game(0,TEST_GRID);
        	Games.remove(Players.get(U.Handle).game);
        	Players.get(U.Handle).game = null;
        	Games.add(game);
        	Players.get(U.Handle).timer_done = false;
        	Players.get(U.Handle).ready = -1;
        	Players.get(U.Handle).letters.clear();			
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
				if (Players.get(U.Handle).game.isValidWord(letters,U.Handle,leaderboard))
				{	
					//Update the letters to be selected							
					Players.get(U.Handle).letters = letters;
					
					//update leaderboard
					//leaderboard.add(U.Handle,0);				
				}
				else
				{		
					//If it isnt a valid word then clear out selected letters
					Players.get(U.Handle).letters.clear();
				}
			}
			
			//Check if game is over
			ArrayList<String> winners = new ArrayList<>();
			winners = Players.get(U.Handle).game.GameOver(U.timer_done,leaderboard);
			if (winners.size()!= 0 )
			{
				Summary summary = new Summary();
				summary.Create_Summary(Players.get(U.Handle).game.ID,winners);
				
				jsonString = gson.toJson(summary);
				System.out.println("These are the winners: "+winners);
				System.out.println("These are the ID: "+Players.get(U.Handle).game.ID);
				System.out.println("This is the summary: "+summary.Summary_list);
				broadcast(jsonString);		
				summary.Clear_Summary();
				Players.get(U.Handle).game.ID.clear();
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
        // chat.add_message(message);
/* 
        String jsonMsg = gson.toJson(chat);
        broadcast(message);
        System.out.println(jsonMsg); */
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
    String t_grid = System.getenv("TEST_GRID");
	String version = System.getenv("VERSION");
    VERSION = version;
    if (t_grid == null)
    {
    	TEST_GRID = 20;
    }
    else
    {
    	TEST_GRID = Integer.valueOf(t_grid);
    }
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


