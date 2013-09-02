package core;

/**
 * 
 * @author grant
 * @author
 */
public class EventThread implements Runnable
{
	private EventLoop processor;
	
	private int FPS = 30;
	private int delay = (int) 1000 / FPS;
	
	public EventThread(EventLoop processor)
	{
		this.processor = processor;
	}
	
	public void run() 
	{
		boolean cycleFailed = false;
		
		while (!cycleFailed)
		{
			try 
			{
				Thread.sleep(delay);
				processor.update();
			} 
			catch (InterruptedException e) 
			{
				cycleFailed = true;
				e.printStackTrace();
			}
		}
	}
}