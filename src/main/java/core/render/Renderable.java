package core.render;

import java.awt.Image;
import java.util.ArrayList;

public interface Renderable 
{
	public ArrayList<Image> getImages();
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
}
