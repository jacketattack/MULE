package game.state;

import game.Session;
import core.StateSelector;

/**
 * 
 * @author grant
 * @author
 */
public class GameSetupState implements State 
{
	public void update() 
	{
		// user sets difficulty
		// user sets number of players
		// user sets map type
		
		// each player picks a race
		// "                        " a color
		// "                        " a name
		
		// if done 
		// {
		// 	setupGame()
		// 
		// 	StateSelector stateSelector = StateSelector.getInstance();
		// 	GameState gameState = new GameState();
		//		
		//		give gameState sessions
		//
		// 	stateSelector.setState(gameState);
		// }
		
		GameState gameState = new GameState();
		gameState.setInitialSession(new Session());
		
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(gameState);
	}
}
