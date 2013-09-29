package game;

/**
 * 
 * @author grant
 * @author
 */
public enum Difficulty 
{
	BEGINNER,
	STANDARD,
	TOURNAMENT;
	
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
