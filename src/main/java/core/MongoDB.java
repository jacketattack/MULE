package core;

import game.Session;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.mongodb.MongoClient;

public class MongoDB implements Database
{
	private static final String URI = "mongodb://mule:CS1332_MULE@paulo.mongohq.com:10018/MULE";
	private static final String FILENAME = "/tmp/session.mule";
	
	private MongoClient client;

	public MongoDB()
	{
		try 
		{
			//client = new MongoClient(URI);
		} 
		catch (Exception e)
		{
			// alert user
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
	    }
		catch(IOException i)
	    {
			i.printStackTrace();
	    }
		return "id";
	}
	
	public Session load(String id)
	{
		Session session = null;
		
	    try
	    {
	    	FileInputStream fileInputStream = new FileInputStream(FILENAME);
	        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	        session = (Session) objectInputStream.readObject();
	        objectInputStream.close();
	        fileInputStream.close();
	    }
	    catch(Exception e)
	    {
	    	// bad
	    }
	    
		return session;
	}
}
