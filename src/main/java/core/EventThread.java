package core;

/**
 * ...
 * @author grant
 */
public class EventThread implements Runnable
{
	private static final int FPS = 30;
	private static final int DELAY = (int)(1000 / FPS);

	private EventLoop eventLoop;
	
	public EventThread(EventLoop eventLoop)
	{
		this.eventLoop = eventLoop;
	}
	
	public void run() 
	{
		boolean cycleFailed = false;
		
		while (!cycleFailed)
		{
			try 
			{
				Thread.sleep(DELAY);
				eventLoop.update();
			} 
			catch (InterruptedException e) 
			{
				cycleFailed = true;
				e.printStackTrace();
			}
		}
	}
}