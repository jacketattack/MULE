package game;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import ui.render.Renderable;
import core.ImageLoader;

/**
 * The plot class represents a plot with a type that contains different
 * production values
 */
public class Plot implements Renderable, Serializable
{
	private static final long serialVersionUID = 9074483871643315710L;

	public static final int SIZE = 70;

	private boolean isOwned;

	private Color color;
	private Point location;
	private PlotType plotType;
	private ImprovementType improvementType;

    private Mule mule;

	public Plot (PlotType type, int x, int y)
	{
		this.plotType = type;
		improvementType = ImprovementType.EMPTY;
		
		location = new Point(y, x);
	}
	
	public Plot(Plot plot)
	{
		this.location = new Point();
		this.location.x = plot.location.x;
		this.location.y = plot.location.y;
		
		this.color = plot.color;
		this.plotType = plot.plotType;
		this.improvementType = plot.improvementType;
		this.mule = plot.mule;
	}
	
    /**
     * The get PlotType method returns the current type
     * @return PlotType type - the plot type
     */
	public PlotType getType()
	{
		return plotType;
	}
	
    public Mule getMule() 
    {
        return mule;
    }
    
    public boolean hasMule()
    {
    	return mule != null;
    }

    public void setMule(Mule mule) 
    {
        this.mule = mule;
        this.improvementType = mule.getType();
    }
	
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
	
	public Image getBorderImage()
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		String colorName = "empty";
				
		if (color.getRGB() == Color.red.getRGB())
		{
			colorName = "red";				
		}
		else if (color.getRGB() == Color.blue.getRGB())
		{
			colorName = "blue";
		} 
		else if (color.getRGB() == Color.black.getRGB())
		{
			colorName = "black";
		}
		else if (color.getRGB() == Color.green.getRGB()) 
		{
			colorName = "green";
		}
		
		return imageLoader.load("assets/images/plot/" + colorName + "Border.png");
	}

	public ArrayList<Image> getImages()
	{
		ArrayList<Image> images = new ArrayList<Image>();

		ImageLoader imageLoader = ImageLoader.getInstance();	
		images.add(imageLoader.load(plotType.getImagePath()));
		images.add(imageLoader.load(improvementType.getPlotImagePath()));
		
		if (color != null)
		{
			images.add(getBorderImage());
		}
		
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
	
	public boolean isOwned()
	{
		return isOwned;
	}
	
	public void setIsOwned(boolean bool)
	{
		isOwned = bool;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}

	@Override
	public void setX(int x) 
	{
		location.x = x;
	}

	@Override
	public void setY(int y)
	{
		location.y = y;	
	}
	
	public boolean inBounds(int x, int y)
	{
		return  x > location.getX() * SIZE && x < location.getX() * SIZE + SIZE && y > location.getY() * SIZE && y < location.getY() * SIZE + SIZE;
	}
}
