package ui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import core.ImageLoader;

@SuppressWarnings("serial")
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
	
	public ToggleButton (String text, String onIconPath, String offIconPath)
	{	
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
		
		setText(text);
		setFocusable(false);
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(SwingConstants.CENTER);
		
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
