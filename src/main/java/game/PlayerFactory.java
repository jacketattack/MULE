package game;

import java.util.Date;

public class PlayerFactory 
{
	private static int n = 0; 
	public Player createPlayer()
	{		
		Player player = new Player();
		player.setId("" + new Date().getTime() + n);
		n++;
		
		return player;
	}
}
