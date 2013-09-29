package game.round;

import game.Session;

/**
 * 
 * @author grant
 * @author
 */
public class RandomEventRound implements Round
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
		return session;
	}

	public void setSession(Session session) 
	{
		this.session = session;
	}
}
