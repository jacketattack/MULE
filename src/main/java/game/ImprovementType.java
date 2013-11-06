package game;

/**
 * An enum for controlling which image represents the plot improvements on the ui
 * 
 * @author grant
 * @author trevor
 */
public enum ImprovementType 
{
	EMPTY ("empty.png", "assets/images/mule/muleEmpty.png"),
	FOOD ("food.png", "assets/images/mule/muleFood.png"),
	ORE ("ore.png", "assets/images/mule/muleOre.png"),
	ENERGY ("energy.png", "assets/images/mule/muleEnergy.png"),
	CRYSTITE ("crystite.png", "assets/images/mule/muleCrystite.png");
	
	private String plotImageName;
	private String muleImageName;
	
    /**
     * Sets the image name for the enumerated type
     */
	private ImprovementType(String plotImageName, String muleImageName)
	{
		this.plotImageName = plotImageName;
		this.muleImageName = muleImageName;
	}
	
    /**
     * 
     * @return the String representing the improvement image
     */
	public String getPlotImageName()
	{
		return plotImageName;
	}
	
	public String getMuleImageName()
	{
		return muleImageName;
	}
}
