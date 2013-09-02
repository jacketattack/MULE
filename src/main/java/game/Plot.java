package game;

import java.awt.Point;

/**
 * 
 * @author grant
 * @author
 */
public class Plot 
{
	public int size;
	public Point location;
	public PlotType type;

	private int foodProduction;
	private int energyProduction;
	private int oreProduction;
	private int crystiteProduction;
	
	public Plot (PlotType type)
	{
		this.type = type;

		foodProduction = PlotType.getDefaultFoodProduction(type);
		energyProduction = PlotType.getDefaultEnergyProduction(type);
		oreProduction = PlotType.getDefaultOreProduction(type);
		crystiteProduction = PlotType.getDefaultCrystiteProduction(type);
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
