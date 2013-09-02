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
public class DefaultRound implements Round 
{
	private Session session;
	
	public void update() 
	{
	}
	
	public boolean isDone() 
	{
		return false;
	}

	public Session getSession() 
	{
		return new Session();
	}

	public void setSession(Session session) 
	{
		this.session = session;
	}
}
