package core.db;

import game.Session;

/**
 * DB manages the database interaction. The database implementation
 * is provided via dependency injection.
 */
public class DB 
{
	private static DB instance;
	
	/** The database implementation */
	private Database database;
	
	/**
	 * Use a specified database implementation for all requests
	 * @param database The database
	 */
	public void use(Database database)
	{
		this.database = database;
	}

	/**
	 * Whether the client is connected to the database
	 * @return Whether the database is connected
	 */
	public boolean isConnected()
	{
		if (database == null)
		{
			return false;
		}
		
		return database.isConnected();
	}
	
	/**
     * Save a session to the database
     * @param id The session id
     * @param session The session
     */
	public void save(String id, Session session)
	{
		if (database == null)
		{
			return;
		}
		
		database.save(id, session);
	}
	
	/**
     * Load a session from the database
     * @param id The session id
     * @return The session
     */
	public Session load(String id)
	{
		if (database == null)
		{
			return null;
		}
		
		return database.load(id);
	}

    /**
     * Whether an object exists in a specific collection
     * @param collectionId The collection to search
     * @param objectId The object desired
     * @return Whether the object exists
     */
	public boolean exists(String collectionId, String objectId)
	{
		if (database == null)
		{
			return false;
		}
		
		return database.exists(collectionId, objectId);
	}

	
	/**
	 * Returns the DB instance. 
	 *
	 * @return The DB instance
	 */
	public static DB getInstance()
	{
		if (instance == null)
		{
			instance = new DB();
		}
		
		return instance;
	}
}