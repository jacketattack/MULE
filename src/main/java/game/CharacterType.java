package game;

/**
 * Enum that specifies the starting money for different character types.
 * 
 * @author grant
 * @author trevor
 */
public enum CharacterType 
{
	HUMAN (600),
	FLAPPER (1600),
	BONZOID (1000),
	UGAITE (1000),
	BUZZITE (1000);
	
	private int money;
	/**
         * Character constructor used to link initial money with character type
         * @param money Starting amount
         */
	private CharacterType(int money)
	{
		this.money = money;
	}
	
	public int getMoney()
	{
		return money;
	}
}
