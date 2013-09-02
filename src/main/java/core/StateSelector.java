package core;

import game.state.*;

/**
 * 
 * @author grant
 * @author
 */
public class StateSelector
{
	private static StateSelector instance;
	
	private State currentState;
	
	private StateSelector()
	{
		currentState = new DefaultState();
	}
	
	public static StateSelector getInstance()
	{
		if (instance == null)
		{
			instance = new StateSelector();
		}
		
		return instance;
	}
	
	public void setState(State state)
	{
		if (state == null)
		{
			return;
		}
		
		currentState = state;
	}
	
	public State getState()
	{
		return currentState;
	}
}