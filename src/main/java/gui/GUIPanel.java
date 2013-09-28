package gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUIPanel extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;

	public GUIPanel () 
	{
        setPreferredSize(new Dimension(Plot.SIZE * rows, Tile.SIZE * columns));
	}
	
    public void mouseClicked(MouseEvent e) 
    {
        game.click(e.getX(), e.getY(), !SwingUtilities.isRightMouseButton(e));
        repaint();
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
