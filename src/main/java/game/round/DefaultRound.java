package game.round;

import game.Session;

/**
 * The default round for the GameState. 
 * Just like phantom nodes in linked lists, the 
 * purpose of this class is to avoid the need to
 * check for null.
 * 
 * @author grant
 * @author
 */
public class DefaultRound extends Round
{	
	public DefaultRound(Session session)
	{	
		super(session);
	}
	
	/**
	 * Alert the state that an mouse click occurred
	 * @param x The x pos in pixels
	 * @param y The y pos in pixels
	 * @param leftMouse Whether the left mouse was pressed
	 */
	public void click(int x, int y, boolean leftMouse)
	{
	}
	
	public void update() {}

	public boolean isDone() 
	{
		return false;
	}

	public void setSession(Session session) {}

	public Session getSession() 
	{
		return session;
	}
}
