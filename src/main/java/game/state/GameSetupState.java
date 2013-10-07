package game.state;

import game.Character;
import game.CharacterType;
import game.Difficulty;
import game.Map;
import game.Session;

import java.awt.Color;
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

	/** List holding character data */
	private ArrayList<String> characterNames;
	/** List holding character data */
	private ArrayList<CharacterType> characterTypes;
	/** List holding character data */
	private ArrayList<Color> characterColors;
	
	/**
	 * Instantiate all the variables in the constructor
	 */
	public GameSetupState()
	{
		characterNames = new ArrayList<String>();
		characterTypes = new ArrayList<CharacterType>();
		characterColors = new ArrayList<Color>();
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
     * Add a name to the character list. This list corresponds
     * to the other character data received from the user
     * @param name The character's name
     */
	public void addPlayerName(String name)
	{
		characterNames.add(name);
	}
	
    /**
     * Add a character type to the character list. This list corresponds
     * to the other character data received from the user
     * @param name The character's type
     */
	public void addCharacterType(CharacterType type)
	{
		characterTypes.add(type);
	}
	
    /**
     * Add a color to the character list. This list corresponds
     * to the other character data received from the user
     * @param name The character's color
     */
	public void addCharacterColor(Color color)
	{
		characterColors.add(color);
	}
	
	/**
	 * Assemble the game session from all the user data
	 */
	public void createSession()
	{
		ArrayList<Character> characters = new ArrayList<Character>();
		
		// create the characters
		for (int a = 0; a < numPlayers; a++)
		{
			Character character = new Character();
			character.setName(characterNames.get(a));
			character.setType(characterTypes.get(a));
			character.setColor(characterColors.get(a));
			characters.add(character);
		}
		
		// create the session
		Session session = new Session(characters);
		session.setMap(map);
		System.out.println(session);
		
		GameState gameState = new GameState(session);

		// switch to the game state
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(gameState);
		
		// switch to the game panel
		Window window = Window.getInstance();
		window.setPanel(new GamePanel());
	}
}
