package ui;

import game.state.MenuState;

import java.awt.Dimension;

import javax.swing.*;

import ui.panel.MenuPanel;
import core.Keyboard;
import core.StateSelector;

/**
 * This is a singleton JFrame that we use for the display.
 * 
 * We switch out the panel in this JFrame based on the current
 * game state and round.
 */
public class Window extends JFrame
{	
	private static final long serialVersionUID = -7539009001939040943L;
	
	public static final int WIDTH = 630;
	public static final int HEIGHT = 440;
	
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
		
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
		setFocusable(true);
		
		Keyboard keyboard = Keyboard.getInstance();
		addKeyListener(keyboard);
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

		StateSelector stateSelector = StateSelector.getInstance();
		MenuState state = new MenuState();
		stateSelector.setState(state);
        
        setPanel(new MenuPanel());
        
        pack();
	}
	
	/**
	 * Make the Window visible
	 */
	public void open()
	{
        setVisible(true);
	}
	
	/**
	 * Set the current panel
	 * @param panel the new panel for the Window
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
