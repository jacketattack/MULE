package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Window extends JFrame 
{
	public static Window instance;
	
	private JPanel currentPanel;
	
	public Window getInstance()
	{
		if (instance == null)
		{
			instance = new Window();
		}
		
		return instance;
	}
	
	public static void main(String[] args)
	{
		new Window();
	}
	
	public Window ()
	{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        setPreferredSize(new Dimension(630, 440));
        setMinimumSize(new Dimension(630, 440));

        currentPanel = new MenuPanel();
        add(currentPanel);
	}
	
	public void setPanel(JPanel panel)
	{
		remove(currentPanel);
		currentPanel = panel;
		add(currentPanel);
	}
}
