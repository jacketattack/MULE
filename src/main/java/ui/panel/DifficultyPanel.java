package ui.panel;

import game.Difficulty;
import game.state.GameSetupState;
import game.state.MenuState;

import javax.swing.JLabel;

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
	
	/**
	 * This constructor contains all the display for JButtons and the
	 * JLabel letting players know that they need to pick a difficulty.
	 * All buttons use the same listener. 
	 */
	public DifficultyPanel()
	{		
		JLabel title = new JLabel("difficulty");
		add(title);

		Button beginner = new Button("assets/images/buttons/beginnerDefault.png", "assets/images/buttons/beginnerHover.png", "assets/images/buttons/beginnerClick.png");
		beginner.setWidth(160);
		beginner.setHeight(50);
		beginner.setX(230);
		beginner.setY(100);
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
		
		Button standard = new Button("assets/images/buttons/standardDefault.png", "assets/images/buttons/standardHover.png", "assets/images/buttons/standardClick.png");
		standard.setWidth(160);
		standard.setHeight(50);
		standard.setX(230);
		standard.setY(170);
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
		
		Button tournament = new Button("assets/images/buttons/tournamentDefault.png", "assets/images/buttons/tournamentHover.png", "assets/images/buttons/tournamentClick.png");
		tournament.setWidth(160);
		tournament.setHeight(50);
		tournament.setX(230);
		tournament.setY(240);
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
		
		Button backButton = new Button("assets/images/buttons/backDefault.png", "assets/images/buttons/backHover.png", "assets/images/buttons/backClick.png");
		backButton.setWidth(71);
		backButton.setHeight(33);
		backButton.setX(539);
		backButton.setY(367);
		onHover(backButton, backButton.HOVER_COMMAND, backButton.UNHOVER_COMMAND);
		onPress(backButton, backButton.PRESS_COMMAND);
		onRelease(backButton, new Callable()
		{
			public void call()
			{
	            Window window = Window.getInstance();
	            window.setPanel(new MenuPanel());
	            
	            StateSelector stateSelector = StateSelector.getInstance();
	            stateSelector.setState(new MenuState());
	            	
			}
		});
		buttons.add(backButton);
	}
}
