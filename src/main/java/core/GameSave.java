package core;

import game.Session;
import core.db.Database;

public class GameSave 
{
	private Database db;
	
	public GameSave(Database db)
	{
		this.db = db;
	}
	
	public void save(Session session, String id)
	{	
		db.save(id,session);
	}
	
	public Session load(String id)
	{
		return db.load(id);
	}
}
