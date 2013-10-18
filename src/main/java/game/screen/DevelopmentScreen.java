package game.screen;

import ui.Window;
import game.Character;
import game.Map;
import game.Plot;
import game.Session;

public class DevelopmentScreen extends Screen 
{	
	public DevelopmentScreen(Session session) 
	{
		super(session);
	}

	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();
		
		for (int a = 0; a < 5; a++)
		{
			for (int b = 0; b < 9; b++)
			{
				Map map = session.getMap();
				Plot plot = map.getPlot(a, b);
				renderables.add(plot);
			}
		}	
	}

	public boolean shouldSwitch(Character character) 
	{
		if (character.getX() > Plot.SIZE * 4 - Character.WIDTH && character.getX() < Plot.SIZE * 4 + 10 && character.getY() < Plot.SIZE * 3 && character.getY() > Plot.SIZE * 2)
		{
			character.setX(Character.WIDTH * 3);
			character.setY(Plot.SIZE * 2 + Plot.SIZE / 2 - Character.HEIGHT / 2);
			return true;
		}
		
		if (character.getX() > Plot.SIZE * 5 - 10 && character.getX() < Plot.SIZE * 5 && character.getY() < Plot.SIZE * 3 && character.getY() > Plot.SIZE * 2)
		{
			character.setX(Window.WIDTH - Character.WIDTH * 2);
			character.setY(Plot.SIZE * 2 + Plot.SIZE / 2 - Character.HEIGHT / 2);
			return true;
		}
		
		if (character.getX() > Plot.SIZE * 4 + 10 && character.getX() < Plot.SIZE * 5 - 10 && character.getY() < Plot.SIZE * 2 + 10 - Character.HEIGHT && character.getY() > Plot.SIZE * 2 - Character.HEIGHT)
		{
			character.setX(Window.WIDTH / 2 - Character.WIDTH / 2);
			character.setY(10);
			return true;
		}

		if (character.getX() > Plot.SIZE * 4 + 10 && character.getX() < Plot.SIZE * 5 - 10 && character.getY() < Plot.SIZE * 3 && character.getY() > Plot.SIZE * 3 - 10)
		{
			character.setX(Window.WIDTH / 2 - Character.WIDTH / 2);
			character.setY(Window.HEIGHT - Character.HEIGHT - 100);
			return true;
		}
			
		return false;
	}
}