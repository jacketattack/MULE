package game.store;

import game.Follower;
import game.Mule;

public class MuleStore extends Store 
{
	public static final int MULE_COST = 100;
	
	public MuleStore()
	{
		imagePath = "assets/images/store/storeMule.png";
	}
	
	public void act() 
	{
		String playerId = session.getCurrentPlayerId();
		
		if (session.getPlayerFollower(playerId) == null && session.getPlayerMoney(playerId) >= MULE_COST)
		{
			Follower mule = new Mule(playerId);
			session.setPlayerFollower(playerId, mule);
			
			int balance = session.getPlayerMoney(playerId);
			session.setPlayerMoney(playerId, balance - MULE_COST);
		}
		else 
		{
			Follower mule = session.getPlayerFollower(playerId);
			if (mule instanceof Mule)
			{
				session.setPlayerFollower(playerId, null);
				
				int balance = session.getPlayerMoney(playerId);
				session.setPlayerMoney(playerId, balance + MULE_COST);
			}
		}
	}
}