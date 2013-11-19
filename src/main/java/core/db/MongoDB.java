package core.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoDB implements Database
{
	/** The database URI */
	private static final String URI = "paulo.mongohq.com";	
	/** The database PORT */
	private static final int PORT = 10068;

	/** The database client */
	private MongoClient mongo;
	/** The mongo database */
	private DB db;

	/** User name */
	private String user;
	/** User password */
	private String password;
	
	/** Whether the client is connected to the database */
	private boolean connected;

	public MongoDB()
	{
		// load the credentials, create
		// a connection to the database, and
		// authenticate the session
		try 
		{
			loadConfig();
			
			mongo = new MongoClient(new ServerAddress(URI, PORT));
			
			db = mongo.getDB("MULE");
			boolean authenticated = db.authenticate(user, password.toCharArray());
			
			if (!authenticated)
			{
				connected = false;
			}
			else
			{
				connected = true;
			}
		} 
		catch (Exception e)
		{
			connected = false;
		}
	}
	
    /**
     * Whether the client is connected to the database
     * @return Whether the database is connected
     */
	public boolean isConnected()
	{
		return connected;
	}

	/**
	 * Put a DatabaseObject into the database
	 * @param collectionId The collection id
	 * @param objectId The object id
	 * @param object The DatabaseObject to store
	 */
	public void put(String collectionId, String objectId, DatabaseObject object)
	{
		if (db == null)
			return;
		
		DBCollection collection = db.getCollection(collectionId);
		BasicDBObject save = new BasicDBObject("id", new BasicDBObject("$regex", objectId));
		
		if (exists(collectionId, objectId))
		{
			collection.remove(save);
		}
		
		for (String key : object.getEntries())
		{
			Object value = object.get(key);
			save.put(key, value);
		}
		
		collection.insert(save);
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
		if (db == null)
			return null;
		
		DBCollection collection = db.getCollection(collectionId);
		BasicDBObject save = new BasicDBObject("id", new BasicDBObject("$regex", objectId));
		DBObject dbObject = collection.findOne(save);
		
		if (dbObject == null)
		{
			return null;
		}
				
		@SuppressWarnings("unchecked")
		Set<String> keys = dbObject.toMap().keySet();
		DatabaseObject data = new DatabaseObject();
		
		for (String key : keys)
		{
			data.put(key, dbObject.get(key));
		}
		
		return data;
	}

    /**
     * Whether an object exists in a specific collection
     * @param collectionId The collection to search
     * @param objectId The object desired
     * @return Whether the object exists
     */
	public boolean exists(String collectionId, String objectId)
	{
		if (db == null)
			return false;
		
		DBCollection collection = db.getCollection(collectionId);
        BasicDBObject object = new BasicDBObject("id", objectId);
        DBObject dbObject = collection.findOne(object);
        
        if (dbObject != null)
        {
        	return true;
        }
        return false;
	}

	/**
	 * Load the database credentials from mongo.cred
	 * @throws Exception
	 */
	private void loadConfig() throws Exception
	{
		InputStream input = this.getClass().getResourceAsStream("/assets/mongo.cred");
		
		if (input != null)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			user = reader.readLine();
			password = reader.readLine();
		}
	}
}
