package ui;

import ui.render.Render;
import ui.render.Renderable;

public class Button implements Renderable 
{
	private Render render;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Button(String path) 
	{	
		render = new Render();
		render.addImage(path);
	}
	
	public boolean inBounds(int x, int y)
	{
		if ( x > this.x && x < this.x + width && y > this.y && y < this.y + height)
		{
			return true;
		}
		
		return false;
	}
	
	public void setWidth(int width) 
	{
		this.width = width;
	}
	
	public void setHeight(int height) 
	{
		this.height = height;
	}
	
	public Render getRender()
	{
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
