package ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.panel.MenuPanel;

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

        currentPanel = new MenuPanel();
        add(currentPanel);
        
        pack();
	}
	
	public void setPanel(JPanel panel)
	{
		remove(currentPanel);
		currentPanel = panel;
		add(currentPanel);
		
		pack();
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
