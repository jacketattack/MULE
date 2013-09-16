package game;

/**
 * 
 * @author grant
 * @author
 */
public class Inventory 
{
	private int food;
	private int energy;
	private int ore;
	private int crystite;
	private int money;
	private Plot[] ownedPlots;

	public int getFood()
	{
		return food;
	}

	public int getEnergy()
	{
		return energy;
	}

	public int getOre()
	{
		return ore;
	}

	public int getCrystite()
	{
		return crystite;
	}

	public int getMoney()
	{
		return money;
	}

	public Plot[] getPlots()
	{
		return ownedPlots;
	}
}
