package GourmetSnacks;

import game.Player;

import java.util.HashMap;

import core.db.DB;
import core.db.DatabaseObject;

public class Config 
{
	private static Config instance;
	
	public Config()
	{
		data = new HashMap<String, Object>();
	}
	
	private boolean downloadConfigEnabled;
	
	private HashMap<String, Object> data;
	
	public void load()
	{
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
				//data.put("plotCost", (Integer)response.get("plotCost"));
				
				Player.MOVEMENT_SPEED = (int)get("playerMovementSpeed");
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
	
	public Object get(String id)
	{
		return data.get(id);
	}
	
	private void loadDefaults()
	{	
		data.put("playerMovementSpeed", 2);
		//data.put("plotCost", 300);
	}

	public static Config getInstance()
	{
		if (instance == null)
		{
			instance = new Config();
		}
		
		return instance;
	}
}