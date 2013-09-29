package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel
{	
	private JButton newGame;
	private JButton loadGame;
	private JButton credits;

	public MenuPanel() 
	{   
                newGame = new JButton("NEW GAME");
                newGame.addActionListener(new NewGameListener());
                
                loadGame = new JButton("LOAD GAME");
                credits = new JButton("CREDITS");
                
                add(newGame);
                add(loadGame);
                add(credits);
	}
}
