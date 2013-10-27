package game;

import java.util.Comparator;

/**
 * A class for establishing a turn order, based on increasing score.
 * Meant to be used with Collections.sort(array,TurnOrderCalculator);
 * This will order you array properly.
 * 
 * If anyone wants to turn this into a singleton class, feel free
 * @author nicholast
 */
public class TurnOrderCalculator implements Comparator<Character>
{    
    @Override
    public int compare(Character c1, Character c2) 
    {
        if (c1.getScore()<c2.getScore())
        {
            return -1;
        }
        else if (c1.getScore()>c2.getScore())
        {
            return 1;
        }
        else
        {
        return 0;
        }    
    }    
}
