package core;

import game.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.text.Position.Bias;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoDB implements Database
{
	private static final String URI = "paulo.mongohq.com";	
	private static final int PORT = 10018;
	private static final String FILENAME = "/tmp/session.mule";
	
	private MongoClient mongo;
	private DB db;
	
	public MongoDB()
	{
		try 
		{
			mongo = new MongoClient(new ServerAddress(URI, PORT));
			
			db = mongo.getDB("MULE");
			boolean authenticated = db.authenticate("admin", "123456".toCharArray());
			System.out.println(authenticated);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String save(Session session)
	{
		try
	    {
			FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	        objectOutputStream.writeObject(session);
	        objectOutputStream.close();
	        fileOutputStream.close();
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(session);
	       	        
	        DBCollection coll = db.getCollection("save");
	        
	        BasicDBObject doc = new BasicDBObject("id", "grant");
	        
	        DBObject r = coll.findOne(doc);
	        
	        if (r != null && r.get("data") != null)
	        {
	        	return "";
	        }
	        
            doc.append("data", baos.toByteArray());
	        
	        coll.insert(doc);
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "grant";
	}
	
	public Session load(String id)
	{
		Session session = null;
		
	    try
	    {   
	        DBCollection coll = db.getCollection("save");
	        
	        BasicDBObject save = new BasicDBObject("id", new BasicDBObject("$regex", "grant"));
	        
	        DBObject obj = coll.findOne(save);
	        
	        System.out.println(obj.get("data"));
	        
	        byte[] f = (byte[])obj.get("data");
	        
			ByteArrayInputStream bais = new ByteArrayInputStream(f);
	        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
	        session = (Session) objectInputStream.readObject();
	        
	        System.out.println(session.toString());
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    
		return session;
	}
}
