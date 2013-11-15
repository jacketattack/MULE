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
	
	public void put(String collectionId, String objectId, DatabaseObject object);
	public DatabaseObject get(String collectionId, String objectId);
}
