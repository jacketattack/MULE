package game;


public class Mule extends Follower
{	
	private ImprovementType type;
	
	public Mule(String playerId)
	{
		super(playerId);
		imagePath = "assets/images/mule.png";
	}
}
