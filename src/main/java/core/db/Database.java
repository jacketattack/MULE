package core.db;

/**
 * The database interface
 */
public interface Database 
{
	/**
	 * Whether the client is connected to the database
	 * @return Whether the database is connected
	 */
    public boolean isConnected();
    
    /**
     * Whether an object exists in a specific collection
     * @param collectionId The collection to search
     * @param objectId The object desired
     * @return Whether the object exists
     */
    public boolean exists(String collectionId, String objectId);

	/**
	 * Put a DatabaseObject into the database
	 * @param collectionId The collection id
	 * @param objectId The object id
	 * @param object The DatabaseObject to store
	 */
	public void put(String collectionId, String objectId, DatabaseObject object);

	/**
	 * Get a DatabaseObject from the database
	 * @param collectionId The collection id
	 * @param objectId The object id
	 * @return The DatabaseObject fetched. Null is returned if it's not
	 * found or the database is not connected
	 */
	public DatabaseObject get(String collectionId, String objectId);
}
