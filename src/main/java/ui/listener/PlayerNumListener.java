package ui.listener;

import game.state.GameSetupState;
import game.state.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.Window;
import ui.panel.CharacterCreationPanel;
import core.StateSelector;

/**
 * 
 * @author grant
 * @author trevor
 */
public class PlayerNumListener implements ActionListener 
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
