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
	private static ImageLoader instance;
	
	/** Cache of images with their paths as keys */
	private HashMap<String, Image> cache;
	
	private ImageLoader()
	{
		cache = new HashMap<String, Image>();
	}

    /**
     * This method loads an image and catches errors if image is unable to load
     *
     * @param The image path
     * @return The loaded image
     */
	public Image load(String path)
	{
		Image image = null;
		
		if (path.length() > 0 && path.charAt(0) != '/')
		{
			path = '/' + path;
		}
		
		if (cache.containsKey(path))
		{
			return cache.get(path);
		}
		
		try 
		{
			InputStream input = this.getClass().getResourceAsStream(path);
			
			if (input != null)
				image = ImageIO.read(input);
			else
				throw new IOException("could not load image " + path);
			
			if (!cache.containsKey(path))
			{
				cache.put(path, image);
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return image;
	}

    /**
     * Retrieves the ImageLoader singleton
     *
     * @return The ImageLoader signleton
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
