package game;

/**
 * PlotType is an enum that stores the different possible plot types
 * @author grant
 * @author
 */
public enum PlotType 
{	
	RIVER (4, 0, 2, "assets/images/plot/river.png"),
	PLAIN (2, 1, 3, "assets/images/plot/plain.png"),
	MOUNTAIN_1 (1, 2, 1, "assets/images/plot/mountain_1.png"), 
	MOUNTAIN_2 (1, 3, 1, "assets/images/plot/mountain_2.png"), 
	MOUNTAIN_3 (1, 4, 1, "assets/images/plot/mountain_3.png"),
	TOWN (0, 0, 0, "assets/images/plot/town.png");

	private int food;
	private int ore;
	private int energy;
	
	private String imagePath;
	
	private PlotType (int food, int ore, int energy, String imagePath)
	{
		this.food = food;
		this.ore = ore;
		this.energy = energy;
		this.imagePath = imagePath;
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
	
	public String getImagePath()
	{
		return imagePath;
	}
}
