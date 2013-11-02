package game;

import java.util.Comparator;

/**
 * A class for establishing a turn order, based on increasing score.
 * Meant to be used with Collections.sort(array,TurnOrderCalculator);
 * This will order you array properly.
 * @author nicholast
 */
public class TurnOrderCalculator implements Comparator<Character>
{    
    public int compare(Character c1, Character c2) 
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
