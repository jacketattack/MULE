package game.round;

import game.Character;
import game.screen.DevelopmentScreen;
import game.screen.Screen;
import game.screen.TownScreen;

import java.util.ArrayList;

import core.Keyboard;

public class DevelopmentRound extends Round
{	
	private Keyboard keyboard;
	private ArrayList<Character> characters;
	
	private Screen currentScreen;
	private TownScreen townScreen;
	private DevelopmentScreen developmentScreen;
	
	public DevelopmentRound() 
	{	
		keyboard = Keyboard.getInstance();
	}
	
	public void init ()
	{
		characters = new ArrayList<Character>();
		for (Character character : session.getCharacters())
		{
			characters.add(character);
		}
		session.setCurrentCharacterIndex(0);
		
		developmentScreen = new DevelopmentScreen(session);
		townScreen = new TownScreen(session);
		currentScreen = developmentScreen;		
		
		session.setTimer(600);
	}

	@Override
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();
		
		Character character = session.getCurrentCharacter();

		handleKeyInput();
		
		currentScreen.setCharacter(character);
		currentScreen.update();
		if (currentScreen.shouldSwitch())
		{
			switchScreen();
		}
		
		renderables.addAll(currentScreen.getRenderables());
		renderableStrings.addAll(currentScreen.getRenderableStrings());
		renderables.add(character);

		session.decrementTimer();
		if (session.getTimer() <= 0)
		{
			advancePlayer();
		}		
	}
	
	private void advancePlayer()
	{
		session.setTimer(600);
		currentScreen.reset();
		session.incrementCurrentCharacterIndex();
		currentScreen = developmentScreen;
		
		System.out.println(session.toString());
	}
	
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
		if (session.getCurrentCharacterIndex() >= characters.size())
		{				
			return true;				
		}
		
		return false;
	}
}
