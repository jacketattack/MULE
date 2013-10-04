package game;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author grant
 * @author trevor
 */
public class Plot 
{
	public static final int SIZE = 70;
	
	private Point location;
	private PlotType plotType;
	private ImprovementType improvementType;

	private int foodProduction;
	private int energyProduction;
	private int oreProduction;
	private int crystiteProduction;
	
	public Plot (PlotType type, int x, int y)
	{
		this.plotType = type;
		location = new Point(x, y);
		improvementType = ImprovementType.EMPTY;

		foodProduction = PlotType.getDefaultFoodProduction(type);
		energyProduction = PlotType.getDefaultEnergyProduction(type);
		oreProduction = PlotType.getDefaultOreProduction(type);
		crystiteProduction = PlotType.getDefaultCrystiteProduction(type);
	}

	public PlotType getType()
	{
		return plotType;
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
	
	public Image getPlotImage()
	{
		Image image = null;
		String fileName = "assets/images/plot/plot" + plotType + ".png";
		try 
		{
			image = ImageIO.read(new File(fileName));
		} 
		catch (IOException e)
		{
			System.out.println(e);
			System.out.println("johnny, i tried" + fileName);
		}
		
		return image;
	}
}
