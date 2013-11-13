package core;

import game.state.*;

/**
 * StateSelector provides access to the game's current state.
 * 
 * StateSelector is a singleton; instances of this class 
 * cannot be created.
 */
public class StateSelector
{
	private static StateSelector instance;
	
	/** The current state the game is in */
	private State currentState;
	
	private StateSelector()
	{
		// Set the current state to DefaultState so that
		// other classes don't need to worry about the current
		// state being null
		currentState = new DefaultState();
	}
	
	/**
	 * Returns the StateSelector object associated with the current game. 
	 * Most of the methods of class StateSelector are instance methods and 
	 * must be invoked with respect to the current object. 
	 * 
	 * @return the StateSelector object associated with the current game.
	 */
	public static StateSelector getInstance()
	{
		if (instance == null)
		{
			instance = new StateSelector();
		}
		
		return instance;
	}
	
	/**
	 * Sets the current state.
	 * The previous state is immediately removed upon invocation.
	 * @param state the new state
	 */
	public void setState(State state)
	{
		if (state == null)
		{
			return;
		}
		
		currentState = state;
	}
	
	/**
	 * Returns the current state. 
	 * The current state is guaranteed to not be null
	 *
	 * @return The current state
	 */
	public State getState()
	{
		return currentState;
	}
}