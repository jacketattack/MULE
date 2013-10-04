package game;

import java.awt.Color;

/**
 * The character class represents the entity that different players control. 
 * @author grant
 * @author matt
 */
public class Character 
{
	private String name;
	private Color color;
	private CharacterType type;
	
	private Inventory inventory;

	/**
	 *The Character constructor sets the starting inventory for a given difficulty and race.
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
		inventory.food = Difficulty.getStartingFood(difficulty);
		inventory.energy = Difficulty.getStartingEnergy(difficulty);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public String toString()
	{
		return "[Character]" +
				"\nName: " + name +
				"\nType: " + type +
				"\nColor: " + color +
				"\nFood: " + inventory.food +
				"\nEnergy: " + inventory.energy +
				"\nOre: " + inventory.ore +
				"\nCrystite: " + inventory.crystite +
				"\nMoney: " + inventory.money;
	}
}
