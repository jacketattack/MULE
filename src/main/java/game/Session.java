package game;

import game.round.Round;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds all the data pertaining to the current game being played.
 * Using a game session allows for the game to be easily saved.
 * 
 * @author grant
 * @author trevor
 */
public class Session implements Serializable
{
	private static final long serialVersionUID = 4844617072635077446L;

	private int timer;
	private int roundAt;
	private int currentCharacterIndex;
	
	private Map map;
	private ArrayList<Character> characters;

	public Round currentRound;
	public ArrayList<Round> rounds;
	
	/**
     * The Session constructor defaults the round to one and creates an Array
     * of characters
     * @param characters 
     */
	public Session(ArrayList<Character> characters)
	{
		this.characters = characters;
		rounds = new ArrayList<Round>();
	}
	
	/**
     * The getCharacters returns an arraylist of characters
     * @return ArrayList characters - the characters of the session 
     */
	public ArrayList<Character> getCharacters()
	{
		return characters;
	}
	
   /**
    * The getRoundAt() returns the current round numer
    * @return int roundAt- the current round 
    */
	public int getRoundAt()
	{
		return roundAt;
	}
	/**
     * The incrementRound, increments roundAt
     * 
     */
	public void incrementRound()
	{
		roundAt++;
	}
	
	/**
     * The setMap sets the map for the current session
     * @param map - the map of the game
     */
    public void setMap(Map map) 
    {
        this.map = map;
    }
    
    /**
     * The getMap returns the map of the game
     * @return Map map - the current map of the game. 
     */
    public Map getMap()
    {
        return map;
    }
    
    public void setTimer(int timer)
    {
    	this.timer = timer;
    }
    
    public void decrementTimer()
    {
    	timer--;
    }
    
    public void incrementTimer()
    {
    	timer++;
    }
    
    public int getTimer()
    {
    	return timer;
    }
    
    public void setCurrentCharacterIndex(int index)
    {
    	this.currentCharacterIndex = index;
    }
    
    public void incrementCurrentCharacterIndex()
    {
    	currentCharacterIndex++;
    }
    
    public int getCurrentCharacterIndex()
    {
    	return currentCharacterIndex;
    }
    
    public Character getCurrentCharacter()
    {
    	return characters.get(currentCharacterIndex);
    }
}
