package ui.panel;

import game.Map;
import game.Plot;
import game.Session;
import game.state.GameState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import core.StateSelector;

/**
 * 
 * @author grant
 * @author trevor
 */
public class GamePanel extends JPanel implements MouseListener
{
	public GamePanel() 
	{
	}
	
	public void paintComponent(Graphics g)
	{
		StateSelector stateSelector = StateSelector.getInstance();
		GameState state = (GameState)stateSelector.getState();
		
		Session session = state.getSession();
		
		Map map = session.getMap();
		for (int a = 0; a < 5; a++)
		{
			for (int b = 0; b < 9; b++)
			{
				Plot plot = map.getPlot(a, b);
				g.drawImage(plot.getPlotImage(), plot.getY() * Plot.SIZE, plot.getX() * Plot.SIZE, null);
			}
		}
	}
	
    public void mouseClicked(MouseEvent e) 
    {
        // game.click(e.getX(), e.getY(), !SwingUtilities.isRightMouseButton(e));
        repaint();
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
