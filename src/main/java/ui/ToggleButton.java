package ui;

import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import core.ImageLoader;

/**
 * This is a button class we use that can be thought
 * of as a "lightbulb" in that it can be clicked to be
 * set to on or off.
 * 
 * Two different images can be shown, one for 'on'
 * and one for 'off.'
 * 
 * @author trevor
 *
 */
public class ToggleButton extends JButton 
{
	private static final long serialVersionUID = -6363789140803173950L;

	private enum State 
	{
		ON, OFF
	}
	
	private ImageIcon onIcon;
	private ImageIcon offIcon;
	private ImageIcon currentIcon;
	
	private State state;
	
	private HashMap<String, String> attributes;
	
	/**
	 * The constructor for the ToggleButton. Every button has a 
	 * unique id, and two different paths to images that it displays
	 * depending on the state of the button.
	 * 
	 * @param id unique string id for the button
	 * @param onIconPath path to image to display when state is 'on'
	 * @param offIconPath path to image to display when state if 'off'
	 */
	public ToggleButton (String id, String onIconPath, String offIconPath)
	{	
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
		
		setFocusable(false);
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(SwingConstants.CENTER);
		
		ImageLoader imageLoader = new ImageLoader();
		onIcon = new ImageIcon(imageLoader.load(onIconPath));
		offIcon = new ImageIcon(imageLoader.load(offIconPath));
		
		attributes = new HashMap<>();
		attributes.put("id", id);
		
		turnOff();
	}
	
	/**
	 * Simple setter for the text on the button.
	 * 
	 * @param text string of text for the button to display
	 */
	public void setTitle(String text)
	{
		setText(text);
	}

    /**
     * Simple setter for attribute.
     * 
     * @param key key for the attribute
     * @param value value for attribute
     */
	public void setAttribute(String key, String value)
	{
		attributes.put(key, value);
	}
	
	/**
	 * This is a simple getter for the attribute
	 * 
	 * @param key the key to help get the value
	 * @return value of respective key
	 */
	public String getAttribute(String key)
	{
		return attributes.get(key);
	}
	
	/**
	 * Changes state of ToggleButton
	 */
	public void toggle()
	{
		if (state == State.ON)
		{
			turnOff();
		}
		else
		{
			turnOn();
		}
	}

	/**
	 * Sets the state of the button to 'on.'
	 */
	public void turnOn()
	{
		state = State.ON;
		currentIcon = onIcon;
		this.setIcon(currentIcon);
	}
	
	/**
	 * Sets the state of the button to 'off.'
	 */
	public void turnOff()
	{
		state = State.OFF;
		currentIcon = offIcon;
		this.setIcon(currentIcon);
	}
}
