package game;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import core.ImageLoader;

/**
 * The character class represents the entity that different players control. 
 * @author grant
 * @author matt
 */
public class Character implements Renderable
{
	private Point location;
	
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
		location = new Point(0, 0);
		
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
		
		inventory.money = type.getMoney();
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
	
	public String getName()
	{
		return name;
	}
	
	public int getMoney()
	{
		return inventory.money;
	}
	
	public int getOre() 
	{
		return inventory.ore;
	}
	
	public int getFood()
	{
		return inventory.food;
	}
	
	public int getCrystite()
	{
		return inventory.crystite;
	}
	
	public int getEnergy()
	{
		return inventory.energy;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}

	public void setX(int x)
	{
		location.x = x;
	}

	public int getX()
	{
		return (int)location.getX();
	}

	public void setY(int y)
	{
		location.y = y;
	}

	public int getY()
	{
		return (int)location.getY();
	}
	
	public void applyForce(int x, int y)
	{
		location.x += x;
		location.y += y;
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

	public ArrayList<Image> getImages() 
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		Image image = imageLoader.load("assets/images/character/human.png");
		
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(image);
		
		return images;
	}
}
