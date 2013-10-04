package game;

/**
 * 
 * @author grant
 * @author trevor
 */
public enum CharacterType 
{
	HUMAN,
	FLAPPER,
	BONZOID,
	UGAITE,
	BUZZITE;
	
	public static int getStartingMoney(CharacterType type) 
	{
		int amount = 0;
		switch (type)
		{
			case HUMAN:
				amount = 600;
				break;
			case FLAPPER:
				amount = 1600;
				break;
			default:
				amount = 1000;
				break;		
		}
		return amount;	
	}
}
