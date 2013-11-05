package game.screen;

import game.Player;
import game.Plot;
import game.Session;
import ui.Window;

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
				Plot plot = session.getPlot(a, b);
				renderables.add(plot);
			}
		}	
	}

	public boolean shouldSwitch() 
	{
		boolean switching = false;
		
		if (session.getPlayerX(playerId) > Plot.SIZE * 4 - Player.WIDTH && session.getPlayerX(playerId) < Plot.SIZE * 4 + 10 && session.getPlayerY(playerId) < Plot.SIZE * 3 && session.getPlayerY(playerId) > Plot.SIZE * 2)
		{
			session.setPlayerX(playerId, Player.WIDTH * 3);
			session.setPlayerY(playerId, Plot.SIZE * 2 + Plot.SIZE / 2 - Player.HEIGHT / 2);
			switching = true;
		}
		
		if (session.getPlayerX(playerId) > Plot.SIZE * 5 - 10 && session.getPlayerX(playerId) < Plot.SIZE * 5 && session.getPlayerY(playerId) < Plot.SIZE * 3 && session.getPlayerY(playerId) > Plot.SIZE * 2)
		{
			session.setPlayerX(playerId, Window.WIDTH - Player.WIDTH * 2);
			session.setPlayerY(playerId, Plot.SIZE * 2 + Plot.SIZE / 2 - Player.HEIGHT / 2);
			switching = true;
		}
		
		if (session.getPlayerX(playerId) > Plot.SIZE * 4 + 10 && session.getPlayerX(playerId) < Plot.SIZE * 5 - 10 && session.getPlayerY(playerId) < Plot.SIZE * 2 + 10 - Player.HEIGHT && session.getPlayerY(playerId) > Plot.SIZE * 2 - Player.HEIGHT)
		{
			session.setPlayerX(playerId, Window.WIDTH / 2 - Player.WIDTH / 2);
			session.setPlayerY(playerId, 10);
			switching = true;
		}

		if (session.getPlayerX(playerId) > Plot.SIZE * 4 + 10 && session.getPlayerX(playerId) < Plot.SIZE * 5 - 10 && session.getPlayerY(playerId) < Plot.SIZE * 3 && session.getPlayerY(playerId) > Plot.SIZE * 3 - 10)
		{
			session.setPlayerX(playerId, Window.WIDTH / 2 - Player.WIDTH / 2);
			session.setPlayerY(playerId, Window.HEIGHT - Player.HEIGHT - 100);
			switching = true;
		}
		
		return switching;
	}
}