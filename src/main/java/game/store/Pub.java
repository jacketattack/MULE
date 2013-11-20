package game.store;

/**
 * a Pub ends a players turn while giving them a random amount of  money
 */
public class Pub extends Store 
{
    /**
     * The constructor sets the image for the store
     */
	public Pub()
	{
		imagePath = "assets/images/store/storePub.png";
	}

    /**
     * ends the players turn and increments their money
     */
	public void act()
	{
		String id = session.getCurrentPlayerId();
		
		int bonus = (int)(session.getCurrentRound() * (Math.random() * session.getTimer()));
		if (bonus > 250)
		{
			bonus = 250;
		}
		
		session.setPlayerMoney(id, session.getPlayerMoney(id) + bonus);
		session.setTimer(0);
	}
}
