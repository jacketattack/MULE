package game.round;

import game.Session;

/**
 * 
 * @author grant
 * @author
 */
public interface Round 
{
	/**
	 * Called once every frame. All actions that take place 
	 * during a round must be the result of this method being
	 * called.
	 */
	public void update();
	
	/**
	 * Returns whether the round has been completed. 
	 * If true, the round next in line will begin
	 */
	public boolean isDone();
	
	/**
	 * Returns the current game session
	 */
	public Session getSession();
	
	/**
	 * Sets the current game session. The game session
	 * gives you access to all players, characters, etc.
	 */
	public void setSession(Session session);
}