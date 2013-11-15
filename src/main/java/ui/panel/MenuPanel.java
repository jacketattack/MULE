package ui.panel;

import game.state.GameSetupState;
import ui.Button;
import ui.Button.ButtonState;
import ui.Window;
import ui.render.Render;
import core.StateSelector;

/**
 * MenuPanel is the view for the main menu
 */
public class MenuPanel extends RenderPanel
{
	private static final long serialVersionUID = -2030311112997794025L;

	private Button newGame;
	private Button loadGame;
	private Render logo;
	
	private Render backgroundRender;

	public MenuPanel() 
	{ 
		logo = new Render();
		logo.x = 210;
		logo.y = 40;
		logo.addImage("assets/images/logo.png");
		
		newGame = new Button("assets/images/buttons/startDefault.png", "assets/images/buttons/startHover.png", "assets/images/buttons/startClick.png");
		newGame.setWidth(140);
		newGame.setHeight(70);
		newGame.setX(240);
		newGame.setY(140);
		buttons.add(newGame);

		loadGame = new Button("assets/images/buttons/loadDefault.png", "assets/images/buttons/loadHover.png", "assets/images/buttons/loadClick.png");
		loadGame.setWidth(140);
		loadGame.setHeight(70);
		loadGame.setX(240);
		loadGame.setY(240);
		buttons.add(loadGame);
		
		backgroundRender = new Render();
		backgroundRender.addImage("assets/images/background.png");
		
		addNonButtonRenders();
		for (Button button : buttons)
		{		
			renders.add(button.getRender());
		}
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void addNonButtonRenders()
	{
		renders.add(backgroundRender);
		renders.add(logo);
	}
	
	private void createGame()
	{
		GameSetupState state = new GameSetupState();
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(state);

		DifficultyPanel panel = new DifficultyPanel();
		Window window = Window.getInstance();
		window.setPanel(panel);
	}
	
	private void loadGame()
	{
		LoadPanel panel = new LoadPanel();
		Window window = Window.getInstance();
		window.setPanel(panel);
	}
	
    public void move(int x, int y) 
    {
    	addNonButtonRenders();
		
    	for (Button button : buttons)
    	{
    		if (button.inBounds(x, y))
    		{
    			button.setState(ButtonState.HOVER);
    		}
    		else
    		{
    			button.setState(ButtonState.DEFAULT);
    		}

    		renders.add(button.getRender());
    	}
    	
    	repaint();
    }    
    
    public void press(int x, int y) 
    {
    	addNonButtonRenders();
    	
    	for (Button button : buttons)
    	{
    		if (button.inBounds(x, y))
    		{
    			button.setState(ButtonState.CLICK);
    		}
    		else
    		{
    			button.setState(ButtonState.DEFAULT);
    		}

    		renders.add(button.getRender());
    	}
    	
    	repaint();
    }
    public void release(int x, int y) 
    {
    	addNonButtonRenders();
    	
    	for (Button button : buttons)
    	{
    		if (button.inBounds(x, y))
    		{
    			if (button == newGame)
    			{
    				createGame();
    			}
    			else if (button == loadGame)
    			{
    				loadGame();
    			}
    		}
    		else
    		{
    			button.setState(ButtonState.DEFAULT);
    		}
    		
    		renders.add(button.getRender());
    	}

    	repaint();
    }
}
