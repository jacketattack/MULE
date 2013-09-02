package GourmetSnacks;

import game.state.MenuState;
import core.EventLoop;
import core.StateSelector;

/**
 * 
 * @author grant
 * @author
 */
public class App 
{
    public static void main( String[] args )
    {
	    	MenuState menuState = new MenuState();
	    StateSelector stateSelector = 	StateSelector.getInstance();
	    stateSelector.setState(menuState);
    
	    	EventLoop eventLoop = EventLoop.getInstance();
	    	eventLoop.start();
    }
}
