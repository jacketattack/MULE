package game.round;

import game.Player;
import game.TurnOrderCalculator;
import game.screen.DevelopmentScreen;
import game.screen.Screen;
import game.screen.TownScreen;

import java.util.ArrayList;
import java.util.Comparator;

import ui.Window;
import ui.render.StringRender;
import core.Keyboard;

/**
 * The DevelopmentRound is the part of the game where the users can buy land and interact with the land.
 *
 * @author Matt
 */
public class DevelopmentRound extends Round
{	
	private static final long serialVersionUID = -3325329048415985552L;

	private Keyboard keyboard;

	private boolean done;
	private Screen currentScreen;
	private TownScreen townScreen;
	private DevelopmentScreen developmentScreen;
    private Comparator<Player> turnOrderCalculator; 
    
    private ArrayList<String> playerIds;
    
	private int[] timers;
	
	public DevelopmentRound() 
	{	
		keyboard = Keyboard.getInstance();
		playerIds = new ArrayList<String>();
	}

    /**
     * The init method sets up the current round for play!
     */
	public void init ()
	{	
		done = false;
		turnOrderCalculator = new TurnOrderCalculator();

		playerIds = new ArrayList<String>();
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
     * The update method is called everyTime the thread ticks.  It handles realtime updates of the game
     */
	public void update() 
	{
		renders.clear();
		stringRenders.clear();

		String playerId = session.getCurrentPlayerId();
		session.updatePlayer(playerId);
		
		handleKeyInput();
		
		currentScreen.setPlayerId(playerId);
		currentScreen.update();
		if (currentScreen.shouldSwitch())
		{
			switchScreen();
		}
		
		StringRender characterName = new StringRender(session.getPlayerName(playerId), 500, 400);
		stringRenders.add(characterName);
		
		renders.addAll(currentScreen.getRenders());
		stringRenders.addAll(currentScreen.getStringRenders());
		renders.add(session.getPlayerRender(playerId));
		
		if (session.getPlayerFollower(playerId) != null)
		{
			renders.add(session.getPlayerFollower(playerId).getRender());
		}

		session.decrementTimer();
		
		if (session.getTimer() <= 0)
		{	
			currentScreen = townScreen;
			session.setTimer(timers[playerIds.indexOf(session.getCurrentPlayerId())]);
			boolean newRound = session.advancePlayer();	
			
			if (newRound)
			{
				done = true;
			}
		}		
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
