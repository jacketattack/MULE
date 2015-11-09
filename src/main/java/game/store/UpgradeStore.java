package game.store;

import game.Follower;
import game.ImprovementType;
import game.Mule;

/**
 * Upgrade store represents  a store that upgrades a move to a given improvement type.
 */

public class UpgradeStore extends Store 
{
    public static final int COST = 100;
    
    private ImprovementType type;

    /**
     * Sets the imagepath and improvement type to the store.
     * @param type
     */

    public UpgradeStore(ImprovementType type)
    {
        this.type = type;
        imagePath = type.getStoreImagePath();
    }

    /**
     * allows the user to buy a mule upgrade while decrementing their money
     */
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
