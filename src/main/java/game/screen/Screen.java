package game.screen;

import game.Session;

import java.util.ArrayList;

import ui.render.Renderable;
import ui.render.RenderableString;

/**
 * Screen is an abstract class that represents what is displayed to the user
 *
 * @author Matt
 */
public abstract class Screen
{	
	protected Session session;	
	protected ArrayList<Renderable> renderables;
	protected ArrayList<RenderableString> renderableStrings;
	protected String playerId;

    /**
     * Links the screen to a session and sets up different renderable things.
     * @param session
     */
	public Screen(Session session)
	{
		this.session = session;
		
		renderables = new ArrayList<Renderable>();
		renderableStrings = new ArrayList<RenderableString>();
	}

    /**
     * update is called every tick of a thread that updates the screen
     */
	public abstract void update();

    /**
     * checks to see if the screen should switch
     * @return  if the screen should switch
     */
	public abstract boolean shouldSwitch();
	
	public void setPlayerId(String id)
	{
		playerId = id;
	}

    /**
     *  gets the renderables for the screen
     *
     *
     * @returns the ArrayList of renderables
     */
	public ArrayList<Renderable> getRenderables()
	{
		return renderables;
	}

    /**
     * Gets the string renderables
     * @return an ArrayList of renderable strings
     */
	public ArrayList<RenderableString> getRenderableStrings()
	{
		return renderableStrings;
	}
}
