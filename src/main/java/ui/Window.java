package ui;

import game.state.MenuState;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.panel.MenuPanel;
import core.Keyboard;
import core.StateSelector;

/**
 * This is a singleton JFrame that we use for the display.
 * 
 * We switch out the panel in this JFrame based on the current
 * game state and round.
 * 
 * @author grant
 * @author trevor
 */
@SuppressWarnings("serial")
public class Window extends JFrame
{	
	private static Window instance;
	
	private JPanel currentPanel;
	
	/**
	 * This Constructor is called only when the application
	 * is first started up and it builds the JFrame as well
	 * as setting the initial JPanel to be for the Menu display.
	 */
	private Window()
	{
		instance = this;
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
		setFocusable(true);
		
		Keyboard keyboard = Keyboard.getInstance();
		addKeyListener(keyboard);
        
        setPreferredSize(new Dimension(630, 440));
        setMinimumSize(new Dimension(630, 440));

		StateSelector stateSelector = StateSelector.getInstance();
		MenuState state = new MenuState();
		stateSelector.setState(state);
        
        setPanel(new MenuPanel());
        
        pack();
	}
	
	/**
	 * 
	 * @param panel
	 */
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
	
	/**
	 * This is the core functionality of the singleton 
	 * in that this static method returns the Window
	 * singleton so that other classes may change the 
	 * panel.
	 * 
	 * @return The Window object that is the singleton. 
	 */
	public static Window getInstance()
	{
		if (instance == null)
		{
			instance = new Window();
		}
		return instance;
	}
}
