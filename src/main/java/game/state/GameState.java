package game.state;

import game.Session;
import game.round.DefaultRound;
import game.round.DevelopmentRound;
import game.round.LandGrantRound;
import game.round.Round;

import java.util.ArrayList;

import ui.Window;
import ui.panel.GamePanel;
import core.Keyboard;

/**
 * GameState runs the entire in-game experience. 
 * This class will manage the game 'rounds'
 * @author grant
 */
public class GameState implements State
{
	/** The current game session */
	private Session session;
	
	/** The current game round */
	private Round currentRound;
	
	private ArrayList<Round> rounds;
	
	private Keyboard keyboard;
	
	/**
	 * The game session is given to the GameState
	 * through this constructor
	 */
	public GameState(Session session)
	{	
		this.session = session;
		
		rounds = new ArrayList<Round>();
		
		LandGrantRound landGrantRound = new LandGrantRound();
		landGrantRound.setSession(session);
		landGrantRound.init();
		rounds.add(landGrantRound);
		currentRound = rounds.get(0);
		
		rounds.add(new DevelopmentRound());
		
		keyboard = Keyboard.getInstance();
	}
	
	/**
	 * Communicate between rounds
	 */
	public void update() 
	{	
		Window window = Window.getInstance();
		
		if (window.getPanel() instanceof GamePanel == false)
		{
			return;
		}
		
		if (currentRound.isDone())
		{
			Round previousRound = rounds.remove(0);
			currentRound = null;
			
			if (rounds.size() > 0)
			{
				currentRound = rounds.get(0);
				currentRound.setSession(previousRound.getSession());
				currentRound.init();
			}

			if (currentRound == null)
			{
				currentRound = new DefaultRound();
			}
		}

		currentRound.update();
		
		GamePanel panel = (GamePanel)window.getPanel();

		if (keyboard.isDown(192))
		{
			panel.toggleConsole();
		}
		
		panel.draw(currentRound.getRenderables());
		panel.drawStrings(currentRound.getRenderableStrings());
		panel.repaint();
	}
	
	/**
	 * Alert the state that an mouse click occurred
	 * @param x The x pos in pixels
	 * @param y The y pos in pixels
	 * @param leftMouse Whether the left mouse was pressed
	 */
	public void click(int x, int y, boolean leftMouse)
	{
		currentRound.click(x, y, leftMouse);
	}
	
	public Round getRound()
	{
		return currentRound;
	}
	
	/** 
	 * Get the current game session
	 * @return the current game session
	 */
	public Session getSession()
	{
		return session;
	}
}
