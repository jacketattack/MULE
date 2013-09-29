package game;

import java.util.ArrayList;

/**
 * Holds all the data pertaining to the current game being played.
 * Using a game session allows for the game to be easily saved.
 * To save the game, serialize the session using something like GSON (http://code.google.com/p/google-gson/).
 * To load the game, deserialize the session and give it to the current round (will require modification to the game state)
 * 
 * @author grant
 * @author
 */
public class Session 
{
	private ArrayList<Character> characters;
	private int roundAt;
	
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
}
