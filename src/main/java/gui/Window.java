package gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame 
{
	public Window ()
	{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        GUIPanel panel = new GUIPanel();
        add(panel);
	}
}
