package core;

import core.db.Database;
import game.Session;

public class Game 
{
	private Database db;
	
	public Game(Database db)
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
