package game.store;

import game.Follower;
import game.ImprovementType;
import game.Mule;

public class UpgradeStore extends Store 
{
	public static final int COST = 100;
	
	private ImprovementType type;
	
	public UpgradeStore(ImprovementType type)
	{
		this.type = type;
		imagePath = type.getStoreImagePath();
	}
	
	public void act() 
	{
		String playerId = session.getCurrentPlayerId();
		Follower follower = session.getPlayerFollower(playerId);
		
		if (follower != null && follower instanceof Mule)
		{
			Mule mule = (Mule)follower;
			
			if (mule.getType() == type)
			{
				mule.setType(ImprovementType.EMPTY);
				int balance = session.getPlayerMoney(playerId);
				session.setPlayerMoney(playerId, balance + COST);
			}
			else if (session.getPlayerMoney(playerId) >= COST)
			{
				mule.setType(type);
				int balance = session.getPlayerMoney(playerId);
				session.setPlayerMoney(playerId, balance - COST);
			}
		}
	}
}
