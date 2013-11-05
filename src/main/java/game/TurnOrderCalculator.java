package game;

import java.util.Comparator;

/**
 * A class for establishing a turn order, based on increasing score.
 * Meant to be used with Collections.sort(array,TurnOrderCalculator);
 * This will order you array properly.
 * @author nicholast
 */
public class TurnOrderCalculator implements Comparator<Player>
{    
    public int compare(Player c1, Player c2) 
    {
        if (c1.getScore() < c2.getScore())
        {
            return -1;
        }
        else if (c1.getScore() > c2.getScore())
        {
            return 1;
        }
        else
        {
        	return 0;
        }    
    }    
}
