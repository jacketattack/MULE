package game.state;

import game.Session;


/**
 * 
 * @author grant
 * @author
 */
public class GameState implements State 
{
	private Session session;
	
	public GameState(Session session)
	{	
		this.session = session;
	}
	
	public void update() 
	{	
		
	}
	
	public Session getSession()
	{
		return session;
	}
}
