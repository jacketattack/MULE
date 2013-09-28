package game;

import java.awt.Point;

/**
 * 
 * @author grant
 * @author trevor
 */
public class Plot 
{
	public static final int SIZE = 70;
	
	private Point location;
	private PlotType type;
	private Character owner;

	private int foodProduction;
	private int energyProduction;
	private int oreProduction;
	private int crystiteProduction;
	
	public Plot (PlotType type, int x, int y)
	{
		this.type = type;
		location = new Point(x, y);
		owner = null;

		foodProduction = PlotType.getDefaultFoodProduction(type);
		energyProduction = PlotType.getDefaultEnergyProduction(type);
		oreProduction = PlotType.getDefaultOreProduction(type);
		crystiteProduction = PlotType.getDefaultCrystiteProduction(type);
	}

	public boolean isMountain3()
	{
		return type == PlotType.MOUNTAIN_3;
	}

	public PlotType getType()
	{
		return type;
	}

	public void setOwner(Character owner)
	{
		this.owner = owner;
	}

	public int getX()
	{
		return (int)location.getX();
	}

	public int getY()
	{
		return (int)location.getY();
	}
	
	public int getFoodProduction()
	{
		return foodProduction;
	}
	
	public int getEnergyProduction()
	{
		return energyProduction;
	}

	public int getOreProduction()
	{
		return oreProduction;
	}	
	
	public int getCrystiteProduction()
	{
		return crystiteProduction;
	}
}
