package game.round;

import game.Session;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

import ui.render.Render;
import ui.render.StringRender;

public abstract class Round implements Serializable
{
	private static final long serialVersionUID = -6096201010802245141L;

	/** The current game session */
	protected Session session;
	
	protected CopyOnWriteArrayList<Render> renders;
	protected CopyOnWriteArrayList<StringRender> stringRenders;
	
	public Round()
	{
		renders = new CopyOnWriteArrayList<>();
		stringRenders = new CopyOnWriteArrayList<>();
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
	
	public CopyOnWriteArrayList<Render> getRenders()
	{
		return renders;
	}

	public CopyOnWriteArrayList<StringRender> getStringRenders()
	{
		return stringRenders;
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