package GourmetSnacks;

import game.Player;

import java.util.HashMap;

import core.db.DB;
import core.db.DatabaseObject;

public class Config 
{
	private static Config instance;
	
	private HashMap<String, Object> data;
	
	private boolean downloadConfigEnabled;
	
	public Config()
	{
		data = new HashMap<>();
	}
	
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
				data.put("playerMovementSpeed", response.get("playerMovementSpeed"));
				
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