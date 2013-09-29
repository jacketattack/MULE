package game;

import java.util.ArrayList;

/**
 * 
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
	
    public Inventory() 
    {
    	ownedPlots = new ArrayList<Plot>();
    }
}
