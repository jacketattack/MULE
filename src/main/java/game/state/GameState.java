package game.state;

import game.Callable;
import game.Session;
import game.round.DefaultRound;
import game.round.LandGrantRound;
import game.round.Round;
import ui.KeyboardListener;
import ui.Window;
import ui.panel.GamePanel;

/**
 * GameState runs the entire in-game experience. 
 * This class will manage the game 'rounds'
 * @author grant
 */
public class GameState implements State, Callable
{
	/** The current game session */
	private Session session;
	
	/** The current game round */
	private Round currentRound;
	
	/**
	 * The game session is given to the GameState
	 * through this constructor
	 */
	public GameState(Session session)
	{	
		this.session = session;
		currentRound = new LandGrantRound(session);

		KeyboardListener keyboardListener = KeyboardListener.getInstance();
		keyboardListener.addListener(this);
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
			// current round equals next round in line.
			// this will probably come from a stack or arraylist
			// someone sets up
			currentRound = new DefaultRound(session);
		}
		
		currentRound.update();
		
		GamePanel panel = (GamePanel)window.getPanel();
		
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
	
	/**
	 * Receive commands (from keyboard listener)
	 */
	public <T> void call(T object)
	{
		if (object instanceof Character && (Character)object == '`')
		{
			Window window = Window.getInstance();
			GamePanel panel = (GamePanel)window.getPanel();
			panel.toggleConsole();
		}
	}
}
