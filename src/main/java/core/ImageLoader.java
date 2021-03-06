package core;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * ImageLoader is a simple singleton class that handles loading all the images
 */
public class ImageLoader 
{       
    /** Cache of images with their paths as keys */
    private static HashMap<String, Image> cache = new HashMap<>();

    /**
     * This method loads an image and catches errors if image is unable to load
     *
     * @param path the image path
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
}
