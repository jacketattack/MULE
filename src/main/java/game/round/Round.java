package game.round;

import game.Session;

public interface Round 
{
	public void update();
	public boolean isDone();
	
	public Session getSession();
	public void setSession(Session session);
}