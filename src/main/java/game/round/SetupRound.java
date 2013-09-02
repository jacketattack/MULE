package game.round;

import game.Session;

/**
 * 
 * @author grant
 * @author
 */
public class SetupRound implements Round
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
