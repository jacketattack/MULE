package core;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Imageloader is a simple signleton class that handles loading all the images
 */

public class ImageLoader 
{
	private HashMap<String, Image> cache;
	
	private static ImageLoader instance;
	
	private ImageLoader()
	{
		cache = new HashMap<String, Image>();
	}

    /**
     * This method loads an image and catches errors if image is unable to load
     * 
     * @param path
     * @return the loaded image
     */
	public Image load(String path)
	{
		Image image = null;
		
		if (cache.containsKey(path))
		{
			return cache.get(path);
		}
		
		try 
		{
			InputStream input = this.getClass().getResourceAsStream(path);
			image = ImageIO.read(input);
			
			if (!cache.containsKey(path))
			{
				cache.put(path, image);
			}
		} 
		catch (IOException e)
		{
		}
		
		return image;
	}

    /**
     * retrieves the ImageLoader singleton
     *
     * @return the ImageLoader signleton
     */
	public static ImageLoader getInstance()
	{
		if (instance == null)
		{
			instance = new ImageLoader();
		}
		
		return instance;
	}
}
