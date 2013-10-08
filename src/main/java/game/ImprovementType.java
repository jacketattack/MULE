package game;

/**
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
	
	private ImprovementType(String imageName)
	{
		this.imageName = imageName;
	}
	
	public String getImageName()
	{
		return imageName;
	}
}
