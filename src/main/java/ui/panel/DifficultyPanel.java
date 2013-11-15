package ui.panel;

import game.Difficulty;
import game.state.GameSetupState;
import game.state.MenuState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.BackListener;
import ui.Window;
import core.StateSelector;

/**
 * This class mirrors the design taken in the situation
 * of clicking JButtons, capturing the data in the button,
 * and passing that GameSetupState.
 */
public class DifficultyPanel extends JPanel
{
	private static final long serialVersionUID = 2915261277394603626L;

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
		
		title = new JLabel("difficulty");
		add(title);
		
		beginner = new JButton("beginner");
		beginner.addActionListener(buttonListener);
		add(beginner);
		
		standard = new JButton("standard");
		standard.addActionListener(buttonListener);
		add(standard);
		
		tournament = new JButton("tournamanet");
		tournament.addActionListener(buttonListener);
		add(tournament);
		
		JButton backBtn = new JButton("back");
		BackListener<MenuPanel, MenuState> backListener = new BackListener<MenuPanel, MenuState>(MenuPanel.class, MenuState.class);
		backBtn.addActionListener(backListener);
		add(backBtn);
	}
	
	/**
	 * This is the class for the action Listener for clicking on the
	 * JButtons. It handles logic needed when a button is clicked
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
				case "beginner": 
					difficulty = Difficulty.BEGINNER;
					break;
				case "standard":
					difficulty = Difficulty.STANDARD;
					break;
				case "tournament":
					difficulty = Difficulty.TOURNAMENT;
					break;
			}

			StateSelector stateSelector = StateSelector.getInstance();
			GameSetupState state = (GameSetupState)stateSelector.getState();
			state.setDifficulty(difficulty);
			
			Window window = Window.getInstance();
			window.setPanel(new MapTypePanel());
		}
	}
}
