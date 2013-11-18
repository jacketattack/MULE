package core.db;

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
	 * Put a DatabaseObject into the database
	 * @param collectionId The collection id
	 * @param objectId The object id
	 * @param object The DatabaseObject to store
	 */
	public void put(String collectionId, String objectId, DatabaseObject object)
	{
		if (database == null || !database.isConnected())
		{
			return;
		}
		
		database.put(collectionId, objectId, object);
	}
	
	/**
	 * Get a DatabaseObject from the database
	 * @param collectionId The collection id
	 * @param objectId The object id
	 * @return The DatabaseObject fetched. Null is returned if it's not
	 * found or the database is not connected
	 */
	public DatabaseObject get(String collectionId, String objectId)
	{
		if (database == null || !database.isConnected())
		{
			return null;
		}

		return database.get(collectionId, objectId);
	}

    /**
     * Whether an object exists in a specific collection
     * @param collectionId The collection to search
     * @param objectId The object desired
     * @return Whether the object exists
     */
	public boolean exists(String collectionId, String objectId)
	{
		if (database == null || !database.isConnected())
		{
			return false;
		}
		
		return database.exists(collectionId, objectId);
	}

    /**
     * Returns the DB instance
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