package core.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DatabaseObject stores all the data from a database as a key-value pair.
 * This allows for different databases to communicate in a unison way.
 */
public class DatabaseObject 
{
	private Map<String, Object> data;
	private ArrayList<String> entries;
	
	public DatabaseObject()
	{
		data = new HashMap<>();
		entries = new ArrayList<>();
	}
	
	/**
	 * Get a value out of the database object
	 * @param id The key
	 * @return The corresponding value
	 */
	public Object get(String id)
	{
		return data.get(id);
	}
	
	/**
	 * Put a value in the database object
	 * @param id The key
	 * @param object The value
	 */
	public void put(String id, Object object)
	{
		data.put(id, object);
		entries.add(id);
	}
	
	/**
	 * Get all the keys stored in database object
	 * @return A list of keys
	 */
	public ArrayList<String> getKeys()
	{
		return entries;
	}
	
	/**
	 * Get a Map of all key-value pairs
	 * @return The map
	 */
	public Map<String, Object> getMap()
	{
		return data;
	}
}