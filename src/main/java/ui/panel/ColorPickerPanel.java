package ui.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Window;

@SuppressWarnings("serial")
public class ColorPickerPanel extends JPanel 
{
	private JButton redButton;
	private JButton blueButton;
	private JButton greenButton;
	private JButton blackButton;
	
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
	
	private class ColorListener implements ActionListener
	{
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
