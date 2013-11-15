package core.db;

import game.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoDB implements Database
{
	private static final String URI = "paulo.mongohq.com";	
	private static final int PORT = 10068;
	
	private MongoClient mongo;
	private DB db;
	
	private String user;
	private String password;
	
	private boolean connected;

	public MongoDB()
	{
		try 
		{
			loadCredentials();
			
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
     * Save a session from the database. MongoDB stores sessions as a key-value pair.
     * The session id is used as the key and the value is the binary serialization
     * of the session.
     * 
     * @param id The session id
     * @param session The session
     * @return The session
     */
	public void save(String id, Session session)
	{
		try
	    {	
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
	        objectOutputStream.writeObject(session.getSaveCopy());

	        DBCollection collection = db.getCollection("save");
	        BasicDBObject save = new BasicDBObject("id", id);
	        
	        if (exists("save", id))
	        {
	        	collection.remove(save);
	        }

	        save.append("data", byteArrayOutputStream.toByteArray());
	        
            collection.insert(save);
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
     * Load a session from the database. MongoDB stores sessions as a key-value pair.
     * The session id is used as the key and the value is the binary serialization
     * of the session.
     * 
     * @param id The session id
     * @return The session
     */
	public Session load(String id)
	{
		Session session = null;
		
	    try
	    {   
	        DBCollection collection = db.getCollection("save");
	        BasicDBObject save = new BasicDBObject("id", new BasicDBObject("$regex", id));
	        DBObject dbObject = collection.findOne(save);

            if (dbObject == null)
            {
                return null;
            }
	              
	        DBObject dbSave = collection.findOne(save);
	        byte[] bytes = (byte[]) dbSave.get("data");
	        
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
	        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
	        session = (Session) objectInputStream.readObject();	        
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    
		return session;
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
     * Whether an object exists in a specific collection
     * @param collectionId The collection to search
     * @param objectId The object desired
     * @return Whether the object exists
     */
	public boolean exists(String collectionId, String objectID)
	{
		DBCollection collection = db.getCollection(collectionId);
        BasicDBObject object = new BasicDBObject("id",objectID);
        DBObject dbObject = collection.findOne(object);
        
        if (dbObject != null)
        {
        	return true;
        }
        return false;
	}

	/**
	 * Load the database credentials
	 * @throws Exception
	 */
	private void loadCredentials() throws Exception
	{
	    File file = new File("/assets/mongo.cred");
		Scanner scanner = new Scanner(file);
		
		if (scanner.hasNext())
			user = scanner.nextLine();
		
		if (scanner.hasNext())
			password = scanner.nextLine();
		
		scanner.close();
	}
}
