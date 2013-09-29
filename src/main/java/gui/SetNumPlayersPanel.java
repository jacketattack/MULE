package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SetNumPlayersPanel extends JPanel
{
	private JLabel numPlayersPrompt;

	public SetNumPlayersPanel() 
	{   
		numPlayersPrompt = new JLabel("Select number of Players");
		JButton button1 = new JButton("1");
		JButton button2 = new JButton("2");
		JButton button3 = new JButton("3");
		JButton button4 = new JButton("4");
		
		button1.addActionListener(new PlayerNumListener());
		button2.addActionListener(new PlayerNumListener());
		button3.addActionListener(new PlayerNumListener());
		button4.addActionListener(new PlayerNumListener());
		
		add(numPlayersPrompt);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
	}
}
