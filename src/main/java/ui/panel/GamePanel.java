package ui.panel;

import game.state.GameState;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.render.Renderable;
import ui.render.RenderableString;
import core.StateSelector;

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
		this.renderables = new Renderable[renderables.size()];
		for (int a = 0; a < renderables.size(); a++)
		{
			this.renderables[a] = renderables.get(a);
		}
	}
	
	public void drawStrings(ArrayList<RenderableString> renderableStrings)
	{
		this.renderableStrings = new RenderableString[renderableStrings.size()];
		for (int a = 0; a < renderableStrings.size(); a++)
		{
			this.renderableStrings[a] = renderableStrings.get(a);
		}
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
