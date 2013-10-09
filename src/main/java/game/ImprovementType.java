package game;

/**
 * An enum for controlling which image represents the plot improvements on the ui
 * 
 * @author grant
 * @author trevor
 */
public enum ImprovementType 
{
	EMPTY ("empty.png"),
	FOOD ("food.png"),
	ORE ("ore.png"),
	ENERGY ("energy.png"),
	CRYSTITE ("crystite.png");
	
	private String imageName;
	
        /**
         * Sets the image name for the enumerated type
         */
	private ImprovementType(String imageName)
	{
		this.imageName = imageName;
	}
	
        /**
         * 
         * @return the String representing the improvement image
         */
	public String getImageName()
	{
		return imageName;
	}
}
