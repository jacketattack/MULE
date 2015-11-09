package core;

/**
 * EventThread is the thread used by the EventLoop. It is 
 * designed to execute at a specific FPS.
 *
 * Timer or another 'clock' could always be used instead
 * of this class if problems arise.
 */
public class EventThread implements Runnable
{
    /** The desired frames per second */
    private static final int FPS = 30;

    /** The delay (in ms) between frames */
    private static final int DELAY = (int)(1000 / FPS);

    private EventLoop eventLoop;
    
    public EventThread(EventLoop eventLoop)
    {
        this.eventLoop = eventLoop;
    }
    
    /** 
     * As long as an unexpected error does not occur, the
     * EventThread will invoke the 'update' method of EventLoop
     * every frame.
     */
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