package core;

import game.Session;

public interface Database 
{
	public String save(Session session);
	public Session load(String id);
}
