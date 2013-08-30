package game;

public enum PlayerLevel 
{
	BEGINNER,
	STANDARD,
	TOURNAMENT;
	
	public static int getStartingFood(PlayerLevel level)
	{
		int amount = -1;
		
		switch (level)
		{
			case BEGINNER:
				amount = 8;
				break;
			case STANDARD:
				amount = 4;
				break;
			case TOURNAMENT:
				amount = 4;
				break;
		}
		
		return amount;
	}

	
	public static int getStartingEnergy(PlayerLevel level)
	{
		int amount = -1;
		
		switch (level)
		{
			case BEGINNER:
				amount = 4;
				break;
			case STANDARD:
				amount = 2;
				break;
			case TOURNAMENT:
				amount = 2;
				break;
		}
		
		return amount;
	}
	
	/**
	 * This method returns zero regardless of level. It is included for the sake of consistency.
	 */
	public static int getStartingOre(PlayerLevel level)
	{
		return 0;
	}
}
