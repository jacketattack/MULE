package game;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import core.ImageLoader;
import core.render.Renderable;

/**
 * 
 * @author grant
 * @author trevor
 */
public class Plot implements Renderable
{
	public static final int SIZE = 70;
	
	private Point location;
	private PlotType plotType;
	private ImprovementType improvementType;
	
	public Plot (PlotType type, int x, int y)
	{
		this.plotType = type;
		improvementType = ImprovementType.EMPTY;
		
		location = new Point(y, x);
	}

	public PlotType getType()
	{
		return plotType;
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
		ImageLoader imageLoader = ImageLoader.getInstance();
		return imageLoader.load("assets/images/plot/" + plotType.getImageName());
	}
	
	public Image getImprovementImage()
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		return imageLoader.load("assets/images/plot/" + improvementType.getImageName());
	}

	public ArrayList<Image> getImages()
	{
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(getBackgroundImage());
		images.add(getImprovementImage());
		
		return images;
	}

	public int getX()
	{
		return (int)(location.getX() * SIZE);
	}

	public int getY()
	{
		return (int)(location.getY() * SIZE);
	}
}
