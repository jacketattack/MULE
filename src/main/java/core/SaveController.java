package core;

import game.Session;

public class SaveController 
{
	private Database db;
	
	public SaveController(Database db)
	{
		this.db = db;
	}
	
	public String save(Session session)
	{
		return db.save(session);
	}
	
	public Session load(String id)
	{
		return db.load(id);
	}
}
