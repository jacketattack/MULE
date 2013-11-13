package game.state;

import game.Session;
import game.round.AuctionRound;
import game.round.DefaultRound;
import game.round.DevelopmentRound;
import game.round.LandGrantRound;
import game.round.ProductionRound;
import game.round.Round;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import ui.Window;
import ui.panel.GamePanel;
import ui.panel.PausePanel;
import core.Keyboard;

/**
 * GameState runs the entire in-game experience. 
 * This class will manage the game 'rounds'
 * @author grant
 */
public class GameState implements State
{
	public static final int NUM_OF_ROUNDS = 12;
	
	private Session session;
	
	private Round currentRound;
	private ArrayList<Round> rounds;
	
	private Keyboard keyboard;

	private boolean paused;
	private int pauseDelay;
	
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

		for (int a = 0; a < NUM_OF_ROUNDS; a++)
		{
			DevelopmentRound developmentRound = new DevelopmentRound();
			developmentRound.setSession(session);
			developmentRound.init();
			
			AuctionRound auctionRound = new AuctionRound();
			auctionRound.setSession(session);
			auctionRound.init();
			
			ProductionRound productionRound = new ProductionRound();
			productionRound.setSession(session);
			productionRound.init();

			rounds.add(developmentRound);
			rounds.add(auctionRound);
			rounds.add(productionRound);
		}
		
		rounds.add(new DefaultRound());
		
		currentRound = rounds.get(session.getCurrentRound());
		
		for (int a = 0; a < session.getCurrentRound(); a++)
		{
			rounds.remove(0);
		}
		
		keyboard = Keyboard.getInstance();
		
		paused = false;
		pauseDelay = 0;

		session.forceSave();
	}
	
	/**
	 * Communicate between rounds
	 */
	public void update() 
	{	
		Window window = Window.getInstance();
		
		JPanel panel = window.getPanel();
		
		if (pauseDelay > 0)
			pauseDelay--;
		
		if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0)
		{	
			if (!paused)
			{
				paused = true;
				window.setPanel(new PausePanel());
			}
			else
			{
				paused = false;
				window.setPanel(new GamePanel());
			}
			
			pauseDelay = 20;
		}
		
		if (panel.getClass() != GamePanel.class)
		{
			paused = false;
		}
		
		if (paused)
			return;
		
		if (currentRound.isDone())
		{
			Round previousRound = rounds.remove(0);
			session.incrementRound();
			currentRound = null;
					
			if (rounds.size() > 0)
			{
				currentRound = rounds.get(0);
				currentRound.setSession(previousRound.getSession());
				currentRound.init();
			}
		}

		currentRound.update();
		
		if (panel instanceof GamePanel)
		{
			GamePanel gamePanel = (GamePanel)panel;
			gamePanel.draw(currentRound.getRenders());
			gamePanel.drawStrings(currentRound.getStringRenders());
			gamePanel.repaint();
		}
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
