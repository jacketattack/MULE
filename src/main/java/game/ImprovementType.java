package game;

/**
 * An enum for controlling which image represents the plot improvements on the ui
 * 
 * @author grant
 * @author trevor
 */
public enum ImprovementType 
{
	EMPTY ("assets/images/plot/empty.png", "assets/images/mule/muleEmpty.png", ""),
	FOOD ("assets/images/plot/food.png", "assets/images/mule/muleFood.png", "assets/images/store/storeFood.png"),
	ORE ("assets/images/plot/ore.png", "assets/images/mule/muleOre.png", "assets/images/store/storeOre.png"),
	ENERGY ("assets/images/plot/energy.png", "assets/images/mule/muleEnergy.png", "assets/images/store/storeEnergy.png"),
	CRYSTITE ("assets/images/plot/crystite.png", "assets/images/mule/muleCrystite.png", "assets/images/store/storeCrystite.png");
	
	private String plotImagePath;
	private String muleImagePath;
	private String storeImagePath;
	
    /**
     * Sets the image name for the enumerated type
     */
	private ImprovementType(String plotImagePath, String muleImagePath, String storeImagePath)
	{
		this.plotImagePath = plotImagePath;
		this.muleImagePath = muleImagePath;
		this.storeImagePath = storeImagePath;
	}
	
    /**
     * @return Path for plot improvement image
     */
	public String getPlotImagePath()
	{
		return plotImagePath;
	}

    /**
     * @return Path for mule improvement image
     */
	public String getMuleImagePath()
	{
		return muleImagePath;
	}
	
	public String getStoreImagePath()
	{
		return storeImagePath;
	}
}
