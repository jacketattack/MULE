package ui;

import java.awt.Image;
import java.util.ArrayList;

import core.ImageLoader;
import game.Renderable;

public class BoundsButton implements Renderable {

	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	
	public BoundsButton(String path) {
		
		ImageLoader imageLoader = ImageLoader.getInstance();
		image = imageLoader.load(path);
		
	}
	
	public boolean inSide(int i, int j){
		return ( (i >= x && i< (x+width)) && (j>= y && j < (y+height)) );
	}
	
	public void setX(int x) 
	{
		this.x = x;
	}
	
	public void setY(int y) 
	{
		this.y = y;
	}
	
	public void setWidth(int width) 
	{
		this.width = width;
	}
	
	public void setHeight(int height) 
	{
		this.height = height;
	}

	@Override
	public ArrayList<Image> getImages() 
	{	
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(image);
		
		
		return images;
	}

	@Override
	public int getX() 
	{
		return x;
	}

	@Override
	public int getY() 
	{
		return y;
	}
}
