package core.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseObject 
{
	private Map<String, Object> data;
	private ArrayList<String> entries;
	
	public DatabaseObject()
	{
		data = new HashMap<>();
		entries = new ArrayList<>();
	}
	
	public Object get(String id)
	{
		return data.get(id);
	}
	
	public void put(String id, Object object)
	{
		data.put(id, object);
		entries.add(id);
	}
	
	public ArrayList<String> getEntries()
	{
		return entries;
	}
	
	public Map<String, Object> getMap()
	{
		return data;
	}
}