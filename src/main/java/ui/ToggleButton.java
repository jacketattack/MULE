package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
	
	public ToggleButton (String onIconPath, String offIconPath)
	{	
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		onIcon = new ImageIcon(imageLoader.load(onIconPath));
		offIcon = new ImageIcon(imageLoader.load(offIconPath));
		
		turnOff();
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
