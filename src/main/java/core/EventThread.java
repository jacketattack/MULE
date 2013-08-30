package core;

public class EventThread implements Runnable
{
	private EventLoopProcessor processor;
	
	private int FPS = 30;
	private int delay = (int) 1000 / FPS;
	
	public EventThread(EventLoopProcessor processor)
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