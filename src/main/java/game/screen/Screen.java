package game.screen;

import game.Session;

import java.util.ArrayList;

import ui.render.Render;
import ui.render.StringRender;

/**
 * Screen is an abstract class that represents what is displayed to the user
 *
 * @author Matt
 */
public abstract class Screen
{   
    protected Session session;  
    protected ArrayList<Render> renders;
    protected ArrayList<StringRender> stringRenders;
    protected String playerId;

    /**
     * Links the screen to a session and sets up different renderable things.
     * @param session the current session
     */
    public Screen(Session session)
    {
        this.session = session;
        
        renders = new ArrayList<>();
        stringRenders = new ArrayList<>();
    }

    /**
     * update is called every tick of a thread that updates the screen
     */
    public abstract void update();

    /**
     * checks to see if the screen should switch
     * @return  if the screen should switch
     */
    public abstract boolean shouldSwitch();
    
    public void setPlayerId(String id)
    {
        playerId = id;
    }

    /**
     *  gets the renders for the screen
     *
     *
     * @return the ArrayList of renders
     */
    public ArrayList<Render> getRenders()
    {
        return renders;
    }

    /**
     * Gets the string renders
     * @return an ArrayList of renderable strings
     */
    public ArrayList<StringRender> getStringRenders()
    {
        return stringRenders;
    }
}
