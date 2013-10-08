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
}
