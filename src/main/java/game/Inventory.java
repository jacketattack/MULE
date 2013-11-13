package game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An inventory holds a number of items something can have
 */
public class Inventory implements Serializable
{
	private static final long serialVersionUID = -5069381464350666035L;
	
	public int food;
	public int energy;
	public int ore;
	public int crystite;
	public int money;
	
	/** The ids of plots owned */
	public ArrayList<String> ownedPlotIds;
    
    public Inventory() 
    {
    	ownedPlotIds = new ArrayList<String>();
    }
    
    /**
     * Deep copy an inventory into a new inventory
     * @param inventory Inventory to copy
     */
    public Inventory(Inventory inventory)
    {
    	this.food = inventory.food;
    	this.energy = inventory.energy;
    	this.ore = inventory.ore;
    	this.crystite = inventory.crystite;
    	this.money = inventory.money;
    	this.ownedPlotIds = inventory.ownedPlotIds;
    }
    
    /**
     * Get a score based on the inventories contents
     * @return The score
     */
    public double getScore()
    {
        double score = food + energy + ore + crystite + ((double) money) / 10;
        score += ownedPlotIds.size();
        return score;
    }
    
    /**
     * Method used by AuctionStore to construct array to track prices
     * @return the number of items in the inventory excluding money
     */
    public int itemCount()
    {
        return 4;
    }
}
