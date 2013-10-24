package game;


import java.awt.Image;
import java.util.ArrayList;

import core.ImageLoader;

public class Pub extends Store 
{
	public ArrayList<Image> getImages() 
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		ArrayList<Image> images = new ArrayList<Image>();
		Image image = imageLoader.load("assets/images/store/storePub.png");
		images.add(image);
		return images;
	}
}
