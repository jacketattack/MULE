package gui;

import game.state.GameSetupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.StateSelector;

public class NewGameListener implements ActionListener 
{
	public void actionPerformed(ActionEvent e) 
	{
		GameSetupState state = new GameSetupState();
		
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(state);
	}
}
