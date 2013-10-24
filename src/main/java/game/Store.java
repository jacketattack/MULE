package game;

import core.render.Renderable;


public abstract class Store implements Renderable
{
	private int x;
	private int y;
	private int width;
	private int height;
	
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
}
