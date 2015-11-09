package game.round;

import game.Session;

import java.util.concurrent.CopyOnWriteArrayList;

import ui.render.Render;
import ui.render.StringRender;

public abstract class Round
{
    /** The current game session */
    protected Session session;
    
    protected CopyOnWriteArrayList<Render> renders;
    protected CopyOnWriteArrayList<StringRender> stringRenders;
    
    public Round()
    {
        renders = new CopyOnWriteArrayList<>();
        stringRenders = new CopyOnWriteArrayList<>();
    }
    
    /**
     * Called once every frame. All actions that take place 
     * during a round must be the result of this method being
     * called.
     */
    public abstract void update();

    /**
     * Alert the round that an mouse click occurred
     * @param x The x pos in pixels
     * @param y The y pos in pixels
     * @param leftMouse Whether the left mouse was pressed
     */
    public abstract void click(int x, int y, boolean leftMouse);
    
    /**
     * Returns whether the round has been completed. 
     * If true, the round next in line will begin
     */
    public abstract boolean isDone();

    /**
     * Method that should properly set up required instance variables
     * and graphics for the class
     */
    public abstract void init();

    /**
     *
     * @return
     */
    public CopyOnWriteArrayList<Render> getRenders()
    {
        return renders;
    }

    /**
     *
     * @return
     */
    public CopyOnWriteArrayList<StringRender> getStringRenders()
    {
        return stringRenders;
    }

    /**
     * Gets the session for use in rounds
     * @return
     */
    public Session getSession()
    {
        return session;
    }

    /**
     * Sets the session of the current game for this round to use
     * @param session
     */
    public void setSession(Session session)
    {
        this.session = session;
    }
}