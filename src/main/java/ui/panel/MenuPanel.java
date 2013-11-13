package ui.panel;

import game.state.GameSetupState;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import ui.Button;
import ui.Button.ButtonState;
import ui.Window;
import ui.render.Render;
import core.ImageLoader;
import core.StateSelector;

/**
 * MenuPanel is the view for the main menu
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements MouseListener, MouseMotionListener
{	
	private Button newGame;
	private Button loadGame;
	private Render logo;
	
	private ArrayList<Render> renders;
	private ArrayList<Button> buttons;

	public MenuPanel() 
	{   
		buttons = new ArrayList<Button>();
		renders = new ArrayList<Render>();
		
		logo = new Render();
		logo.x = 210;
		logo.y = 40;
		logo.addImage("assets/images/logo.png");
		renders.add(logo);
		
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
		
		for (Button button : buttons)
		{
			renders.add(button.getRender());
		}
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void addNonButtonRenders()
	{
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
	
	public void paintComponent(Graphics g)
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		Image background = imageLoader.load("assets/images/background.png");
		g.drawImage(background, 0, 0, null);
		
		for (Render render : renders)
		{
			for (Image image : render.getImages())
			{
				g.drawImage(image, render.x, render.y, null);
			}
		}
	}
    
    public void mouseMoved(MouseEvent e) 
    {
    	renders.clear();
    	addNonButtonRenders();
		
    	for (Button button : buttons)
    	{
    		if (button.inBounds(e.getX(), e.getY()))
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

    
    public void mousePressed(MouseEvent e) 
    {
    	renders.clear();
    	addNonButtonRenders();
    	
    	for (Button button : buttons)
    	{
    		if (button.inBounds(e.getX(), e.getY()))
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
    
    public void mouseReleased(MouseEvent e) 
    {
    	renders.clear();
    	addNonButtonRenders();
    	
    	for (Button button : buttons)
    	{
    		if (button.inBounds(e.getX(), e.getY()))
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

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
}
