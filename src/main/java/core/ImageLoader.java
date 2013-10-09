package core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader 
{
	private HashMap<String, Image> cache;
	
	private static ImageLoader instance;
	
	private ImageLoader()
	{
		cache = new HashMap<String, Image>();
	}
	
	public Image load(String path)
	{
		Image image = null;
		
		if (cache.containsKey(path))
		{
			return cache.get(path);
		}
		
		try 
		{
			image = ImageIO.read(new File(path));
			
			if (!cache.containsKey(path))
			{
				cache.put(path, image);
			}
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
