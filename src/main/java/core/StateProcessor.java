package core;

public class StateProcessor
{
	private static StateProcessor instance;
	
	private State currentState;
	
	private StateProcessor()
	{
		
	}
	
	public static StateProcessor getInstance()
	{
		if (instance == null)
		{
			instance = new StateProcessor();
		}
		
		return instance;
	}
	
	public void update()
	{
		currentState.update();
	}
	
	public void setState(State state)
	{
		currentState = state;
	}
}
