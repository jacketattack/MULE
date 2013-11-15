package ui.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.Button;
import ui.render.Render;
import ui.render.StringRender;

public class RenderPanel extends JPanel implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 2374888784220516769L;

	protected ArrayList<Render> renders;
	protected ArrayList<StringRender> stringRenders;
	protected ArrayList<Button> buttons;
	
	public RenderPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		
		renders = new ArrayList<Render>();
		stringRenders = new ArrayList<StringRender>();
		buttons = new ArrayList<Button>();
	}
	
	public void click(int x, int y, boolean isRightMouseButton) {}
   	public void move(int x, int y) {}    
   	public void press(int x, int y) {}
	public void release(int x, int y) {}
    
	final public void paintComponent(Graphics g)
	{
        	super.paintComponent(g);
        
		if (renders == null || stringRenders == null)
			return;
	
		// copy everything to an immutable array
		// so we don't have concurrent modification
		Render[] renderArray = new Render[renders.size()];
		StringRender[] stringRenderArray = new StringRender[stringRenders.size()];		
		for (int a = 0; a < renders.size(); a++)
		{
			renderArray[a] = renders.get(a);	
		}		

		for (int a = 0; a < stringRenders.size(); a++)
		{
			stringRenderArray[a] = stringRenders.get(a);
		}
	
		for (Render render : renderArray)
		{
			for (Image image : render.getImages())
			{
				g.drawImage(image, render.x, render.y, null);
			}
		}
			
		for (StringRender string : stringRenderArray)
		{
			g.drawString(string.getText(), string.getX(), string.getY());
		}
		
		renders.clear();
		stringRenders.clear();
	}

	final public void mouseMoved(MouseEvent e) 
 	{
		move(e.getX(), e.getY());
	}
	
	final public void mouseClicked(MouseEvent e) 
	{
		click(e.getX(), e.getY(), !SwingUtilities.isRightMouseButton(e));
	}

	final public void mousePressed(MouseEvent e) 
	{
		press(e.getX(), e.getY());
	}

	final public void mouseReleased(MouseEvent e) 
	{
		release(e.getX(), e.getY());
	}
	
	final public void mouseDragged(MouseEvent e) {}
	final public void mouseEntered(MouseEvent e) {}
	final public void mouseExited(MouseEvent e) {}
}
