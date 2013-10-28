package game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The inventory represents the characters different items, currency, and plots
 * @author grant
 */
public class Inventory implements Serializable
{
	private static final long serialVersionUID = -5069381464350666035L;
	
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
}
