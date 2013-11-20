package GourmetSnacks;

import game.Player;

import java.util.HashMap;

import core.db.DB;
import core.db.DatabaseObject;

/**
 * The configuration singleton
 */
public class Config 
{
	private static Config instance;
	
	private HashMap<String, Object> data;
	
	public Config()
	{
		data = new HashMap<>();
	}

	/**
	 * Load the config file. If the online config file is not available
	 * or accepted, a local configuration will be used
	 */
	public void load()
	{
		DB db = DB.getInstance();
		DatabaseObject response = db.get("config", "live");

        boolean downloadConfigEnabled;
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
	
	/**
	 * Get a key value pair configuration
	 * @param id The key
	 * @return The value
	 */
	public Object get(String id)
	{
		return data.get(id);
	}
	
	/**
	 * Get instance
	 * @return The instance
	 */
	public static Config getInstance()
	{
		if (instance == null)
		{
			instance = new Config();
		}
		
		return instance;
	}
	
	private void loadDefaults()
	{	
		data.put("playerMovementSpeed", 2);
	}
}