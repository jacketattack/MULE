package game;

import java.awt.Color;

import ui.render.Render;
import ui.render.Renderable;

/**
 * The plot class represents a plot with a type that contains different
 * production values
 */
public class Plot implements Renderable
{
	public static final int SIZE = 70;

	private boolean isOwned;

	private int x;
	private int y;
	private Color color;
	private PlotType plotType;
	private ImprovementType improvementType;

    private Mule mule;
    
    private Render render;

    /**
     * Create a plot based on a plot type
     * @param type The type of this plot
     * @param x The x position
     * @param y The y position
     */
	public Plot (PlotType type, int x, int y)
	{
		this.plotType = type;
		improvementType = ImprovementType.EMPTY;
		
		this.x = x;
		this.y = y;
		
		render = new Render();
		render.x = y * Plot.SIZE;
		render.y = x * Plot.SIZE;
	}
	
	/**
	 * Create a deep copy of a plot
	 * @param plot The plot to copy
	 */
	public Plot(Plot plot)
	{
		this.x = plot.x;
		this.y = plot.y;
		this.render = plot.render;
		
		this.color = plot.color;
		this.plotType = plot.plotType;
		this.improvementType = plot.improvementType;
		this.mule = plot.mule;
	}
	
    /**
     * The get PlotType method returns the current type
     * @return PlotType type - the plot type
     */
	public PlotType getPlotType()
	{
		return plotType;
	}
	
	/**
	 * Get the plot's improvement type
	 * @return The plot's improvement type
	 */
	public ImprovementType getImprovementType()
	{
		return improvementType;
	}

	/**
	 * Set the plot's improvement type
	 * param type The plot's improvement type
	 */
    public void setImprovementType(ImprovementType type)
    {
    	this.improvementType = type;
    	if (type != ImprovementType.EMPTY)
    	{
    		Mule mule = new Mule("");
    		mule.setType(type);
    		this.mule = mule;
    	}
    }

	/**
	 * Get the plot's mule
	 * @return The plot's mule
	 */
    public Mule getMule() 
    {
        return mule;
    }
    
    /**
     * Whether the plot has a mule on it
     * @return Whether the plot has a mule
     */
    public boolean hasMule()
    {
    	return mule != null;
    }

    /**
     * Set the plot's mule
     * @param mule The mule to place on the plot
     */
    public void setMule(Mule mule) 
    {
        this.mule = mule;
        if (mule == null)
        {
            this.improvementType = ImprovementType.EMPTY; 	
        }
        else
        {
        	this.improvementType = mule.getType();
        }
    }
	
    /**
     * Get the food production of this tile
     * @return The food production
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
	 * Get the image path for the border image based on who owns the pot
	 * @return The border image path
	 */
	public String getBorderImagePath()
	{		
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
		
		return "assets/images/plot/" + colorName + "Border.png";
	}
	
	/**
	 * Get the x position of the tile
	 * @return The x position
	 */
	public int getX()
	{
		return this.x * SIZE;
	}

	/**
	 * Get the y position of the tile
	 * @return The y position
	 */
	public int getY()
	{
		return this.y * SIZE;
	}
	
	/**
	 * Whether the plot is owned
	 * @return Whether the plot is owned
	 */
	public boolean isOwned()
	{
		return isOwned;
	}
	
	/**
	 * Set the plot's owned status
	 * @param bool Whether the plot is owned or not
	 */
	public void setIsOwned(boolean bool)
	{
		isOwned = bool;
	}
	
	/**
	 * Set the color
	 * @param color The color
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}

	/**
	 * Get the color
	 * @return The color
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Get the plot's id
	 * @return The plot's id
	 */
    public String getId()
    {
    	return x + "x" + y;
    }
	
    /**
     * Whether the given x/y coordinates are within 
     * the bounds of the plot
     * @return Whether the x/y coordinates are inside the plot
     */
	public boolean inBounds(int x, int y)
	{
		return x > this.y * SIZE && x < this.y * SIZE + SIZE && y > this.x * SIZE && y < this.x * SIZE + SIZE;
	}
	
	/**
	 * Get the plot's Render
	 * @return The render
	 */
	public Render getRender()
	{
		render.clearImages();
		render.addImage(plotType.getImagePath());
		render.addImage(improvementType.getPlotImagePath());
		if (color != null)
		{
			render.addImage(getBorderImagePath());
		}
		return render;
	}
}
