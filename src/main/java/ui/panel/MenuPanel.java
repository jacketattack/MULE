package ui.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.listener.NewGameListener;

/**
 * 
 * @author grant
 * @author trevor
 */
public class MenuPanel extends JPanel
{	
	private JButton newGame;
	private JButton loadGame;
	private JButton credits;

	public MenuPanel() 
	{   
		newGame = new JButton("NEW GAME");
        newGame.addActionListener(new NewGameListener());
        add(newGame);
            
        loadGame = new JButton("LOAD GAME");
        add(loadGame);
        
        credits = new JButton("CREDITS");
        add(credits);
	}
}
