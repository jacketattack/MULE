package GourmetSnacks;

import java.util.HashMap;

import core.db.DB;
import core.db.DatabaseObject;

public class Config 
{
	private static boolean downloadConfigEnabled;
	
	private static HashMap<String, Object> data;
	
	public static void load()
	{
		data = new HashMap<String, Object>();
		
		DB db = DB.getInstance();
		DatabaseObject response = db.get("config", "live");
		
		if (response == null || response.get("downloadConfigEnabled") == null)
			downloadConfigEnabled = false;
		else
			downloadConfigEnabled = (boolean)response.get("downloadConfigEnabled");

		if (downloadConfigEnabled)
		{
			try
			{	
				data.put("playerMovementSpeed", (Integer)response.get("playerMovementSpeed"));
				data.put("plotCost", (Integer)response.get("plotCost"));
			}
			catch (Exception e)
			{
				loadDefaults();
			}
		}
		else
		{
			loadDefaults();
		}
	}
	
	public static Object get(String id)
	{
		return data.get(id);
	}
	
	private static void loadDefaults()
	{
		data.put("playerMovementSpeed", 2);
		data.put("plotCost", 300);
	}
}