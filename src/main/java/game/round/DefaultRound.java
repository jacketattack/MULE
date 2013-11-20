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

    /**
     * Refreshes the screen with updated variables and new objects that need
     * to be drawn. Gets called in conjunction with the frame rate.
     */
	public void update() 
	{
		stringRenders.clear();
		
		StringRender text = new StringRender("DefaultRound", 260, 200);
		stringRenders.add(text);
	}

    /**
     * If true indicates the the game flow should advance to the next round
     * @return whether the round has finished.
     */
	public boolean isDone() 
	{
		return false;
	}

    /**
     * Method that should be implemented to set up
     * instance variables and other requirements for each round
     */
	public void init()
	{
		
	}
}
