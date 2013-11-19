package ui.render;

import java.awt.Image;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import core.ImageLoader;

/**
 * A Render is a composition of images that are
 * drawn at a specific x/y position. Renders are
 * disposable objects that are intented to be created 
 * and destroyed often.
 */
public class Render
{
	// There is no value restriction on the
	// x, y, width, and height. Therefore, they
	// have been left 'public' to avoid unnecessary
	// getters and setters
	public int x;
	public int y;
	public int width;
	public int height;
	
	/** File path for all the images that compose this render */
	private ArrayList<String> imagePaths;

	public Render()
	{
		imagePaths = new ArrayList<String>();
	}
	
	/**
	 * Add a image to the render. All images form a composite
	 * and are shown on top of each other by order of insertion
	 * @param imagePath The path for this image
	 */
	public void addImage(String imagePath)
	{		
		// cache image
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.load(imagePath);
		
		imagePaths.add(imagePath);
	}
	
	/**
	 * Remove all image paths
	 */
	public void clearImages()
	{
		imagePaths = new ArrayList<String>();
	}

	public CopyOnWriteArrayList<Image> getImages() 
	/**
	 * Get a list of all images that make up the render. The
	 * images should be drawn on top of each other with the first
	 * at the bottom.
	 * @return A list of images
	 */
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		CopyOnWriteArrayList<Image> images = new CopyOnWriteArrayList<Image>();

		for (String path : imagePaths)
		{		
			Image image = imageLoader.load(path);
			images.add(image);
		}
		
		return images;
	}
}
