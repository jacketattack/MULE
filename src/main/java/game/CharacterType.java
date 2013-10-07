package game;

/**
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
	
	private CharacterType(int money)
	{
		this.money = money;
	}
	
	public int getMoney()
	{
		return money;
	}
}
