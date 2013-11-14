package ui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import ui.ToggleButton;
import ui.Window;

/**
 * This class simply has JButtons for the different colors
 * that the players can pick for their character. It is also 
 * inside the CharacterCreationPanel.
 */
@SuppressWarnings("serial")
public class ColorPickerPanel extends JPanel 
{
	private ToggleButton previousButton;
	private ArrayList<ToggleButton> buttons;
	private ArrayList<Color> blockedColors;
	
	/**
	 * This just has a row of JButtons that the players can click
	 * for their color. The buttons all share the same listener.
	 */
	public ColorPickerPanel()
	{	
		setPreferredSize(new Dimension(Window.WIDTH, 50));
	
		buttons = new ArrayList<ToggleButton>();
		blockedColors = new ArrayList<Color>();
		
		ToggleButton redButton = new ToggleButton("red", "assets/images/colors/redPaletteHighlight.png", "assets/images/colors/redPalette.png");
		redButton.addActionListener(new ColorListener());
		buttons.add(redButton);
		add(redButton);

		ToggleButton blueButton = new ToggleButton("blue", "assets/images/colors/bluePaletteHighlight.png", "assets/images/colors/bluePalette.png");
		blueButton.addActionListener(new ColorListener());
		buttons.add(blueButton);
		add(blueButton);

		ToggleButton greenButton = new ToggleButton("green", "assets/images/colors/greenPaletteHighlight.png", "assets/images/colors/greenPalette.png");
		greenButton.addActionListener(new ColorListener());
		buttons.add(greenButton);
		add(greenButton);

		ToggleButton blackButton = new ToggleButton("black", "assets/images/colors/blackPaletteHighlight.png", "assets/images/colors/blackPalette.png");
		blackButton.addActionListener(new ColorListener());
		buttons.add(blackButton);
		add(blackButton);
		
		previousButton = redButton;
		previousButton.turnOn();
	}
	
	public void blockColor(Color color)
	{
		blockedColors.add(color);
	}
	
	public Color getAvailableColor()
	{
		ArrayList<Color> possibleColors = new ArrayList<Color>();
		possibleColors.add(Color.RED);
		possibleColors.add(Color.BLUE);
		possibleColors.add(Color.GREEN);
		possibleColors.add(Color.BLACK);
		
		for (Color blockedColor : blockedColors)
		{
			for (Color possibleColor : possibleColors)
			{
				if (blockedColor == possibleColor)
				{
					possibleColors.remove(possibleColor);
					break;
				}
			}
		}
		
		if (possibleColors.size() <= 0)
		{
			return null;
		}
		
		return possibleColors.get(0);
	}
	
	public void setColor(Color color)
	{
		boolean buttonFound = false;
		for (ToggleButton button : buttons)
		{
			if (buttonFound)
			{
				button.turnOff();
				continue;
			}
			
			if (button.getAttribute("id").equals("red") && color == Color.RED)
			{
				buttonFound = true;
			}
			else if (button.getAttribute("id").equals("blue") && color == Color.BLUE)
			{
				buttonFound = true;
			}
			else if (button.getAttribute("id").equals("green") && color == Color.GREEN)
			{
				buttonFound = true;
			}
			else if (button.getAttribute("id").equals("black") && color == Color.BLACK)
			{
				buttonFound = true;
			}
			
			if (buttonFound)
			{
				button.turnOn();
				previousButton = button;
			}
			else
			{
				button.turnOff();
			}
		}
	}
	
	/**
	 * This inner class just represents the action Listeners that
	 * are used for the Jbuttons. It passes in the color to the
	 * GameSetupState and then later that data is used for a
	 * Session object.
	 */
	private class ColorListener implements ActionListener
	{
		/**
		 * This action listener figures out what color is 
		 * picked, passes it off to GameSetupState and 
		 * later is used for Session object.
		 */
		public void actionPerformed(ActionEvent e)
		{
			ToggleButton button = (ToggleButton)e.getSource();
			
			String name = button.getAttribute("id");
			Color color = Color.RED;
			
			switch (name)
			{
				case "red":
					color = Color.RED;
					break;
				case "blue":
					color = Color.BLUE;
					break;
				case "green":
					color = Color.GREEN;
					break;
				case "black":
					color = Color.BLACK;
					break;
			}
			
			for (Color c : blockedColors)
			{
				if (c == color)
				{
					return;
				}
			}
			
			if (previousButton == button)
			{
				return;
			}
			
			previousButton.toggle();
			button.toggle();
			
			
			Window window = Window.getInstance();
			PlayerCreationPanel panel = (PlayerCreationPanel)window.getPanel();
			panel.setCurrentColor(color);
			previousButton = button;
		}
	}
}
