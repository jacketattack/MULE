package game;

public class Mule extends Follower
{
	private ImprovementType type;
	
	public Mule(Character character)
	{
		super(character);
		imagePath = "assets/images/character/human.png";
	}
}
