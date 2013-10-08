package ui.panel;

import game.Renderable;
import game.RenderableString;
import game.state.GameState;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import core.StateSelector;

/**
 * 
 * @author grant
 * @author trevor
 * @param <T>
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener
{
	private ArrayList<Renderable> renderables;
	private ArrayList<RenderableString> renderableStrings;
	
	public GamePanel() 
	{
		addMouseListener(this);	
	}
	
	public void draw(ArrayList<Renderable> renderables)
	{
		// Because the game is multi-threaded, we have to deep
		// copy our lists to avoid an ConcurrentModificationException.
		// I'll look into a better solution later
		
		this.renderables = new ArrayList<Renderable>();
		for (Renderable r : renderables)
		{
			this.renderables.add(r);
		}
	}
	
	public void drawStrings(ArrayList<RenderableString> renderableStrings)
	{
		// Because the game is multi-threaded, we have to deep
		// copy our lists to avoid an ConcurrentModificationException.
		// I'll look into a better solution later
		
		this.renderableStrings = new ArrayList<RenderableString>();
		for (RenderableString r : renderableStrings)
		{
			this.renderableStrings.add(r);
		}
	}
	
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        
		if (renderables == null)// || renderableStrings == null)
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
