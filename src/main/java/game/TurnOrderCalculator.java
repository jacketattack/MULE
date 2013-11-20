package game;

import java.util.Comparator;

/**
 * A class for establishing a turn order, based on increasing score.
 * Meant to be used with Collections.sort(array,TurnOrderCalculator);
 * This will order you array properly.
 */
public class TurnOrderCalculator implements Comparator<Player>
{    
	/**
	 * Compare two players based on their score
	 * @param player1 Player one
	 * @param player2 Player two
	 * @return A value between -1 and 1 that represents the comparison
	 */
    public int compare(Player player1, Player player2) 
    {
        if (player1.getScore() < player2.getScore())
        {
            return -1;
        }
        else if (player1.getScore() > player2.getScore())
        {
            return 1;
        }
        else
        {
        	return 0;
        }    
    }    
}
