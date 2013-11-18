package game;

/**
 * Controls the difficulty of the game by altering initial food and energy levels
 */
public enum Difficulty 
{
	BEGINNER,
	STANDARD,
	TOURNAMENT;
	
    /**
     * Gets the initial food supply
     * @return int matching the starting food level
     */
	public static int getStartingFood(Difficulty level)
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

	/**
     * Gets the starting energy level
     * @return The int reperesenting the starting energy for the difficulty
     */
	public static int getStartingEnergy(Difficulty level)
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
}
