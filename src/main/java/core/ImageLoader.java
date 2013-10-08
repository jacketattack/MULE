package core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader 
{
	private static ImageLoader instance;
	
	private ImageLoader()
	{
		
	}
	
	public Image load(String path)
	{
		Image image = null;
		
		try 
		{
			image = ImageIO.read(new File(path));
		} 
		catch (IOException e)
		{
			System.out.println(e);
		}
		
		return image;
	}
	
	public static ImageLoader getInstance()
	{
		if (instance == null)
		{
			instance = new ImageLoader();
		}
		
		return instance;
	}
}
