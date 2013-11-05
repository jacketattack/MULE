package game.store;

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
		String id = session.getCurrentPlayerId();
		
		Follower mule = new Mule(id);
		session.setPlayerFollower(id, mule);
	}
}