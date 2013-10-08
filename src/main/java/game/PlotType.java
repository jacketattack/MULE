package game;

/**
 * 
 * @author grant
 * @author
 */
public enum PlotType 
{	
	RIVER (4, 0, 2, "river.png"),
	PLAIN (2, 1, 3, "plain.png"),
	MOUNTAIN_1 (1, 2, 1, "mountain_1.png"), 
	MOUNTAIN_2 (1, 3, 1, "mountain_2.png"), 
	MOUNTAIN_3 (1, 4, 1, "mountain_3.png"),
	TOWN (0, 0, 0, "town.png");

	private int food;
	private int ore;
	private int energy;
	
	private String imageName;
	
	private PlotType (int food, int ore, int energy, String imageName)
	{
		this.food = food;
		this.ore = ore;
		this.energy = energy;
		this.imageName = imageName;
	}
	
	public int getFood()
	{
		return food;
	}
	
	public int getOre()
	{
		return ore;
	}
	
	public int getEnergy()
	{
		return energy;
	}
	
	public String getImageName()
	{
		return imageName;
	}
}
