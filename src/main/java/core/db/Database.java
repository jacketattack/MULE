package core.db;

import game.Session;

public interface Database 
{
	public String save(String id, Session session);
	public Session load(String id);
}
