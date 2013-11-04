package game;

public class UpgradeStore extends Store 
{
	public static final int COST = 100;
	
	private ImprovementType type;
	
	public UpgradeStore(ImprovementType type)
	{
		this.type = type;
		imagePath = "assets/images/store/store" + type + ".png";
	}
	
	public void act() 
	{
		Character character = session.getCurrentCharacter();
		Follower follower = character.getFollower();
		
		if (follower != null && follower instanceof Mule)
		{
			Mule mule = (Mule)follower;
			
			if (mule.getType() == type)
			{
				mule.setType(ImprovementType.EMPTY);
				character.setMoney(character.getMoney() + COST);
			}
			else if (character.getMoney() >= COST)
			{
				mule.setType(type);
				character.setMoney(character.getMoney() - COST);
			}
		}
	}
}
