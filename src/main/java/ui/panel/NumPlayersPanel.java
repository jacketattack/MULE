package ui.panel;

import game.state.GameSetupState;

import javax.swing.JLabel;

import ui.Button;
import ui.Window;
import core.Callable;
import core.StateSelector;

/**
 * This simple JPanel has JButtons letting the 
 * user pick how many players will be in this game.
 * It is apart of the step in GameSetup.
 */
public class NumPlayersPanel extends RenderPanel
{
	private static final long serialVersionUID = 4089186439341591567L;
	
	/**
	 * This has a simple JLabel and the 4 JButtons for 
	 * 1-4 players. All buttons use the same listener.
	 */
	public NumPlayersPanel() 
	{   
		JLabel title = new JLabel("select number of players");
		add(title);
		
		Button button1 = new Button("assets/images/buttons/1Default.png", "assets/images/buttons/1Hover.png", "assets/images/buttons/1Click.png");
		button1.setWidth(160);
		button1.setHeight(50);
		button1.setX(230);
		button1.setY(80);
		onHover(button1, button1.HOVER_COMMAND, button1.UNHOVER_COMMAND);
		onPress(button1, button1.PRESS_COMMAND);
		onRelease(button1, new Callable()
		{
			public void call()
			{
				StateSelector stateSelector = StateSelector.getInstance();
				GameSetupState state = (GameSetupState)stateSelector.getState();
				state.setNumPlayers(1);
				
				Window window = Window.getInstance();
				window.setPanel(new PlayerCreationPanel());
			}
		});
		buttons.add(button1);
		
		Button button2 = new Button("assets/images/buttons/2Default.png", "assets/images/buttons/2Hover.png", "assets/images/buttons/2Click.png");
		button2.setWidth(160);
		button2.setHeight(50);
		button2.setX(230);
		button2.setY(150);
		onHover(button2, button2.HOVER_COMMAND, button2.UNHOVER_COMMAND);
		onPress(button2, button2.PRESS_COMMAND);
		onRelease(button2, new Callable()
		{
			public void call()
			{
				StateSelector stateSelector = StateSelector.getInstance();
				GameSetupState state = (GameSetupState)stateSelector.getState();
				state.setNumPlayers(2);
				
				Window window = Window.getInstance();
				window.setPanel(new PlayerCreationPanel());
			}
		});
		buttons.add(button2);
		
		Button button3 = new Button("assets/images/buttons/3Default.png", "assets/images/buttons/3Hover.png", "assets/images/buttons/3Click.png");
		button3.setWidth(160);
		button3.setHeight(50);
		button3.setX(230);
		button3.setY(220);
		onHover(button3, button3.HOVER_COMMAND, button3.UNHOVER_COMMAND);
		onPress(button3, button3.PRESS_COMMAND);
		onRelease(button3, new Callable()
		{
			public void call()
			{
				StateSelector stateSelector = StateSelector.getInstance();
				GameSetupState state = (GameSetupState)stateSelector.getState();
				state.setNumPlayers(3);
				
				Window window = Window.getInstance();
				window.setPanel(new PlayerCreationPanel());
			}
		});
		buttons.add(button3);
		
		Button button4 = new Button("assets/images/buttons/4Default.png", "assets/images/buttons/4Hover.png", "assets/images/buttons/4Click.png");
		button4.setWidth(160);
		button4.setHeight(50);
		button4.setX(230);
		button4.setY(290);
		onHover(button4, button4.HOVER_COMMAND, button4.UNHOVER_COMMAND);
		onPress(button4, button4.PRESS_COMMAND);
		onRelease(button4, new Callable()
		{
			public void call()
			{
				StateSelector stateSelector = StateSelector.getInstance();
				GameSetupState state = (GameSetupState)stateSelector.getState();
				state.setNumPlayers(4);
				
				Window window = Window.getInstance();
				window.setPanel(new PlayerCreationPanel());
			}
		});
		buttons.add(button4);

        
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
	            window.setPanel(new MapTypePanel());
	            	
			}
		});
		buttons.add(backButton);
	}
}
