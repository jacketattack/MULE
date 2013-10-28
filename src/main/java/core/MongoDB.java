package core;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class MongoDB 
{
	private static final String URI = "mongodb://mule:CS1332_MULE@paulo.mongohq.com/MULE";

	private MongoClient client;

	public MongoDB()
	{
		try 
		{
			client = new MongoClient(URI, 10018);
		} 
		catch (UnknownHostException e) 
		{
			client = new DefaultDatabase(); 
		}
	}
}
