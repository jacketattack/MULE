package game.state;

import game.Character;
import game.CharacterType;
import game.Difficulty;
import game.Session;

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
	private int numPlayers;
	private ArrayList<String> characterNames;
	private ArrayList<CharacterType> characterTypes;
	private Difficulty difficulty;
	
	public GameSetupState()
	{
		characterNames = new ArrayList<String>();
		characterTypes = new ArrayList<CharacterType>();
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
	
	public void addPlayerName(String name)
	{
		characterNames.add(name);
	}
	
	public void addCharacterType(CharacterType type)
	{
		characterTypes.add(type);
	}
	
	public void createSession()
	{
		ArrayList<Character> characters = new ArrayList<Character>();
		
		for (int a = 0; a < numPlayers; a++)
		{
			Character character = new Character();
			character.setName(characterNames.get(a));
			character.setType(characterTypes.get(a));
			characters.add(character);
		}
		
		Session session = new Session(characters);
		System.out.println(session);
		
		StateSelector stateSelector = StateSelector.getInstance();
		GameState gameState = new GameState(session);
		
		stateSelector.setState(gameState);
		
		Window window = Window.getInstance();
		window.setPanel(new GamePanel());
	}
}
