package core.db;

import game.Session;

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
     * Save a session to the database
     * @param id The session id
     * @param session The session
     */
    public void save(String id, Session session);
	
    /**
     * Load a session from the database
     * @param id The session id
     * @return The session
     */
    public Session load(String id);
}
