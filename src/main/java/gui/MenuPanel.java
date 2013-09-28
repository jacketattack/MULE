package gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	
	private JButton newGame;
	private JButton loadGame;
	private JButton credits;

	public MenuPanel() 
	{   
        newGame = new JButton("NEW GAME");
        loadGame = new JButton("LOAD GAME");
        credits = new JButton("CREDITS");
        
        add(newGame);
        add(loadGame);
        add(credits);
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
