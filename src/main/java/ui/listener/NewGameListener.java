package ui.listener;

import game.state.GameSetupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Window;
import ui.panel.DifficultyPanel;
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
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(state);

		DifficultyPanel panel = new DifficultyPanel();
		Window window = Window.getInstance();
		window.setPanel(panel);
	}
}
