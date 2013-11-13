package core;

import game.state.State;

/**
 * StateUpdater updates the game's current state once
 * every clock cycle. 
 * 
 * StateUpdater is a singleton; instances of this class 
 * cannot be created.
 */
public class StateUpdater 
{
	private static StateUpdater instance;

	/** Instance of the StateSelector */
	private StateSelector stateSelector;
	
	private StateUpdater()
	{
		stateSelector = StateSelector.getInstance();
	}
	
	/**
	 * Returns the StateUpdater object associated with the current game. 
	 * Most of the methods of class StateUpdater are instance methods and 
	 * must be invoked with respect to the current object. 
	 * 
	 * @return the StateUpdater object associated with the current game.
	 */
	public static StateUpdater getInstance()
	{
		if (instance == null)
		{
			instance = new StateUpdater();
		}
		
		return instance;
	}
	
	/**
	 * Invokes the 'update' method of the current state.
	 */
	public void update()
	{
		State currentState = stateSelector.getState();
		currentState.update();
	}
}
