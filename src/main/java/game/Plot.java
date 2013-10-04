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

	private int foodProduction;
	private int energyProduction;
	private int oreProduction;
	private int crystiteProduction;
	
	public Plot (PlotType type, int x, int y)
	{
		this.type = type;
		location = new Point(x, y);

		foodProduction = PlotType.getDefaultFoodProduction(type);
		energyProduction = PlotType.getDefaultEnergyProduction(type);
		oreProduction = PlotType.getDefaultOreProduction(type);
		crystiteProduction = PlotType.getDefaultCrystiteProduction(type);
	}

	public PlotType getType()
	{
		return type;
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
