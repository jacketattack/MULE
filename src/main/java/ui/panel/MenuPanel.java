package ui.panel;

import game.state.GameSetupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Window;
import core.StateSelector;

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

	private class NewGameListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			GameSetupState state = new GameSetupState();
			StateSelector stateSelector = StateSelector.getInstance();
			stateSelector.setState(state);

			DifficultyPanel panel = new DifficultyPanel();
			Window window = Window.getInstance();
			window.setPanel(panel);
		}
	}
}
