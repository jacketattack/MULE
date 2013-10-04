package ui.panel;

import game.Map;
import game.Plot;
import game.Session;
import game.state.GameState;
import game.Character;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import core.StateSelector;

/**
 * 
 * @author grant
 * @author trevor
 */
public class GamePanel extends JPanel implements MouseListener
{
	private Image characterStatBackground;
	private static final String characterBackgroundPath = "assets/images/characterStatBackground.png";
	
	public GamePanel() 
	{
		try {
			characterStatBackground = ImageIO.read(new File(characterBackgroundPath));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		StateSelector stateSelector = StateSelector.getInstance();
		GameState state = (GameState)stateSelector.getState();
		
		
		Session session = state.getSession();
		ArrayList<Character> characters = session.getCharacters();
		
		Map map = session.getMap();
		for (int a = 0; a < 5; a++)
		{
			for (int b = 0; b < 9; b++)
			{
				Plot plot = map.getPlot(a, b);
				g.drawImage(plot.getPlotImage(), plot.getY() * Plot.SIZE, plot.getX() * Plot.SIZE, null);
			}
		}
		
		//Now draw player stats bar
		for (int i = 0 ; i < characters.size(); i++) {
			g.drawImage(characterStatBackground, i * 126, 350, null);
			Character character = characters.get(i);
			g.drawString(character.getName(), (i * 126) + 15, 362);
			
			//draw strings for character inventory
			g.drawString("$" + character.getMoney(), (i*126) + 10, 380);
			g.drawString("" + character.getOre(), (i*126) + 10, 400);
			g.drawString("" + character.getFood(), (i*126) + 10, 420);
			g.drawString("" + character.getCrystite(), (i*126) + 30, 400);
			g.drawString("" + character.getEnergy(), (i*126) + 30,  420);
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
