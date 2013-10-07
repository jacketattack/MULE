package game.state;

import game.Session;

/**
 * GameState runs the entire in-game experience. 
 * This class will manage the game 'rounds'
 * @author grant
 */
public class GameState implements State 
{
	/** The current game session*/
	private Session session;
	
	/**
	 * The game session is given to the GameState
	 * through this constructor
	 */
	public GameState(Session session)
	{	
		this.session = session;
	}
	
	/**
	 * All game functionality starts in this method
	 */
	public void update() 
	{	
		
	}
	
	/** 
	 * Get the current game session
	 * @return the current game session
	 */
	public Session getSession()
	{
		return session;
	}
}
