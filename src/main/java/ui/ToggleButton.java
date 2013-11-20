package ui;

import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import core.ImageLoader;

public class ToggleButton extends JButton 
{
	private enum State 
	{
		ON, OFF
	}
	
	private ImageIcon onIcon;
	private ImageIcon offIcon;
	private ImageIcon currentIcon;
	
	private State state;
	
	private HashMap<String, String> attributes;
	
	public ToggleButton (String id, String onIconPath, String offIconPath)
	{	
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
		
		setFocusable(false);
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(SwingConstants.CENTER);
		
		ImageLoader imageLoader = ImageLoader.getInstance();
		onIcon = new ImageIcon(imageLoader.load(onIconPath));
		offIcon = new ImageIcon(imageLoader.load(offIconPath));
		
		attributes = new HashMap<>();
		attributes.put("id", id);
		
		turnOff();
	}
	
	public void setTitle(String text)
	{
		setText(text);
	}

    //method never used
	public void setAttribute(String key, String value)
	{
		attributes.put(key, value);
	}
	
	public String getAttribute(String key)
	{
		return attributes.get(key);
	}
	
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

	public void turnOn()
	{
		state = State.ON;
		currentIcon = onIcon;
		this.setIcon(currentIcon);
	}
	
	public void turnOff()
	{
		state = State.OFF;
		currentIcon = offIcon;
		this.setIcon(currentIcon);
	}
}
