package game.round;

import game.Callable;
import game.Character;
import game.DevelopmentScreen;
import game.Map;
import game.Plot;
import game.Screen;
import game.Session;
import game.TownScreen;

import java.util.ArrayList;

import ui.KeyboardListener;

public class DevelopmentRound extends Round implements Callable
{
	private Screen currentScreen;
	private TownScreen townScreen;
	private DevelopmentScreen developmentScreen;
	
	private ArrayList<Character> characters;
	
	private int currentCharacterIndex;
	private int timer;
	
	public DevelopmentRound(Session session) 
	{
		super(session);
		
		KeyboardListener keyboardListener = KeyboardListener.getInstance();
		keyboardListener.addListener(this);
		
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
		
		renderables.addAll(currentScreen.getRenderables());
		renderableStrings.addAll(currentScreen.getRenderableStrings());
		
		renderables.add(character);
	}

	@Override
	public void click(int x, int y, boolean leftMouse)
	{
		
	}

	@Override
	public boolean isDone() 
	{
		if (currentCharacterIndex >= characters.size())
		{				
			return true;				
		}
		return false;
	}

	@Override
	public void setSession(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
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

	public <T> void call(T object) 
	{
		if (object instanceof Integer)
		{
			int num = (Integer)object;
			
			if (num == 37)
			{
				moveCharacter(-5, 0);
			}
			else if (num == 39)
			{
				moveCharacter(5, 0);
			}

			if (num == 38)
			{
				moveCharacter(0, -5);
			}
			else if (num == 40)
			{
				moveCharacter(0, 5);
			}
		}
	}
	
	public void moveCharacter(int x, int y)
	{
		Character character = characters.get(currentCharacterIndex);
		character.applyForce(x, y);
	}
}
