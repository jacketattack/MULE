package game;

public enum CharacterType 
{
	HUMAN,
	FLAPPER,
	ROBOT;

	
	public static int getStartingMoney(CharacterType type) 
	{
		int amount = 0;
		switch (type)
		{
		
		case HUMAN:
			amount = 1600;
			break;
		case FLAPPER:
			amount = 600;
			break;
		case ROBOT:
			amount = 1000;
			break;		
		}
		return amount;	
	}
}
