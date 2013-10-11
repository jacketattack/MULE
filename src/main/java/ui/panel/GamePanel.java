package ui.panel;

import game.state.GameState;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import core.EventLoop;
import core.StateSelector;
import core.render.Renderable;
import core.render.RenderableString;

/**
 * This JPanel represents the base panel for displaying
 * the map and the players (and the store) info along the 
 * bottom. Many other JPanels extend this to start with this
 * layout as a primer.
 * 
 * @author grant
 * @author trevor
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener
{
	private Renderable[] renderables;
	private RenderableString[] renderableStrings;
	
	private RenderableString[] consoleStrings;
	private boolean displayConsole;
	
	/**
	 * The only thing needed here at the moment is loading the background
	 * for the player stat info located along the bottom of the JPanel.
	 */
	public GamePanel() 
	{
		addMouseListener(this);
	}
	
	public void draw(ArrayList<Renderable> renderables)
	{
		// Because the game is multi-threaded, we have to
		// copy our lists to avoid an ConcurrentModificationException.
		// I'll look into a better solution later
		
		this.renderables = new Renderable[renderables.size()];
		for (int a = 0; a < renderables.size(); a++)
		{
			this.renderables[a] = renderables.get(a);
		}
	}
	
	public void drawStrings(ArrayList<RenderableString> renderableStrings)
	{
		// Because the game is multi-threaded, we have to
		// copy our lists to avoid an ConcurrentModificationException.
		// I'll look into a better solution later

		this.renderableStrings = new RenderableString[renderableStrings.size()];
		for (int a = 0; a < renderableStrings.size(); a++)
		{
			this.renderableStrings[a] = renderableStrings.get(a);
		}
	}
	
	public void toggleConsole()
	{
		displayConsole = !displayConsole;
	}
	
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        
		if (renderables == null || renderableStrings == null)
			return;

		for (Renderable renderable : renderables)
		{
			for (Image image : renderable.getImages())
			{
				g.drawImage(image, renderable.getX(), renderable.getY(), null);
			}
		}
			
		for (RenderableString string : renderableStrings)
		{
			g.drawString(string.getText(), string.getX(), string.getY());
		}
		
		if (displayConsole)
		{
			renderConsole(g);
		}
	}
	
	// put in own class
	private void renderConsole(Graphics g)
	{
		consoleStrings = new RenderableString[2];
		
		EventLoop eventLoop = EventLoop.getInstance();
		long fps = eventLoop.getFPS();
		
		RenderableString fpsString = new RenderableString();
		fpsString.setText("fps: " + fps);
		fpsString.setX(10);
		fpsString.setY(20);
		
		consoleStrings[0] = fpsString;	
		
		StateSelector stateSelector = StateSelector.getInstance();
		GameState state = (GameState)stateSelector.getState();
		
		RenderableString roundString = new RenderableString();
		roundString.setText("round: " + state.getRound().getClass().getSimpleName());
		roundString.setX(10);
		roundString.setY(40);
		
		consoleStrings[1] = roundString;
		
		for (RenderableString string : consoleStrings)
		{
			g.drawString(string.getText(), string.getX(), string.getY());
		}
	}
	
	/**
	 * Later on we will develop this more but for now we just have a
	 * call to repaint.
	 */
    public void mouseClicked(MouseEvent e) 
    {
    	StateSelector stateSelector = StateSelector.getInstance();
    	GameState state = (GameState)stateSelector.getState();
    	
    	state.click(e.getX(), e.getY(), !SwingUtilities.isRightMouseButton(e));
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
