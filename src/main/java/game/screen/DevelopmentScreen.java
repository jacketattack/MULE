package game.screen;

import game.Follower;
import game.Mule;
import game.Player;
import game.Plot;
import game.Session;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ui.Window;
import core.Keyboard;

/**
 * The Development Screen is the screen that shows all the tiles!  A character can walk around the screen and enter town
 * @author Matt
 */

public class DevelopmentScreen extends Screen 
{	
	private int plotTimer;
	private ArrayList<Mule> badMules;
	
	private Keyboard keyboard;
	
	public DevelopmentScreen(Session session) 
	{
		super(session);
		
		plotTimer = 15;
		badMules = new ArrayList<Mule>();
		keyboard = Keyboard.getInstance();

	}

    /**
     * Update refreshes the screen and draws the different entities
     */
	public void update() 
	{
		boolean onOwnedPlot = false;
		
		renderables.clear();
		renderableStrings.clear();
		
		for (int a = 0; a < 5; a++)
		{
			for (int b = 0; b < 9; b++)
			{
				Plot plot = session.getPlot(a, b);
				renderables.add(plot);
				
				if (plot.inBounds(session.getPlayerX(playerId), session.getPlayerY(playerId)) && session.isPlotOwnedByPlayer(playerId, plot))
				{
					onOwnedPlot = true;
				}
			}
		}	

		if (!badMules.isEmpty())
		{
			renderables.addAll(badMules);
			for(Mule baddie: badMules)
			{
				baddie.update();
			}
		}
		
		if (keyboard.isDown(KeyEvent.VK_SPACE) && plotTimer <= 0)
		{
			if (onOwnedPlot)
			{
				// change plot
			}
			else
			{
				Follower follower = session.getPlayerFollower(playerId);
				if (follower != null && follower instanceof Mule)
				{
					((Mule)follower).runAway();
					badMules.add((Mule)follower);
					session.setPlayerFollower(playerId, null);
					plotTimer = 15;
				}
			}
			
			plotTimer = 15;
		}
		
		if (plotTimer > 0)
		{
			plotTimer--;
		}
	}

    /**
     * Checks to see if the player should switch screens
     * @return the boolean whether or not the player is at an exit
     */
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