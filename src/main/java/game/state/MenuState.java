package game.state;

import core.StateSelector;

/**
 * 
 * @author grant
 * @author
 */
public class MenuState implements State 
{
	public void update()
	{
		// make the characters dance on the title screen
		
		// if user clicks start or key -> start()
		
		start();
	}
	
	public void start()
	{
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(new GameSetupState());
	}
}
