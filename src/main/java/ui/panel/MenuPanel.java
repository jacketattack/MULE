package ui.panel;

import game.state.GameSetupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Window;
import core.StateSelector;

/**
 * This is the initial JPanel that is set in the 
 * Window singleton JFrame. It simply has JButtons
 * new game, load game, and Credits. Later on we plan
 * on adding some background art.
 * 
 * @author grant
 * @author trevor
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel
{	
	private JButton newGame;
	private JButton loadGame;
	private JButton credits;

	/**
	 * This JPanel simply contains three JButtons:
	 * new game, load game, and credits. At the moment,
	 * only the new game button has an action listener.
	 */
	public MenuPanel() 
	{   
		newGame = new JButton("NEW GAME");
        newGame.addActionListener(new NewGameListener());
        add(newGame);
            
        loadGame = new JButton("LOAD GAME");
        loadGame.addActionListener(new LoadGameListener());
        add(loadGame);
        
        credits = new JButton("CREDITS");
        add(credits);
	}

	/**
	 * This private inner class is the action listener
	 * for the 'New Game' JButton listener. It handles process
	 * of beginning a new game setup.
	 * 
	 * @author trevor
	 *
	 */
	private class NewGameListener implements ActionListener 
	{
		/**
		 * This action Listener changes the current state to 
		 * GameSetupState as well as set the current panel to
		 * the Difficulty panel in order to begin collecting 
		 * settings for the game.s
		 */
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
	
	private class LoadGameListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			LoadPanel panel = new LoadPanel();
			Window window = Window.getInstance();
			window.setPanel(panel);
		}
	}
}
