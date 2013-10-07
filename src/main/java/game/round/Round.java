package game.round;

import game.Session;

/**
 * @author grant
 */
public abstract class Round 
{
	/** The current game session */
	protected Session session;
	
	/**
	 * Called once every frame. All actions that take place 
	 * during a round must be the result of this method being
	 * called.
	 */
	public abstract void update();
	
	/**
	 * Returns whether the round has been completed. 
	 * If true, the round next in line will begin
	 */
	public abstract boolean isDone();

	/**
	 * Sets the current game session. The game session
	 * gives you access to all players, characters, etc.
	 */
	public abstract void setSession(Session session);
	
	/**
	 * Returns the current game session
	 */
	public abstract Session getSession();
	
}