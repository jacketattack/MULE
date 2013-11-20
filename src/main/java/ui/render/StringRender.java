package ui.render;

import java.awt.Color;

/**
 * A StringRender is render that is purely made up
 * of a string of text. StringRenders are disposable 
 * objects that are intended to be created and destroyed often.
 */
public class StringRender 
{
	// There is no value restriction on the
	// x, y, text, and color. Therefore, they
	// have been left 'public' to avoid unnecessary
	// getters and setters
	public int x;
	public int y;
	public String text;
	public Color color;
	
	/**
	 * The default, empty constructor simply is an empty string
	 * of color black in position 0,0 (top left corner).
	 */
	public StringRender()
	{
		this("", 0, 0, Color.BLACK);
	}
	
	/**
	 * Constructor with text for the renderableString, coordinates
	 * x and y. Color is default black.
	 * 
	 * @param text String for the renderable string to display
	 * @param x x coordinate for top left corner of string (columns)
	 * @param y y coordinate for top left corner of string (rows)
	 */
	public StringRender(String text, int x, int y)
	{	
		this(text, x, y, Color.BLACK);
	}

	/**
	 * Constructor with text for the renderableString, coordinates
	 * x and y, and a specified color.
	 * 
	 * @param text String for the renderable string to display
	 * @param x x coordinate for top left corner of string (columns)
	 * @param y y coordinate for top left corner of string (rows)
	 * @param color The color object for the color of the text to be displayed
	 */
	public StringRender(String text, int x, int y, Color color)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
	}
}
