package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame 
{
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

        MenuPanel panel = new MenuPanel();
        add(panel);
	}
}
