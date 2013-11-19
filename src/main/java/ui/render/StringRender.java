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
	
	public StringRender()
	{
		this("", 0, 0, Color.BLACK);
	}
	
	public StringRender(String text, int x, int y)
	{	
		this(text, x, y, Color.BLACK);
	}

	public StringRender(String text, int x, int y, Color color)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
	}
}
