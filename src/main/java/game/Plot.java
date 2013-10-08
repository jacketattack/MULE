package game;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The plot class represents a plot with a type that contains different
 * production values
 * @author grant
 * @author trevor
 */
public class Plot 
{
	public static final int SIZE = 70;
	
	private Point location;
	private PlotType plotType;
	private ImprovementType improvementType;
	/**
         * The plot constructor sets the type and its top left corner location
         * @param type - the type of the plot
         * @param x - the x coordinate of the plot
         * @param y  - the y coordinate of the plot
         */
	public Plot (PlotType type, int x, int y)
	{
		this.plotType = type;
		improvementType = ImprovementType.EMPTY;
		
		location = new Point(x, y);
	}
        /**
         * The get PlotType method returns the current type
         * @return PlotType type - the plot type
         */
	public PlotType getType()
	{
		return plotType;
	}
        /**
         * The getX returns the x location
         * @return x - the x location of the plot 
         */
	public int getX()
	{
		return (int)location.getX();
	}
        /**
         * The getY returns the y location
         * @return  y - the y location of the plot
         */
	public int getY()
	{
		return (int)location.getY();
	}
	/**
         *  Returns the food production for the plot
         * @return int food - the amount of possible food production 
         */
	public int getFoodProduction()
	{		
		if (improvementType == ImprovementType.EMPTY)
			return 0;
		
		return plotType.getFood();
	}
	/**
         * the getEnergyProduction method returns the energy production 
         * @return int energyProduction - the possible energy production
         */
	public int getEnergyProduction()
	{
		if (improvementType == ImprovementType.EMPTY)
			return 0;
		
		return plotType.getEnergy();
	}
        /**
         * The getOreProduction method returns the ore production
         * @return int oreProduction - the possible ore production
         */
	public int getOreProduction()
	{
		if (improvementType == ImprovementType.EMPTY)
			return 0;
		
		return plotType.getOre();
	}
	/**
         * the getBackgroundImage method gets the image of the land
         * @return Image - the background image
         */
	public Image getBackgroundImage()
	{
		return getImage("assets/images/plot/" + plotType.getImageName());
	}
        /**
         * the getImprovementImage method returns the corresponding image to an
         * improvement
         * 
         * @return Image - the improvement image
         */
	public Image getImprovementImage()
	{
		return getImage("assets/images/plot/" + improvementType.getImageName());
	}
	/**
         * theGetImage method handles the possible errors of trying to open an image
         * @param path - the path of the image
         * @return  Image - the image of the selected path
         */
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
