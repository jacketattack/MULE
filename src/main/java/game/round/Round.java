package game.round;

import game.Session;

import java.io.Serializable;
import java.util.ArrayList;

import core.render.Renderable;
import core.render.RenderableString;

public abstract class Round implements Serializable
{
	/** The current game session */
	protected Session session;
	
	protected ArrayList<Renderable> renderables;
	protected ArrayList<RenderableString> renderableStrings;
	
	public Round()
	{
		renderables = new ArrayList<Renderable>();
		renderableStrings = new ArrayList<RenderableString>();
	}
	
	/**
	 * Called once every frame. All actions that take place 
	 * during a round must be the result of this method being
	 * called.
	 */
	public abstract void update();

	/**
	 * Alert the round that an mouse click occurred
	 * @param x The x pos in pixels
	 * @param y The y pos in pixels
	 * @param leftMouse Whether the left mouse was pressed
	 */
	public abstract void click(int x, int y, boolean leftMouse);
	
	/**
	 * Returns whether the round has been completed. 
	 * If true, the round next in line will begin
	 */
	public abstract boolean isDone();
	
	public abstract void init();
	
	public ArrayList<Renderable> getRenderables()
	{
		return renderables;
	}

	public ArrayList<RenderableString> getRenderableStrings()
	{
		return renderableStrings;
	}
	
	public Session getSession()
	{
		return session;
	}
	
	public void setSession(Session session)
	{
		this.session = session;
	}
}