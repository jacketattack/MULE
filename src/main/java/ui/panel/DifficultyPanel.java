package ui.panel;

import game.Difficulty;
import game.state.GameSetupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Window;
import core.StateSelector;

/**
 * This class mirrors the design taken in the situation
 * of clicking JButtons, capturing the data in the button,
 * and passing that GameSetupState.
 * 
 * @author grant
 * @author trevor
 */
@SuppressWarnings("serial")
public class DifficultyPanel extends JPanel
{
	private JLabel title;
	private JButton beginner;
	private JButton standard;
	private JButton tournament;
	
	/**
	 * This constructor contains all the display for JButtons and the
	 * JLabel letting players know that they need to pick a difficulty.
	 * All buttons use the same listener. 
	 */
	public DifficultyPanel()
	{
		ButtonListener buttonListener = new ButtonListener();
		
		title = new JLabel("Difficulty");
		add(title);
		
		beginner = new JButton("Beginner");
		beginner.addActionListener(buttonListener);
		add(beginner);
		
		standard = new JButton("Standard");
		standard.addActionListener(buttonListener);
		add(standard);
		
		tournament = new JButton("Tournamanet");
		tournament.addActionListener(buttonListener);
		add(tournament);
	}
	
	/**
	 * This is the class for the action Listener for clicking on the
	 * JButtons. It handles logic needed when a button is clicked.
	 * 
	 * @author trevor
	 *
	 */
	private class ButtonListener implements ActionListener
	{
		/**
		 * This method captures the title of the button clicked and
		 * uses that information to figure out what button was clicked
		 * by the user. That difficulty is then passed back to
		 * GameSetupState in order to create a Session object later on.
		 */
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton)e.getSource();	
			String name = button.getText();
	
			Difficulty difficulty = Difficulty.BEGINNER;
			
			switch (name)
			{
				case "Beginner": 
					difficulty = Difficulty.BEGINNER;
					break;
				case "Standard":
					difficulty = Difficulty.STANDARD;
					break;
				case "Tournament":
					difficulty = Difficulty.TOURNAMENT;
					break;
			}

			StateSelector stateSelector = StateSelector.getInstance();
			GameSetupState state = (GameSetupState)stateSelector.getState();
			state.setDifficulty(difficulty);
			
			Window window = Window.getInstance();
			window.setPanel(new SetMapTypePanel());
		}
	}
}
