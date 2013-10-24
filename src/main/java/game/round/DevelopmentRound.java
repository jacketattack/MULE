package game.round;

import game.Character;
import game.screen.DevelopmentScreen;
import game.screen.Screen;
import game.screen.TownScreen;

import java.util.ArrayList;

import core.Keyboard;

public class DevelopmentRound extends Round
{
	private int timer;
	private int currentCharacterIndex;
	
	private Keyboard keyboard;
	private ArrayList<Character> characters;
	
	private Screen currentScreen;
	private TownScreen townScreen;
	private DevelopmentScreen developmentScreen;
	
	public DevelopmentRound() 
	{	
		keyboard = Keyboard.getInstance();
		timer = 600;
	}
	
	public void init ()
	{
		characters = new ArrayList<Character>();
		for (Character character : session.getCharacters())
		{
			characters.add(character);
		}
		currentCharacterIndex = 0;
		
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
		
		currentScreen.setCharacter(character);
		currentScreen.update();
		if (currentScreen.shouldSwitch())
		{
			switchScreen();
		}
		if (currentScreen.shouldEndTimer())
		{
			advancePlayer();
			return;
		}
		
		renderables.addAll(currentScreen.getRenderables());
		renderableStrings.addAll(currentScreen.getRenderableStrings());
		renderables.add(character);

		timer--;
		if (timer <= 0)
		{
			advancePlayer();
		}		
	}
	
	private void advancePlayer()
	{
		Character character = characters.get(currentCharacterIndex);
		
		int bonus = (int)(session.getRoundAt() * (Math.random() * timer));
		if (bonus > 250)
		{
			bonus = 250;
		}
		
		character.setMoney(character.getMoney() + bonus);
		
		timer = 600;
		currentScreen.reset();
		currentCharacterIndex++;
		currentScreen = developmentScreen;
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
