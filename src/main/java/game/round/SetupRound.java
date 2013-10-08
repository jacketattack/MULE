package game.round;

import game.Session;

/**
 * 
 * @author grant
 * @author
 */
public class SetupRound extends Round
{	
	public void update() 
	{
		
	}

	public boolean isDone() 
	{
		return false;
	}

	public void setSession(Session session) 
	{
		this.session = session;
	}

	public Session getSession() 
	{
		return session;
	}
}
