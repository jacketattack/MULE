package ui.panel;

import game.Difficulty;
import game.state.GameSetupState;
import game.state.MenuState;

import javax.swing.JButton;
import javax.swing.JLabel;

import ui.BackListener;
import ui.Button;
import ui.Window;
import core.Callable;
import core.StateSelector;

/**
 * This class mirrors the design taken in the situation
 * of clicking JButtons, capturing the data in the button,
 * and passing that GameSetupState.
 */
public class DifficultyPanel extends RenderPanel
{
	private static final long serialVersionUID = 2915261277394603626L;

	private JLabel title;
	private Button beginner;
	private Button standard;
	private Button tournament;
	
	/**
	 * This constructor contains all the display for JButtons and the
	 * JLabel letting players know that they need to pick a difficulty.
	 * All buttons use the same listener. 
	 */
	public DifficultyPanel()
	{		
		title = new JLabel("difficulty");
		add(title);

		beginner = new Button("assets/images/buttons/loadDefault.png", "assets/images/buttons/loadHover.png", "assets/images/buttons/loadClick.png");
		beginner.setWidth(160);
		beginner.setHeight(50);
		beginner.setX(230);
		beginner.setY(140);
		onHover(beginner, beginner.HOVER_COMMAND, beginner.UNHOVER_COMMAND);
		onPress(beginner, beginner.PRESS_COMMAND);
		onRelease(beginner, new Callable()
		{
			public void call()
			{
				StateSelector stateSelector = StateSelector.getInstance();
				GameSetupState state = (GameSetupState)stateSelector.getState();
				state.setDifficulty(Difficulty.STANDARD);
				
				Window window = Window.getInstance();
				window.setPanel(new MapTypePanel());
			}
		});
		buttons.add(beginner);
		
		standard = new Button("assets/images/buttons/loadDefault.png", "assets/images/buttons/loadHover.png", "assets/images/buttons/loadClick.png");
		standard.setWidth(160);
		standard.setHeight(50);
		standard.setX(230);
		standard.setY(210);
		onHover(standard, standard.HOVER_COMMAND, standard.UNHOVER_COMMAND);
		onPress(standard, standard.PRESS_COMMAND);
		onRelease(standard, new Callable()
		{
			public void call()
			{
				StateSelector stateSelector = StateSelector.getInstance();
				GameSetupState state = (GameSetupState)stateSelector.getState();
				state.setDifficulty(Difficulty.TOURNAMENT);
				
				Window window = Window.getInstance();
				window.setPanel(new MapTypePanel());
			}
		});
		buttons.add(standard);
		
		tournament = new Button("assets/images/buttons/loadDefault.png", "assets/images/buttons/loadHover.png", "assets/images/buttons/loadClick.png");
		tournament.setWidth(160);
		tournament.setHeight(50);
		tournament.setX(230);
		tournament.setY(280);
		onHover(tournament, tournament.HOVER_COMMAND, tournament.UNHOVER_COMMAND);
		onPress(tournament, tournament.PRESS_COMMAND);
		onRelease(tournament, new Callable()
		{
			public void call()
			{
				StateSelector stateSelector = StateSelector.getInstance();
				GameSetupState state = (GameSetupState)stateSelector.getState();
				state.setDifficulty(Difficulty.BEGINNER);
				
				Window window = Window.getInstance();
				window.setPanel(new MapTypePanel());
			}
		});
		buttons.add(tournament);
		
		JButton backBtn = new JButton("back");
		BackListener<MenuPanel, MenuState> backListener = new BackListener<MenuPanel, MenuState>(MenuPanel.class, MenuState.class);
		backBtn.addActionListener(backListener);
		add(backBtn);
	}
}
