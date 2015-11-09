package game.store;

import game.Session;
import ui.render.Render;
import ui.render.Renderable;

/**
 * A Store must be able to interact and decide whether or not someone is inside of the store.
 */

public abstract class Store implements Renderable
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String imagePath;
    protected Session session;

    /**
     *  this method is called whenever a user interacts while inside the store
     */
    public abstract void act();

    /**
     * checks whether or not the x and y coordinates are inside of the store
     * @param x the x coordinate
     * @param y the y coordinate
     * @return  whether or not the coordinates are in the store.
     */
    public boolean inBounds(int x, int y) 
    {
        return  x > this.x && x < this.x + width && y > this.y && y < this.y + height;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int x) 
    {
        this.x = x; 
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y) 
    {
        this.y = y; 
    }
    
    public void setWidth(int x)
    {
        width = x;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setHeight(int y)
    {
        height = y;
    }
    
    public int getHeight() 
    {
        return height;
    }
    
    public void setSession(Session session)
    {
        this.session = session;
    }
    
    public Session getSession()
    {
        return session;
    }

    /**
     * Gets the render for the store
      * @return
     */
    public Render getRender()
    {
        Render render = new Render();
        render.x = this.x;
        render.y = this.y;
        render.width = this.width;
        render.height = this.height;
        render.addImage(imagePath);
        return render;
    }
}
