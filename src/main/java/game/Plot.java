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
	
	public Plot (PlotType type, int x, int y)
	{
		this.plotType = type;
		improvementType = ImprovementType.EMPTY;
		
		location = new Point(x, y);
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
		if (improvementType == ImprovementType.EMPTY)
			return 0;
		
		return plotType.getFood();
	}
	
	public int getEnergyProduction()
	{
		if (improvementType == ImprovementType.EMPTY)
			return 0;
		
		return plotType.getEnergy();
	}

	public int getOreProduction()
	{
		if (improvementType == ImprovementType.EMPTY)
			return 0;
		
		return plotType.getOre();
	}
	
	public Image getBackgroundImage()
	{
		return getImage("assets/images/plot/" + plotType.getImageName());
	}
	
	public Image getImprovementImage()
	{
		return getImage("assets/images/plot/" + improvementType.getImageName());
	}
	
	public Image getImage(String path)
	{
		Image image = null;
		
		try 
		{
			image = ImageIO.read(new File(path));
		} 
		catch (IOException e)
		{
			System.out.println(e);
		}
		
		return image;
	}
}
