package core;

/**
 * 
 * @author grant
 * @author
 */
public class EventLoopProcessor 
{
	private static EventLoopProcessor instance;
	
	private boolean running;
	private StateProcessor stateProcessor;
	
	private EventLoopProcessor()
	{
		
	}

	public static EventLoopProcessor getInstance()
	{
		if (instance == null)
		{
			instance = new EventLoopProcessor();
		}
		
		return instance;
	}
	
	public void start()
	{
		if (!running)
		{
			EventThread thread = new EventThread(this);
			thread.run();

			running = true;
		}
	}
	
	public void update()
	{
		stateProcessor.update();
	}
}
