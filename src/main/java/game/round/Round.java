package game.round;

import game.Renderable;
import game.RenderableString;
import game.Session;

import java.util.ArrayList;

/**
 * @author grant
 */
public abstract class Round 
{
	/** The current game session */
	protected Session session;
	
	protected ArrayList<Renderable> renderables;
	protected ArrayList<RenderableString> renderableStrings;
	
	public Round(Session session)
	{
		this.session = session;
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
	
	public ArrayList<Renderable> getRenderables()
	{
		return renderables;
	}

	public ArrayList<RenderableString> getRenderableStrings()
	{
		return renderableStrings;
	}
}