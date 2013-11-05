package game;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import core.ImageLoader;
import core.render.Renderable;

/**
 * The character class represents the entity that different players control.
 */
public class Player implements Renderable, Serializable
{
	private static final long serialVersionUID = -5587146699955167582L;
	
	public static final int WIDTH = 12;
	public static final int HEIGHT = 19;
	public static final int MOVEMENT_SPEED = 2;  
	
	private Point location;
	private Point oldLocation;
	
	private String name;
	private Color color;
	private PlayerType type;
	
	private Inventory inventory;
	private Follower follower;
	
	private String id;

	/**
	 *The Character constructor sets the starting inventory for a given difficulty and race.
	 *
	 *
	 *@param difficulty-the difficulty for the Character
	 *@param start - the race for the Character
	 *
	 */
	public Player(PlayerType start, Difficulty difficulty) 
	{
		inventory = new Inventory();
                
		setType(start);
		setDifficulty(difficulty);

		location = new Point(0, 0);
		oldLocation = new Point(0, 0);
	}


	/**
	 * Default constructor for character. Sets up an inventory, Human character, and 
	 * beginner difficulty. 
	 *
	 */
	public Player() 
	{
		this(PlayerType.HUMAN, Difficulty.BEGINNER);
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public PlayerType getType()
	{
		return type;
	}
	
	public void setType(PlayerType type)
	{
		this.type = type;
		
		inventory.money = type.getMoney();
	}
	
    public double getScore()
    {
        return inventory.getScore();
    }
    
	/**
	 * Left blank intentionally for now
	 *
	*/
	public void update()
	{
		if (follower != null)
		{
			follower.update();
		}
		
		oldLocation.x = location.x;
		oldLocation.y = location.y;
	}
	
	/**
	 * Sets food and energy levels for the corresponding difficulty
	 *
	 * @param difficulty one of the options in the Difficulty enum
	 */
	public void setDifficulty(Difficulty difficulty)
	{
		inventory.food = Difficulty.getStartingFood(difficulty);
		inventory.energy = Difficulty.getStartingEnergy(difficulty);
	}
	
    public void addPlot(Plot plot)
    {
        inventory.ownedPlots.add(plot);
    }
            
    public ArrayList<Plot> getPlots() 
    {
        return inventory.ownedPlots;
    }
        
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void incrementOre(int ore)
	{
		inventory.ore += ore;
	}
	
	public void incrementCrystite(int crystite)
	{
		inventory.crystite += crystite;
	}
	
	public void incrementFood(int food)
	{
		inventory.food += food;
	}
	
	public void incrementEnergy(int energy)
	{
		inventory.energy += energy;
	}
	
	public void incrementMoney(int money)
	{
		inventory.money += money;
	}
	
	public boolean checkBuy(int cost)
	{
		return cost >= inventory.money;
	}
	
	public boolean checkSell(String resource, int sellAmount)
	{
		boolean canSell = false;
		switch (resource) {
			case "ore":
				canSell = inventory.ore >= sellAmount;
				break;
			case "crystite":
				canSell = inventory.crystite >= sellAmount;
				break;
			case "food":
				canSell = inventory.food >= sellAmount;
				break;
			case "energy":
				canSell = inventory.energy >= sellAmount;
				break;
		}
		return canSell;
	}
	
	public void sellResource(String resource, int quantity, int price)
	{
		switch (resource) {
			case "ore":
				incrementOre(-quantity);
				incrementMoney(price * quantity);
				break;
			case "crystite":
				incrementCrystite(-quantity);
				incrementMoney(price * quantity);
				break;
			case "food":
				incrementFood(-quantity);
				incrementMoney(price * quantity);
				break;
			case "energy":
				incrementEnergy(-quantity);
				incrementMoney(price * quantity);
				break;
		}
	}
	
	public void buyResource(String resource, int quantity, int price) 
	{
		switch (resource) {
		case "ore":
			incrementOre(quantity);
			incrementMoney( -(price * quantity) );
			break;
		case "crystite":
			incrementCrystite(quantity);
			incrementMoney(- (price * quantity));
			break;
		case "food":
			incrementFood(quantity);
			incrementMoney( -(price * quantity));
			break;
		case "energy":
			incrementEnergy(quantity);
			incrementMoney( -(price * quantity) );
			break;
		}
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
	
	public void setMoney(int amount) 
	{
		inventory.money = amount;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Color getColor()
	{
		return color;
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
	
	public int getOldX()
	{
		return (int) oldLocation.getX();
	}
	
	public int getOldY() 
	{
		return (int) oldLocation.getY();
	}
	
	public void applyForce(int x, int y)
	{
		location.x += x;
		location.y += y;
	}
	
	public void setFollower(Follower follower)
	{
		this.follower = follower;
	}
	
	public Follower getFollower()
	{
		return follower;
	}

	public ArrayList<Image> getImages() 
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		Image image = imageLoader.load("assets/images/player/human.png");
		
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(image);
		
		return images;
	}
}
