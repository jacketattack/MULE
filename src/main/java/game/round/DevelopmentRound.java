package game.round;

import game.Character;
import game.DevelopmentScreen;
import game.Screen;
import game.Session;
import game.TownScreen;

import java.util.ArrayList;

import ui.Keyboard;

public class DevelopmentRound extends Round
{
	private Screen currentScreen;
	private TownScreen townScreen;
	private DevelopmentScreen developmentScreen;
	
	private ArrayList<Character> characters;
	
	private int currentCharacterIndex;
	private int timer;

	private Keyboard keyboard;
	
	public DevelopmentRound(Session session) 
	{
		super(session);
		
		keyboard = Keyboard.getInstance();
		
		timer = 600;
		
		//implement correct order later
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
		
		timer--;
		if (timer<=0)
		{
			currentCharacterIndex++;
			timer = 600;
		}		
		
		Character character = characters.get(currentCharacterIndex);
		
		currentScreen.update();
		if (currentScreen.shouldSwitch(character))
		{
			switchScreen();
		}

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
		
		renderables.addAll(currentScreen.getRenderables());
		renderableStrings.addAll(currentScreen.getRenderableStrings());
		
		renderables.add(character);
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
}
