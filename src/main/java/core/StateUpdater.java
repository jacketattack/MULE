package core;

import game.state.State;

/**
 * @author grant
 */
public class StateUpdater 
{
	private static StateUpdater instance;

	private StateSelector stateSelector;
	
	private StateUpdater()
	{
		stateSelector = StateSelector.getInstance();
	}
	
	public static StateUpdater getInstance()
	{
		if (instance == null)
		{
			instance = new StateUpdater();
		}
		
		return instance;
	}
	
	public void update()
	{
		State currentState = stateSelector.getState();
		currentState.update();	
	}
}
