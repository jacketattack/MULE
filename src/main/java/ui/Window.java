package ui;

import game.state.MenuState;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.panel.MenuPanel;
import core.StateSelector;

/**
 * 
 * @author grant
 * @author trevor
 */
public class Window extends JFrame 
{	
	private static Window instance;
	
	private JPanel currentPanel;
	
	private Window()
	{
		instance = this;
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        setPreferredSize(new Dimension(630, 440));
        setMinimumSize(new Dimension(630, 440));

		StateSelector stateSelector = StateSelector.getInstance();
		MenuState state = new MenuState();
		stateSelector.setState(state);
        
        setPanel(new MenuPanel());
        
        pack();
	}
	
	public void setPanel(JPanel panel)
	{
		if (currentPanel != null)
			remove(currentPanel);
		currentPanel = panel;
		add(currentPanel);
		
		pack();
	}
	
	public JPanel getPanel()
	{
		return currentPanel;
	}
	
	public static Window getInstance()
	{
		if (instance == null)
		{
			instance = new Window();
		}
		return instance;
	}
}
