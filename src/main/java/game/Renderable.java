package game;

import java.awt.Image;
import java.util.ArrayList;

public interface Renderable 
{
	// implement cheap cache
	
	public ArrayList<Image> getImages();
	public int getX();
	public int getY();
}
