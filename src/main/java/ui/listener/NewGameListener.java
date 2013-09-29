package ui.listener;

import game.state.GameSetupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Window;
import ui.panel.SetNumPlayersPanel;
import core.StateSelector;

/**
 * 
 * @author grant
 * @author trevor
 */
public class NewGameListener implements ActionListener 
{
	public void actionPerformed(ActionEvent e) 
	{
		GameSetupState state = new GameSetupState();
		SetNumPlayersPanel panel = new SetNumPlayersPanel();

		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(state);
		
		Window window = Window.getInstance();
		window.setPanel(panel);
	}
}
