package ui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ui.ToggleButton;
import ui.Window;

/**
 * This class simply has JButtons for the different colors
 * that the players can pick for their character. It is also 
 * inside the CharacterCreationPanel.
 * 
 * @author trevor
 * @author grant
 */
@SuppressWarnings("serial")
public class ColorPickerPanel extends JPanel 
{
	private ToggleButton previousButton;
	
	/**
	 * This just has a row of JButtons that the players can click
	 * for their color. The buttons all share the same listener.
	 */
	public ColorPickerPanel()
	{
		setPreferredSize(new Dimension(Window.WIDTH, 50));
		
		ToggleButton redButton = new ToggleButton("red", "assets/images/colors/redPaletteHighlight.png", "assets/images/colors/redPalette.png");
		redButton.addActionListener(new ColorListener());
		add(redButton);

		ToggleButton blueButton = new ToggleButton("blue", "assets/images/colors/bluePaletteHighlight.png", "assets/images/colors/bluePalette.png");
		blueButton.addActionListener(new ColorListener());
		add(blueButton);

		ToggleButton greenButton = new ToggleButton("green", "assets/images/colors/greenPaletteHighlight.png", "assets/images/colors/greenPalette.png");
		greenButton.addActionListener(new ColorListener());
		add(greenButton);

		ToggleButton blackButton = new ToggleButton("black", "assets/images/colors/blackPaletteHighlight.png", "assets/images/colors/blackPalette.png");
		blackButton.addActionListener(new ColorListener());
		add(blackButton);
		
		previousButton = redButton;
		previousButton.turnOn();
	}
	
	/**
	 * This inner class just represents the action Listeners that
	 * are used for the Jbuttons. It passes in the color to the
	 * GameSetupState and then later that data is used for a
	 * Session object.
	 * 
	 * @author trevor
	 *
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
			
			if (previousButton == button)
			{
				return;
			}
			
			previousButton.toggle();
			button.toggle();
			
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
			
			Window window = Window.getInstance();
			PlayerCreationPanel panel = (PlayerCreationPanel)window.getPanel();
			
			panel.setCurrentColor(color);

			previousButton = button;
		}
	}
}
