package game.screen;

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
		int left = 280;
		int right = 350;
		int top = 140;
		int bottom = 210;
		int charX = character.getX();
		int charY = character.getY();
		
		if (charX > left && charX < right && charY>top && charY<bottom)
		{
			return true;
		}
		
		return false;
	}
}