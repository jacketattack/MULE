package game;

/**
 * The character class represents the entity that different players control. 
 * @author grant
 * @author matt
 */
public class Character 
{
	private Inventory inventory;

	private String name;
	private CharacterType type;
	private Difficulty difficulty;

	/**
	 *The Character constructor sets the starting inventory for a given difficultry and race.
	 *
	 *
	 *@param difficulty-the difficulty for the Character
	 *@param start - the race for the Character
	 *
	 */
	public Character() 
	{
		inventory = new Inventory();
		
		setType(CharacterType.HUMAN);
		setDifficulty(Difficulty.BEGINNER);
	}
	
	public void update()
	{
		
	}
	
	public void setType(CharacterType type)
	{
		this.type = type;
		
		inventory.money = CharacterType.getStartingMoney(type);
	}
	
	public void setDifficulty(Difficulty difficulty)
	{
		this.difficulty = difficulty;
		
		inventory.food = Difficulty.getStartingFood(difficulty);
		inventory.energy = Difficulty.getStartingEnergy(difficulty);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return "[Character]" +
				"\nName: " + name +
				"\nType: " + type +
				"\nFood: " + inventory.food +
				"\nEnergy: " + inventory.energy +
				"\nOre: " + inventory.ore +
				"\nCrystite: " + inventory.crystite +
				"\nMoney: " + inventory.money;
	}
}
