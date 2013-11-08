package GourmetSnacks;

public class Config 
{
	private static int playerMovementSpeed;
	
	public static void load()
	{
		playerMovementSpeed = 2;
	}
	
	public static int getPlayerMovementSpeed()
	{
		return playerMovementSpeed;
	}
}
