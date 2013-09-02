package GourmetSnacks;

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
	    	EventLoop processor = EventLoop.getInstance();
	    	processor.start();
    }
}
