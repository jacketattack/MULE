package game.screen;

import game.Character;
import game.Session;

import java.util.ArrayList;

import core.render.Renderable;
import core.render.RenderableString;

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
	protected Character character;

    /**
     * Links the screen to a session and sets up different rederable things.
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
     *  checks to see if the screen should switch
     * @return  if the screen should switch
     */
	public abstract boolean shouldSwitch();

    /**
     * sets the currentCharacter to display to the screen
     * @param character
     */
	public void setCharacter(Character character)
	{
		this.character = character;
	}

    /**
     *  gets the renderables for the screen
     *
     *
     * @return    returns the ArrayList of renderables
     */
	public ArrayList<Renderable> getRenderables()
	{
		return renderables;
	}

    /**
     * Gets the string renderables
     * @return  an arraylist of renderable strings
     */
	public ArrayList<RenderableString> getRenderableStrings()
	{
		return renderableStrings;
	}
}
