package ui.render;

import java.awt.Image;
import java.util.ArrayList;

import core.ImageLoader;

public class SimpleRender implements Renderable
{
	private Image image;
	private int x;
	private int y;
	
	public SimpleRender(String path)
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		image = imageLoader.load(path);
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
