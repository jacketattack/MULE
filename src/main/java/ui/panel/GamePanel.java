package ui.panel;

import game.state.GameState;

import java.util.concurrent.CopyOnWriteArrayList;

import ui.render.Render;
import ui.render.StringRender;
import core.StateSelector;

public class GamePanel extends RenderPanel 
{   
    private static final long serialVersionUID = -8162980471814220010L;

    /**
     * Adds to renders to be drawn to the game panel
     * 
     * @param renders renderable objects to draw on panel
     */
    public void draw(CopyOnWriteArrayList<Render> renders)
    {   
        this.renders = renders;
    }
    
    /**
     * Adds strings that are renderable to the game panel
     * 
     * @param stringRenders renderable string objects now going to be drawn
     */
    public void drawStrings(CopyOnWriteArrayList<StringRender> stringRenders)
    {
        this.stringRenders = stringRenders;
    }

    /**
     * Forward mouse clicks to the current state
     * @param x x position
     * @param y y position
     * @param isRightMouseButton Whether the right mouse button was pressed
     */
    public void click (int x, int y, boolean isRightMouseButton)
    {
        StateSelector stateSelector = StateSelector.getInstance();
        GameState state = (GameState)stateSelector.getState();
            if (state != null)
            state.click(x, y, isRightMouseButton);
    }
}
