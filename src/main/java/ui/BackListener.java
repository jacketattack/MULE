package ui;

import game.state.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import core.StateSelector;

public class BackListener implements ActionListener 
{
	private JPanel panel;
	private State state;
	
	public BackListener(JPanel panel)
	{
		this(panel, null);
	}
	
	public BackListener(JPanel panel, State state)
	{
		this.panel = panel;
		this.state = state;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Window window = Window.getInstance();
		window.setPanel(panel);
		
		if (state != null)
		{
			StateSelector stateSelector = StateSelector.getInstance();
			stateSelector.setState(state);
		}
	}
}
