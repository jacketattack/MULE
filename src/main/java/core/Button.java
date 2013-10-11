package core;

import java.awt.Image;
import java.util.ArrayList;

import core.render.Renderable;

public class Button implements Renderable 
{
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	
	public Button(String path) 
	{	
		ImageLoader imageLoader = ImageLoader.getInstance();
		image = imageLoader.load(path);	
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

	public ArrayList<Image> getImages() 
	{	
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(image);
		return images;
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
