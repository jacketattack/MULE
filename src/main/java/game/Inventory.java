package game;

import java.util.ArrayList;

/**
 * The inventory represents the characters different items, currency, and plots
 * @author grant
 */
public class Inventory 
{
	public int food;
	public int energy;
	public int ore;
	public int crystite;
	public int money;
	
	public ArrayList<Plot> ownedPlots;
    
	/**
     * The inventory constructor makes an inventory, defaulting all values to zero
     * and making an ArrayList of plots
     */
    public Inventory() 
    {
    	ownedPlots = new ArrayList<Plot>();
    }
    
    public double getScore()
    {
        double score = food + energy + ore + crystite + ((double)money)/10;
        score += ownedPlots.size();
        return score;
    }
    
    /**
     * Method used by AuctionStore to construct array to track prices
     * @return the number of items in the inventory excluding money
     */
    public int itemsCount()
    {
        return 4;
    }
}
