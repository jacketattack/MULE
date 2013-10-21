package game.state;

import game.Character;
import game.Difficulty;
import game.Map;
import game.Session;

import java.util.ArrayList;

import ui.Window;
import ui.panel.GamePanel;
import core.StateSelector;

/**
 * The GameSetupState manages all the data
 * received from the user when they begin a new game
 * @author grant
 * @author trevor
 */
public class GameSetupState implements State 
{
	/** The current game map */
	private Map map;

	/** Represents how many players are playing */
	private int numPlayers;

	/** The current game difficulty */
	private Difficulty difficulty;

	/** List holding participating characters */
	private ArrayList<Character> characters;
	
	/**
	 * Instantiate all the variables in the constructor
	 */
	public GameSetupState()
	{
		characters = new ArrayList<Character>();
	}

	/**
	 * implementation to come...
	 */
	public void update() 
	{
	}
	
	/**
	 * Set the game's difficulty
	 * @param difficulty The game's difficulty
	 */
	public void setDifficulty(Difficulty difficulty)
	{
		this.difficulty = difficulty;
	}
	
	/**
	 * Set the number of players participating in the game
	 * @param num The number of players
	 */
	public void setNumPlayers(int num) 
	{
		numPlayers = num;
	}
	
	/**
	 * Get the number of players participating in the game
	 * @return The number of players
	 */
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
	/**
	 * Set the game map
	 * @param map The game map
	 */
    public void setMap(Map map)
    {
        this.map = map;
    }
    
    /**
     * Add a character to the game
     * @param character The character
     */
    public void addCharacter(Character character)
    {
    	characters.add(character);
    }
        
	/**
	 * Assemble the game session from all the user data
	 */
	public void createSession()
	{
		// create the session
		Session session = new Session(characters);
		session.setMap(map);
		
		GameState gameState = new GameState(session);

		// switch to the game state
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(gameState);
		
		// switch to the game panel
		Window window = Window.getInstance();
		window.setPanel(new GamePanel());
	}
}
