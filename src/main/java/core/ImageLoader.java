package core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader 
{
	// cache
	private HashMap<String, Image> hashMap;
	
	private static ImageLoader instance;
	
	private ImageLoader()
	{
		hashMap = new HashMap<String, Image>();
	}
	
	public Image load(String path)
	{
		Image image = null;
		
		if (hashMap.containsKey(path))
		{
			return hashMap.get(path);
		}
		
		try 
		{
			image = ImageIO.read(new File(path));
			
			if (!hashMap.containsKey(path))
			{
				hashMap.put(path, image);
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
