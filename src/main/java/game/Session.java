package game;

import java.util.ArrayList;

/**
 * Holds all the data pertaining to the current game being played.
 * Using a game session allows for the game to be easily saved.
 * To save the game, serialize the session using something like GSON (http://code.google.com/p/google-gson/).
 * To load the game, deserialize the session and give it to the current round (will require modification to the game state)
 * 
 * @author grant
 * @author trevor
 */
public class Session 
{
	private int roundAt;
	private ArrayList<Character> characters;
	private Map map;
        
	public Session(ArrayList<Character> characters)
	{
		this.characters = characters;
		roundAt = 1;
	}
	
	public ArrayList<Character> getCharacters()
	{
		return characters;
	}
       
	public int getRoundAt()
	{
		return roundAt;
	}
	
	public void incrementRound()
	{
		roundAt++;
	}
	
    public void setMap(Map map) 
    {
        this.map = map;
    }
    
    public Map getMap()
    {
    	return map;
    }
        
	public String toString()
	{
		String mapText = "";
		
		for (int a = 0; a < 5; a++)
		{
			for (int b = 0; b < 9; b++)
			{
				mapText += map.getPlot(a, b).getType() + " ";
			}
			mapText += "\n";
		}
		
		String charactersText = "";
		for (Character character : characters)
		{
			charactersText += character + "\n\n";
		}
		
		return "[Session]" +
				"\nRound: " + roundAt +
				"\n" + 
				"\n" + charactersText +
				"\n[Map]" +
				"\n" + mapText;
	}
}