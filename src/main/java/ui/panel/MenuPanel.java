package ui.panel;

import game.LocalSession;
import game.state.GameSetupState;
import ui.Button;
import ui.Window;
import ui.render.Render;
import core.Callable;
import core.StateSelector;

/**
 * MenuPanel is the view for the main menu
 */
public class MenuPanel extends RenderPanel
{
	private static final long serialVersionUID = -2030311112997794025L;

	private Render logo;
	private Render backgroundRender;
	
	private GameSetupState gameSetupState;

	public MenuPanel() 
	{ 
		gameSetupState = new GameSetupState(new LocalSession());
		
		backgroundRender = new Render();
		backgroundRender.addImage("assets/images/background.png");
		renders.add(backgroundRender);

		logo = new Render();
		logo.x = 210;
		logo.y = 40;
		logo.addImage("assets/images/logo.png");
		renders.add(logo);
		
		Button newGame = new Button("assets/images/buttons/startDefault.png", "assets/images/buttons/startHover.png", "assets/images/buttons/startClick.png");
		newGame.setWidth(160);
		newGame.setHeight(50);
		newGame.setX(230);
		newGame.setY(160);
		onHover(newGame, newGame.HOVER_COMMAND, newGame.UNHOVER_COMMAND);
		onPress(newGame, newGame.PRESS_COMMAND);
		onRelease(newGame, new Callable()
		{
			public void call()
			{
				createGame();
			}
		});
		buttons.add(newGame);

		Button loadGame = new Button("assets/images/buttons/loadDefault.png", "assets/images/buttons/loadHover.png", "assets/images/buttons/loadClick.png");
		loadGame.setWidth(160);
		loadGame.setHeight(50);
		loadGame.setX(230);
		loadGame.setY(260);
		onHover(loadGame, loadGame.HOVER_COMMAND, loadGame.UNHOVER_COMMAND);
		onPress(loadGame, loadGame.PRESS_COMMAND);
		onRelease(loadGame, new Callable()
		{
			public void call()
			{
				loadGame();
			}
		});
		buttons.add(loadGame);
		
		for (Button button : buttons)
		{		
			renders.add(button.getRender());
		}
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void createGame()
	{
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(gameSetupState);

		DifficultyPanel panel = new DifficultyPanel();
		Window window = Window.getInstance();
		window.setPanel(panel);
	}
	
	private void loadGame()
	{
		Window window = Window.getInstance();
		window.setPanel(new LoadPanel());
	}
	
    public void preRender() 
    {
		renders.add(backgroundRender);
		renders.add(logo);
    }    
}
