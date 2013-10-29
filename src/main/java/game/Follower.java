package game;

import java.awt.Image;
import java.util.ArrayList;

import core.ImageLoader;
import core.render.Renderable;

public abstract class Follower implements Renderable 
{	
	protected int x;
	protected int y;
	
	protected String imagePath;
	
	protected Character character;
	
	public Follower(Character character)
	{
		this.character = character;
		setX(character.getOldX() + 30);
		setY(character.getOldY());
	}
	
	public ArrayList<Image> getImages() 
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		ArrayList<Image> images = new ArrayList<Image>();
		Image image = imageLoader.load(imagePath);
		images.add(image);
		return images;
	}

	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public void setY(int y) 
	{
		this.y = y;
	}
	
	public void update() 
	{
		int dx = character.getX() - character.getOldX();
		int dy = character.getY() - character.getOldY();
		
		if (dx < 0)
		{
			setX(character.getOldX() + 30);
		}
		else if (dx > 0)
		{
			setX(character.getOldX() - 30);
		}

		if (dy < 0)
		{
			setY(character.getOldY());
		}
		else if (dy > 0)
		{
			setY(character.getOldY());
		}
	}
}
