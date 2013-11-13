package core.db;

import game.Session;

public class DB 
{
	private static DB instance;
	private Database database;
	
	public void use(Database database)
	{
		this.database = database;
	}
	
	public boolean isConnected()
	{
		if (database == null)
		{
			return false;
		}
		
		return database.isConnected();
	}

	public String save(String id, Session session)
	{
		if (database == null)
		{
			return "";
		}
		
		return database.save(id, session);
	}
	
	public Session load(String id)
	{
		if (database == null)
		{
			return null;
		}
		
		return database.load(id);
	}
	
	public boolean exists(String collectionId, String objectId)
	{
		if (database == null)
		{
			return false;
		}
		
		return database.exists(collectionId, objectId);
	}
	
	public static DB getInstance()
	{
		if (instance == null)
		{
			instance = new DB();
		}
		
		return instance;
	}
}