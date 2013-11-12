package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class BackListener implements ActionListener 
{
	private JPanel panel;
	
	public BackListener(JPanel panel)
	{
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Window window = Window.getInstance();
		window.setPanel(panel);
	}
}
