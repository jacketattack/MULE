package GourmetSnacks;

import java.awt.EventQueue;

import ui.Window;
import core.EventLoop;

/**
 * 
 * @author grant
 */
public class App 
{
    public static void main( String[] args )
    {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{		
				Window window = Window.getInstance();
				window.open();
			}
		});
		
    	EventLoop eventLoop = EventLoop.getInstance();
    	eventLoop.start();
    }
}
