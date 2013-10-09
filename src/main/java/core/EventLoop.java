package core;


/**
 * EventLoop controls the game's internal clock.
 * All clock cycles originate in the EventLoop's
 * 'update' method and bubble upward. 
 * 
 * EventLoop is a singleton; instances of this class 
 * cannot be created.
 *
 * @author grant
 */
public class EventLoop 
{
	private static EventLoop instance;
	
	private long fps;
	
	/** 
	 * Flag that is used to ensure the 'start'
	 * method is executed only once.
	 */
	private boolean running;

	/** Instance of the StateUpdater */
	private StateUpdater stateUpdater;
	
	private EventLoop()
	{
		stateUpdater = StateUpdater.getInstance();
	}

	/**
	 * Returns the EventLoop object associated with the current game. 
	 * Most of the methods of class EventLoop are instance methods and 
	 * must be invoked with respect to the current object. 
	 * 
	 * @return the EventLoop object associated with the current game.
	 */
	public static EventLoop getInstance()
	{
		if (instance == null)
		{
			instance = new EventLoop();
		}
		
		return instance;
	}
	
	/**
	 * Starts the game's internal clock. This method should only 
	 * be called once (at the start of the application).
	 */
	public void start()
	{
		if (!running)
		{
			EventThread thread = new EventThread(this);
			thread.run();

			running = true;
		}
	}
	
	/**
	 * All clock cycles originate in this method. Invokes the
	 * 'update' method of the StateUpdater.
	 */
	public void update()
	{
		long pre = System.currentTimeMillis();
		stateUpdater.update();
		long post = System.currentTimeMillis();
		
		fps = post - pre;
	}
	
	public long getFPS()
	{
		return fps;
	}
}
