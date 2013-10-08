package ui.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Window;

/**
 * This class simply has Jbuttons for the different colors
 * that the players can pick for their character. It is also 
 * inside the CharacterCreationPanel.
 * 
 * @author trevor
 *
 */
@SuppressWarnings("serial")
public class ColorPickerPanel extends JPanel 
{
	private JButton redButton;
	private JButton blueButton;
	private JButton greenButton;
	private JButton blackButton;
	
	/**
	 * This just has a row of JButtons that the players can click
	 * for their color. The buttons all share the same listener.
	 */
	public ColorPickerPanel()
	{
		redButton = new JButton("Red");
		redButton.addActionListener(new ColorListener());
		add(redButton);

		blueButton = new JButton("Blue");
		blueButton.addActionListener(new ColorListener());
		add(blueButton);

		greenButton = new JButton("Green");
		greenButton.addActionListener(new ColorListener());
		add(greenButton);
		
		blackButton = new JButton("Black");
		blackButton.addActionListener(new ColorListener());
		add(blackButton);
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
			JButton button = (JButton)e.getSource();
			String name = button.getText();
			
			Color color = Color.RED;
			
			switch (name)
			{
				case "Red":
					color = Color.RED;
					break;
				case "Blue":
					color = Color.BLUE;
					break;
				case "Green":
					color = Color.GREEN;
					break;
				case "Black":
					color = Color.BLACK;
					break;
			}
			
			Window window = Window.getInstance();
			CharacterCreationPanel panel = (CharacterCreationPanel)window.getPanel();
			
			panel.setCurrentColor(color);
		}
	}
}
