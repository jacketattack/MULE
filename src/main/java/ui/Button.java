package ui;

import ui.render.Render;
import ui.render.Renderable;
import core.Callable;

/**
 * This class represents an image on screen that can 
 * do specific actions for us when people click on the
 * image.
 * 
 * In essence, it replaces JButtons for us.
 * 
 *
 */
public class Button implements Renderable 
{
    public enum ButtonState 
    {
        DEFAULT, HOVER, CLICK;
    }
    
    public final Callable HOVER_COMMAND = new Callable()
            {
                public void call()
                {
                    setState(ButtonState.HOVER);
                }
            };
    
    public final Callable UNHOVER_COMMAND = new Callable()
    {
        public void call()
        {
            setState(ButtonState.DEFAULT);
        }
    };
    
    public final Callable PRESS_COMMAND = new Callable()
    {
        public void call()
        {
            setState(ButtonState.CLICK);
        }
    };
    
    private ButtonState state;
    
    private Render defaultRender;
    private Render hoverRender;
    private Render clickRender;
    
    private int x;
    private int y;
    private int width;
    private int height;
    
    /**
     * This contructor for a button only has one image
     * regardless of the state it is in.
     * 
     * @param defaultRenderPath Picture to display for the button
     */
    public Button(String defaultRenderPath) 
    {   
        this(defaultRenderPath, defaultRenderPath, defaultRenderPath);
    }

    /**
     * This constructor is for a button with one general image for it 
     * and then a second image for when you hover your mouse over the 
     * image.
     * 
     * @param defaultRenderPath path to image displayed unless hovering over button
     * @param hoverRenderPath path to image to display when mouse hovers over button
     */
    public Button(String defaultRenderPath, String hoverRenderPath)
    {
        this(defaultRenderPath, hoverRenderPath, hoverRenderPath);
    }
    
    /**
     * This constructor is for a button with different images for 
     * states Default, Hover, and click
     * 
     * @param defaultRenderPath path to image shown when not clicking or hovering
     * @param hoverRenderPath path to image shown when mouse hovers over button
     * @param clickRenderPath path to image shown when user clicks on button
     */
    public Button(String defaultRenderPath, String hoverRenderPath, String clickRenderPath)
    {
        state = ButtonState.DEFAULT;
        
        defaultRender = new Render();
        defaultRender.addImage(defaultRenderPath);

        hoverRender = new Render();
        hoverRender.addImage(hoverRenderPath);
        
        clickRender = new Render();
        clickRender.addImage(clickRenderPath);
    }
    
    /**
     * Sets state to chosen conditon
     * 
     * @param state enum for state
     */
    public void setState(ButtonState state)
    {
        this.state = state;
    }
    
    /**
     * This returns true if the x and y coordinates passed 
     * in are within the range of the image of the button.
     * 
     * @param x x coordinate (column)
     * @param y y coordinate (row)
     * @return true if x and y are within the image, false otherwise
     */
    public boolean inBounds(int x, int y)
    {
        if ( x > this.x && x < this.x + width && y > this.y && y < this.y + height)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Sets the width of the button
     * 
     * @param width width is normally based on dimensions of image
     */
    public void setWidth(int width) 
    {
        this.width = width;
    }
    
    /**
     * Sets the height of the button
     * 
     * @param height height, again based on the height of button's image normally
     */
    public void setHeight(int height) 
    {
        this.height = height;
    }
    
    /**
     * Returns Renderable object that is the image
     * for the button based on the current state.
     * 
     */
    public Render getRender()
    {
        Render render = defaultRender;
        
        switch (state)
        {
            case DEFAULT:
                render = defaultRender;
                break;
            case HOVER:
                render = hoverRender;
                break;
            case CLICK:
                render = clickRender;
                break;
        }
        
        render.x = x;
        render.y = y;
        render.width = width;
        render.height = height;
        return render;
    }
    
    public void setX(int x) 
    {
        this.x = x;
    }
    
    public int getX() 
    {
        return x;
    }
    
    public void setY(int y) 
    {
        this.y = y;
    }
    
    public int getY() 
    {
        return y;
    }
}
