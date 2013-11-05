package game.store;


public class Pub extends Store 
{
	public Pub()
	{
		imagePath = "assets/images/store/storePub.png";
	}
	
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
