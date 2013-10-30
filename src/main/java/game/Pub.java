package game;

/**
 * The pub is where a player can gamble the rest of his turn time to get increased money
 *
 * @author Matt
 */

public class Pub extends Store 
{
	public Pub()
	{
		imagePath = "assets/images/store/storePub.png";
	}

    /**
     * Act is called when a player interactions with the pub.  It will gamble away the persons current time.
     */
	public void act()
	{
		Character character = session.getCurrentCharacter();
		
		int bonus = (int)(session.getRoundAt() * (Math.random() * session.getTimer()));
		if (bonus > 250)
		{
			bonus = 250;
		}
		
		character.setMoney(character.getMoney() + bonus);
		
		session.setTimer(0);
	}
}
