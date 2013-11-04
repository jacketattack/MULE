package game.store;

import game.Character;

public class Pub extends Store 
{
	public Pub()
	{
		imagePath = "assets/images/store/storePub.png";
	}
	
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
