package game.store;

import game.Character;
import game.Follower;
import game.Mule;

public class MuleStore extends Store 
{
	public MuleStore()
	{
		imagePath = "assets/images/store/storeMule.png";
	}
	
	public void act() 
	{
		Character character = session.getCurrentCharacter();
		Follower mule = new Mule(character);
		character.setFollower(mule);
	}
}