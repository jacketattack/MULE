package game;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import ui.render.Renderable;
import core.ImageLoader;

public abstract class Follower implements Renderable, Serializable
{	
	protected int x;
	protected int y;
	protected String imagePath;
	protected String playerId;
	protected Session session;
	
	public Follower(String playerId)
	{
		this.playerId = playerId;
	}
	
	public void init()
	{
		setX(session.getPlayerX(playerId) + 30);
		setY(session.getPlayerY(playerId));
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
		setX(session.getPlayerX(playerId) + 30);
		setY(session.getPlayerY(playerId));
		
//		int dx = player.getX() - player.getOldX();
//		int dy = player.getY() - player.getOldY();
//		
//		if (dx < 0)
//		{
//			setX(player.getOldX() + 30);
//		}
//		else if (dx > 0)
//		{
//			setX(player.getOldX() - 30);
//		}
//
//		if (dy < 0)
//		{
//			setY(player.getOldY());
//		}
//		else if (dy > 0)
//		{
//			setY(player.getOldY());
//		}
	}
	
	public void setSession(Session session)
	{
		this.session = session;
	}
}
