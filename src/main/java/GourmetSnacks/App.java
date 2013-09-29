package GourmetSnacks;

import ui.Window;
import core.EventLoop;

/**
 * 
 * @author grant
 * @author
 */
public class App 
{
    public static void main( String[] args )
    {
		Window.getInstance();
    
    	EventLoop eventLoop = EventLoop.getInstance();
    	eventLoop.start();
    }
}
