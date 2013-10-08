package game.state;

import game.Session;
import game.round.LandGrantRound;
import game.round.Round;
import ui.Window;
import ui.panel.GamePanel;

/**
 * GameState runs the entire in-game experience. 
 * This class will manage the game 'rounds'
 * @author grant
 */
public class GameState implements State 
{
	/** The current game session*/
	private Session session;
	
	/** The current game round*/
	private Round currentRound;
	
	/**
	 * The game session is given to the GameState
	 * through this constructor
	 */
	public GameState(Session session)
	{	
		this.session = session;
		currentRound = new LandGrantRound(session);
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
	
	/** 
	 * Get the current game session
	 * @return the current game session
	 */
	public Session getSession()
	{
		return session;
	}
}
