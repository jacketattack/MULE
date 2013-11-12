package ui.panel;

import game.state.GameState;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.render.Render;
import ui.render.StringRender;
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
	private Render[] renders;
	private StringRender[] stringRenders;
	
	/**
	 * The only thing needed here at the moment is loading the background
	 * for the player stat info located along the bottom of the JPanel.
	 */
	public GamePanel() 
	{
		addMouseListener(this);
	}
	
	public void draw(ArrayList<Render> renders)
	{	
		this.renders = new Render[renders.size()];
		for (int a = 0; a < renders.size(); a++)
		{
			this.renders[a] = renders.get(a);
		}
	}
	
	public void drawStrings(ArrayList<StringRender> stringRenders)
	{
		this.stringRenders = new StringRender[stringRenders.size()];
		for (int a = 0; a < stringRenders.size(); a++)
		{
			this.stringRenders[a] = stringRenders.get(a);
		}
	}
	
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        
		if (renders == null || stringRenders == null)
			return;

		for (Render render : renders)
		{
			for (Image image : render.getImages())
			{
				g.drawImage(image, render.x, render.y, null);
			}
		}
			
		for (StringRender string : stringRenders)
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
