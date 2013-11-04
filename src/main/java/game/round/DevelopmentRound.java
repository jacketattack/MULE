package game.round;

import game.Character;
import game.screen.DevelopmentScreen;
import game.screen.Screen;
import game.screen.TownScreen;
import game.TurnOrderCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import core.Keyboard;
import core.render.RenderableString;

/**
 * The DevelopmentRound is the part of the game where the users can buy land and interact with the land.
 *
 * @author Matt
 */

public class DevelopmentRound extends Round
{	
	private Keyboard keyboard;
	private ArrayList<Character> characters;
	private int[] timers;
	
	private Screen currentScreen;
	private TownScreen townScreen;
	private DevelopmentScreen developmentScreen;

    private Comparator<Character> turnOrderCalculator; 
	
	public DevelopmentRound() 
	{	
		keyboard = Keyboard.getInstance();
	}

    /**
     * The init method sets up the current round for play!
     */
	public void init ()
	{
		turnOrderCalculator = new TurnOrderCalculator();
		characters = new ArrayList<>();                
		for (Character character : session.getCharacters())
		{
			characters.add(character);
		}
		
		session.setCurrentCharacterIndex(0);
        Collections.sort(characters, this.turnOrderCalculator);
                
		timers = new int[characters.size()];
		for (int i = 0; i < timers.length; i++) 
		{
			timers[i] = characters.get(i).getFood() * 175;
		}	
		
		developmentScreen = new DevelopmentScreen(session);
		townScreen = new TownScreen(session);
		currentScreen = developmentScreen;		
		
		session.setTimer(timers[session.getCurrentCharacterIndex()]);
	}

    /**
     * The update method is called everyTime the thread ticks.  It handles realtime updates of the game
     */
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();
		
		Character character = session.getCurrentCharacter();
		character.update();

		handleKeyInput();
		
		currentScreen.setCharacter(character);
		currentScreen.update();
		if (currentScreen.shouldSwitch())
		{
			switchScreen();
		}
		
		RenderableString characterName = new RenderableString(character.getName(), 500, 400);
		renderableStrings.add(characterName);
		
		renderables.addAll(currentScreen.getRenderables());
		renderableStrings.addAll(currentScreen.getRenderableStrings());
		renderables.add(character);
		
		if (character.getFollower() != null)
		{
			renderables.add(character.getFollower());
		}

		session.decrementTimer();
		if (session.getTimer() <= 0)
		{
			advancePlayer();
		}		
	}

    /**
     * Advances the current player index to the next player while siwtching to the correct screen!
     */
	private void advancePlayer()
	{
		session.setTimer(timers[session.getCurrentCharacterIndex()]);
		session.incrementCurrentCharacterIndex();
		currentScreen = developmentScreen;
		
		System.out.println(session.toString());
	}

    /**
     * handleKeyInput move the character based on input
     */
	private void handleKeyInput()
	{
		Character character = session.getCurrentCharacter();
		
		if (keyboard.isDown(37))
		{
			character.applyForce(-Character.MOVEMENT_SPEED, 0);
		} 
		else if (keyboard.isDown(39))
		{
			character.applyForce(Character.MOVEMENT_SPEED, 0);
		}

		if (keyboard.isDown(38))
		{
			character.applyForce(0, -Character.MOVEMENT_SPEED);
		} 
		else if (keyboard.isDown(40))
		{
			character.applyForce(0, Character.MOVEMENT_SPEED);
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
		if (session.getCurrentCharacterIndex() >= characters.size())
		{				
			return true;				
		}
		
		return false;
	}
}
