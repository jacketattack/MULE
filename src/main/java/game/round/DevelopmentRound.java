package game.round;

import game.Follower;
import game.Mule;
import game.Player;
import game.TurnOrderCalculator;
import game.screen.DevelopmentScreen;
import game.screen.Screen;
import game.screen.TownScreen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

import ui.Window;
import ui.render.Render;
import ui.render.StringRender;
import core.Keyboard;

/**
 * The DevelopmentRound is the part of the game where 
 * the users can buy land and interact with the land
 */
public class DevelopmentRound extends Round
{	
	private Keyboard keyboard;

	private boolean done;
	private Screen currentScreen;
	private TownScreen townScreen;
	private DevelopmentScreen developmentScreen;

    private ArrayList<String> playerIds;
    
    private Render infoBar;
    
	private int[] timers;
	private int startTimer;

    /**
     * Default constructor for DevelopmentRound.
     */
	public DevelopmentRound() 
	{	
		keyboard = Keyboard.getInstance();
		playerIds = new ArrayList<>();

		infoBar = new Render();
		infoBar.x = 0;
		infoBar.y = 350;
		infoBar.addImage("assets/images/infoBar.png");
	}

    /**
     * The init method sets up the current round for play!
     */
	public void init ()
	{	
		startTimer = 90;
		
		done = false;
        Comparator<Player> turnOrderCalculator = new TurnOrderCalculator();

		playerIds = new ArrayList<>();
		for (String id : session.getPlayerIds())
		{
			session.setPlayerX(id, Window.WIDTH / 2);
			session.setPlayerY(id, Window.HEIGHT / 2 - 50);
			playerIds.add(id);
		}
		
		session.setCurrentPlayer(playerIds.get(0));
		session.sortPlayers(turnOrderCalculator);
                
		timers = new int[playerIds.size()];
		for (int i = 0; i < timers.length; i++) 
		{
			String id = playerIds.get(i);
			timers[i] = session.getPlayerFood(id) * 175;
		}	
		
		developmentScreen = new DevelopmentScreen(session);
		townScreen = new TownScreen(session);
		currentScreen = townScreen;	

		session.setTimer(timers[playerIds.indexOf(session.getCurrentPlayerId())]);
	}

    /**
     * The update method is called every time the thread ticks
     */
	public void update() 
	{
		renders.clear();
		stringRenders.clear();
		
		String playerId = session.getCurrentPlayerId();
		
		if (startTimer > 0)
		{
			startTimer--;
			if (startTimer > 60)
			{
				StringRender string = new StringRender("3", 315, 150, Color.BLACK);
				stringRenders.add(string);
			}
			else if (startTimer > 30)
			{
				StringRender string = new StringRender("2", 315, 150, Color.BLACK);
				stringRenders.add(string);
			}
			else
			{
				StringRender string = new StringRender("1", 315, 150, Color.BLACK);
				stringRenders.add(string);
			}
		}
		else
		{
			session.updatePlayer(playerId);
			handleKeyInput();
			session.decrementTimer();
		}
		
		currentScreen.setPlayerId(playerId);
		currentScreen.update();
		if (currentScreen.shouldSwitch())
		{
			switchScreen();
		}
		
		if (session.getTimer() <= 0)
		{	
			Follower follower = session.getPlayerFollower(playerId);
			if (follower != null && follower instanceof Mule)
			{
				session.setPlayerFollower(playerId, null);
			}
			
			startTimer = 90;
			currentScreen = townScreen;
			session.setTimer(timers[playerIds.indexOf(session.getCurrentPlayerId())]);
			boolean newRound = session.advancePlayer();	
			
			if (newRound)
			{
				done = true;
			}
		}		
		
		renders.addAll(currentScreen.getRenders());
		stringRenders.addAll(currentScreen.getStringRenders());
		renders.add(session.getPlayerRender(playerId));
		
		if (session.getPlayerFollowerRender(playerId) != null)
		{
			renders.add(session.getPlayerFollowerRender(playerId));
		}
		
		String id = session.getCurrentPlayerId();
		
		//prompt.text = session.getPlayerName(id) + " please select a plot";
		//stringRenders.add(prompt);
		
		StringRender name = new StringRender(session.getPlayerName(id), 20, 380, Color.WHITE);
		stringRenders.add(name);
		
		StringRender money = new StringRender("$" + session.getPlayerMoney(id), 20, 400, Color.WHITE);
		stringRenders.add(money);

		StringRender ore = new StringRender("" + session.getPlayerOre(id), 140, 382, Color.WHITE);
		stringRenders.add(ore);

		StringRender food = new StringRender("" + session.getPlayerFood(id), 140, 402, Color.WHITE);
		stringRenders.add(food);

		StringRender crystite = new StringRender("" + session.getPlayerCrystite(id), 180, 382, Color.WHITE);
		stringRenders.add(crystite);

		StringRender energy = new StringRender("" + session.getPlayerEnergy(id), 180, 402, Color.WHITE);
		stringRenders.add(energy);
		
		StringRender timer = new StringRender("" + session.getTimer() / 100, 390, 390, Color.WHITE);
		stringRenders.add(timer);

		renders.add(infoBar);
	}
	
    /**
     * handleKeyInput move the character based on input
     */
	private void handleKeyInput()
	{
		String playerId = session.getCurrentPlayerId();

		if (keyboard.isDown(37))
		{
			session.applyForceToPlayer(playerId, -Player.MOVEMENT_SPEED, 0);
		} 
		else if (keyboard.isDown(39))
		{
			session.applyForceToPlayer(playerId, Player.MOVEMENT_SPEED, 0);
		}

		if (keyboard.isDown(38))
		{
			session.applyForceToPlayer(playerId, 0, -Player.MOVEMENT_SPEED);
		} 
		else if (keyboard.isDown(40))
		{
			session.applyForceToPlayer(playerId, 0, Player.MOVEMENT_SPEED);
		}
	}

    /**
     * Switches the current screen to  the other screen associated with the round.
     */
	private void switchScreen()
	{
		if (currentScreen instanceof TownScreen)
		{
			currentScreen = developmentScreen;
		} 
		else 
		{
			currentScreen = townScreen;
		}
	}

    /**
     * Clicking on a space serves no purpose at the moment but can be implemented easily!
     * @param x The x pos in pixels
     * @param y The y pos in pixels
     * @param leftMouse Whether the left mouse was pressed
     */
	public void click(int x, int y, boolean leftMouse)
	{
		
	}

    /**
     * This method checks to see if we have looped through all the players!
     * @return  boolean done, whether or not the current round is done
     */
	public boolean isDone() 
	{
		return done;
	}
}
