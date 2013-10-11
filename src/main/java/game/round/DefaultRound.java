package game.round;

import game.RenderableString;
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
	
	public void update() 
	{
		renderableStrings.clear();
		
		RenderableString text = new RenderableString("DefaultRound", 260, 200);
		renderableStrings.add(text);
	}

	public boolean isDone() 
	{
		return false;
	}
}
