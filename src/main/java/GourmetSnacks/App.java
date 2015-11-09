package GourmetSnacks;

import java.awt.EventQueue;

import ui.Window;

import core.EventLoop;
import core.IdGenerator;

/**
 * Entry point of the application
 */
public class App 
{
    public static void main( String[] args )
    {
        IdGenerator.getId();
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
