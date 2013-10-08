package ui.panel;

import game.state.GameSetupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Window;
import core.StateSelector;

/**
 * 
 * @author grant
 * @author trevor
 */
@SuppressWarnings("serial")
public class SetNumPlayersPanel extends JPanel
{
	private JLabel title;

	public SetNumPlayersPanel() 
	{   
		title = new JLabel("Select number of Players");
		add(title);
		
		JButton button1 = new JButton("1");
		button1.addActionListener(new PlayerNumListener());
		add(button1);
		
		JButton button2 = new JButton("2");
		add(button2);
		button2.addActionListener(new PlayerNumListener());
		
		JButton button3 = new JButton("3");
		button3.addActionListener(new PlayerNumListener());
		add(button3);
		
		JButton button4 = new JButton("4");
		button4.addActionListener(new PlayerNumListener());
		add(button4);	
	}
	
	private class PlayerNumListener implements ActionListener 
	{	
		public void actionPerformed(ActionEvent e) 
		{
			JButton button = (JButton)e.getSource();
			String buttonText = button.getText();
			
			switch (buttonText) 
			{
				case "1":
					setNumPlayers(1);
					break;
				case "2":
					setNumPlayers(2);
					break;
				case "3":
					setNumPlayers(3);
					break;
				case "4":
					setNumPlayers(4);
					break;
			}
			
			Window window = Window.getInstance();
			window.setPanel(new CharacterCreationPanel());
		}
		
		private void setNumPlayers(int num)
		{
			StateSelector stateSelector = StateSelector.getInstance();
			GameSetupState state = (GameSetupState)stateSelector.getState();
			
			state.setNumPlayers(num);
		}
	}
}
