package ui.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.listener.PlayerNumListener;

/**
 * 
 * @author grant
 * @author trevor
 */
public class SetNumPlayersPanel extends JPanel
{
	private JLabel title;

	public SetNumPlayersPanel() 
	{   
		title = new JLabel("Select number of Players");
		add(title);
		
		JButton button1 = new JButton("1");
		button1.addActionListener(new PlayerNumListener());
		add(button1);
		
		JButton button2 = new JButton("2");
		add(button2);
		button2.addActionListener(new PlayerNumListener());
		
		JButton button3 = new JButton("3");
		button3.addActionListener(new PlayerNumListener());
		add(button3);
		
		JButton button4 = new JButton("4");
		button4.addActionListener(new PlayerNumListener());
		add(button4);	
	}
}
