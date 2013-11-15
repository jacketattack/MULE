package core.db;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

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
	

	public boolean isConnected()
	{
		return connected;
	}

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
				
		Set<String> keys = dbObject.toMap().keySet();
		DatabaseObject data = new DatabaseObject();
		
		for (String key : keys)
		{
			data.put(key, dbObject.get(key));
		}
		
		return data;
	}
	
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
	
	public Object get(String collectionId, String objectId, String property)
	{
		if (db == null)
			return null;
		
		DBCollection collection = db.getCollection(collectionId);
		BasicDBObject object = new BasicDBObject("id", objectId);
		DBObject dbObject = collection.findOne(object);
		
		return dbObject.get(property);
	}

	private void loadConfig() throws Exception
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
