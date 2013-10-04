package game.state;

import game.Character;
import game.CharacterType;
import game.Session;

import java.util.ArrayList;

import ui.Window;
import ui.panel.GamePanel;
import core.StateSelector;
import game.Map;
/**
 * 
 * @author grant
 * @author trevor
 */
public class GameSetupState implements State 
{
        private Map map;
	private int numPlayers;
	private ArrayList<String> characterNames;
	private ArrayList<CharacterType> characterTypes;
	
	public GameSetupState()
	{
		characterNames = new ArrayList<String>();
		characterTypes = new ArrayList<CharacterType>();
	}

	public void update() 
	{
	}
	
	public void setNumPlayers(int num) 
	{
		numPlayers = num;
	}
	
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
        public void setMap(Map map){
            this.map=map;
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
		session.setMap(map);
		StateSelector stateSelector = StateSelector.getInstance();
		GameState gameState = new GameState(session);
		
		stateSelector.setState(gameState);
		
		Window window = Window.getInstance();
		window.setPanel(new GamePanel());
	}
}
