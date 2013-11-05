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
	
	public String save(Session session)
	{	
		String id;
		
		do 
		{
			id = db.save(NameGenerator.getName(), session);
		} 
		while(id.equals(""));
		
		return id;
	}
	
	public Session load(String id)
	{
		return db.load(id);
	}
}
