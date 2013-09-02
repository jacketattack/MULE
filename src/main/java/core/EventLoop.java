package core;

/**
 * 
 * @author grant
 * @author
 */
public class EventLoop 
{
	private static EventLoop instance;
	
	private boolean running;
	private StateUpdater stateUpdater;
	
	private EventLoop()
	{
		
	}

	public static EventLoop getInstance()
	{
		if (instance == null)
		{
			instance = new EventLoop();
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
		stateUpdater.update();
	}
}
