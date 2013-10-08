package ui.panel;

import game.Renderable;
import game.RenderableString;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * @author grant
 * @author trevor
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener
{
	private ArrayList<Renderable> renderables;
	private ArrayList<RenderableString> renderableStrings;
	
	public GamePanel() 
	{
		
	}
	
	public void draw(ArrayList<Renderable> renderables)
	{
		this.renderables = renderables;
		repaint();
	}
	
	public void drawStrings(ArrayList<RenderableString> renderableStrings)
	{
		this.renderableStrings = renderableStrings;
	}
	
	public void paintComponent(Graphics g)
	{
		if (renderables == null)
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
		
		//Now draw player stats bar
//		for (int i = 0 ; i < characters.size(); i++) 
//		{
//			g.drawImage(characterStatBackground, i * 126, 350, null);
//			Character character = characters.get(i);
//			g.drawString(character.getName(), (i * 126) + 15, 362);
//			
//			//draw strings for character inventory
//			g.drawString("$" + character.getMoney(), (i*126) + 10, 380);
//			g.drawString("" + character.getOre(), (i*126) + 10, 400);
//			g.drawString("" + character.getFood(), (i*126) + 10, 420);
//			g.drawString("" + character.getCrystite(), (i*126) + 30, 400);
//			g.drawString("" + character.getEnergy(), (i*126) + 30,  420);
//		}
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
