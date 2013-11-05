package game;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable
{
	private static final long serialVersionUID = -5069381464350666035L;
	
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
    
    public Inventory(Inventory inventory)
    {
    	this.food = inventory.food;
    	this.energy = inventory.energy;
    	this.ore = inventory.ore;
    	this.crystite = inventory.crystite;
    	this.money = inventory.money;
    	
    	ArrayList<Plot> copiedPlots = new ArrayList<Plot>();
    	for (Plot plot : inventory.ownedPlots)
		{
    		Plot copiedPlot = new Plot(plot);
    		copiedPlots.add(copiedPlot);
		}
    	this.ownedPlots = copiedPlots;
    }
    
    public double getScore()
    {
        double score = food + energy + ore + crystite + ((double) money) / 10;
        score += ownedPlots.size();
        return score;
    }
}
