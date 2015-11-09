package game.store;

import game.Follower;
import game.Mule;

/**
 * Allows the user to buy a Mule if they have the money.
 */
public class MuleStore extends Store 
{
    public static final int MULE_COST = 100;

    /**
     * sets the image path for the MuleStore
     */
    public MuleStore()
    {
        imagePath = "assets/images/store/storeMule.png";
    }

    /**
     * Allows the user to buy the mule if they have the money.  also allows the user to sell the mule!
     */
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