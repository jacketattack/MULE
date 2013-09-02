package core;

import game.state.State;

public class StateUpdater 
{
	private static StateUpdater instance;
	
	private StateUpdater()
	{
		
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
		StateSelector stateSelector = StateSelector.getInstance();
		State currentState = stateSelector.getState();
		
		currentState.update();	
	}
}
