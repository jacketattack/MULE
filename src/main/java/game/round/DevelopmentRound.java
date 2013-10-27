package game.round;

import game.Character;
import game.screen.DevelopmentScreen;
import game.screen.Screen;
import game.screen.TownScreen;
import game.TurnOrderCalculator;

import java.util.ArrayList;

import core.Keyboard;
import java.util.Collections;
import java.util.Comparator;

public class DevelopmentRound extends Round
{
	private int timer;
	private int currentCharacterIndex;
	
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
	
	public void init ()
	{
		turnOrderCalculator = new TurnOrderCalculator();
		characters = new ArrayList<>();                
		for (Character character : session.getCharacters())
		{
			characters.add(character);
		}
                Collections.sort(characters, this.turnOrderCalculator);
		currentCharacterIndex = 0;
		timers = new int[characters.size()];
		for (int i = 0; i < timers.length; i++) 
		{
			timers[i] = characters.get(i).getFood() * 175;
		}
		
		timer = timers[0];
		
		developmentScreen = new DevelopmentScreen(session);
		townScreen = new TownScreen(session);
		currentScreen = developmentScreen;
	}

	@Override
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();
		
		Character character = characters.get(currentCharacterIndex);

		handleKeyInput();
		
		currentScreen.update();
		if (currentScreen.shouldSwitch(character))
		{
			switchScreen();
		}
		
		renderables.addAll(currentScreen.getRenderables());
		renderableStrings.addAll(currentScreen.getRenderableStrings());
		renderables.add(character);

		timer--;
		if (timer <= 0)
		{
			currentCharacterIndex++;
			if (! (currentCharacterIndex >= timers.length) )
			{
				timer = timers[currentCharacterIndex];
			}
			currentScreen = developmentScreen;
		}		
	}
	
	private void handleKeyInput()
	{
		Character character = characters.get(currentCharacterIndex);
		
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

	public void click(int x, int y, boolean leftMouse)
	{
		
	}

	public boolean isDone() 
	{
		if (currentCharacterIndex >= characters.size())
		{				
			return true;				
		}
		
		return false;
	}
}
