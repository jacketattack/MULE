package game;


import java.awt.Image;
import java.util.ArrayList;

import core.ImageLoader;

public class Pub extends Store 
{
	public void act()
	{
		Character character = session.getCurrentCharacter();
		
		int bonus = (int)(session.getRoundAt() * (Math.random() * session.getTimer()));
		if (bonus > 250)
		{
			bonus = 250;
		}
		
		character.setMoney(character.getMoney() + bonus);
		
		session.setTimer(0);
	}
	
	public ArrayList<Image> getImages() 
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		ArrayList<Image> images = new ArrayList<Image>();
		Image image = imageLoader.load("assets/images/store/storePub.png");
		images.add(image);
		return images;
	}
}
