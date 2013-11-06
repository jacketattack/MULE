package core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Imageloader is a simple signleton class that handles loading all the images
 *
 *
 * @author Matt
 * @author Grant
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
     * This method load san image and catches errors if image is unable to laod
     *
     *
     * @param path
     * @return    the loaded image
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
			image = ImageIO.read(new File(path));
			
			if (!cache.containsKey(path))
			{
				cache.put(path, image);
			}
		} 
		catch (IOException e)
		{
            System.out.println(path);
			System.out.println(e);
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
