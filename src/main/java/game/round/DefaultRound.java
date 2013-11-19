package game.round;

import ui.render.StringRender;

/**
 * The default round for the GameState. 
 * Just like phantom nodes in linked lists, the 
 * purpose of this class is to avoid the need to
 * check for null.
 * @author grant
 *
 */
public class DefaultRound extends Round
{	
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
		stringRenders.clear();
		
		StringRender text = new StringRender("DefaultRound", 260, 200);
		stringRenders.add(text);
	}

	public boolean isDone() 
	{
		return false;
	}
	
	public void init()
	{
		
	}
}
