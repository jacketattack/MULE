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
 * 
 * @author grant
 * @author trevor
 */
public class GameSetupState implements State 
{
	private Map map;
	private int numPlayers;

	private Difficulty difficulty;

	private ArrayList<String> characterNames;
	private ArrayList<CharacterType> characterTypes;
	private ArrayList<Color> characterColors;
	
	public GameSetupState()
	{
		characterNames = new ArrayList<String>();
		characterTypes = new ArrayList<CharacterType>();
		characterColors = new ArrayList<Color>();
	}

	public void update() 
	{
	}
	
	public void setDifficulty(Difficulty difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public void setNumPlayers(int num) 
	{
		numPlayers = num;
	}
	
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
    public void setMap(Map map)
    {
        this.map = map;
    }
        
	public void addPlayerName(String name)
	{
		characterNames.add(name);
	}
	
	public void addCharacterType(CharacterType type)
	{
		characterTypes.add(type);
	}
	
	public void addCharacterColor(Color color)
	{
		characterColors.add(color);
	}
	
	public void createSession()
	{
		ArrayList<Character> characters = new ArrayList<Character>();
		
		for (int a = 0; a < numPlayers; a++)
		{
			Character character = new Character();
			character.setName(characterNames.get(a));
			character.setType(characterTypes.get(a));
			character.setColor(characterColors.get(a));
			characters.add(character);
		}
		
		Session session = new Session(characters);
		session.setMap(map);
		System.out.println(session);
		
		StateSelector stateSelector = StateSelector.getInstance();
		GameState gameState = new GameState(session);
		
		stateSelector.setState(gameState);
		
		Window window = Window.getInstance();
		window.setPanel(new GamePanel());
	}
}
