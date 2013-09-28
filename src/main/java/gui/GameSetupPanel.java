package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSetupPanel extends JPanel
{	
	private JLabel numPlayersPrompt;
	private JButton newGame;

	public GameSetupPanel() 
	{   
		numPlayersPrompt = new JLabel("Select number of Players");
		
        newGame = new JButton("DONE"); 
        add(newGame);
	}
}
