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
import ui.Button.ButtonState;
import ui.render.Render;
import ui.render.StringRender;
import core.Callable;

public class RenderPanel extends JPanel implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 2374888784220516769L;

	protected ArrayList<Render> renders;
	protected ArrayList<StringRender> stringRenders;
	protected ArrayList<Button> buttons;
	
	private ArrayList<Button> hoverButtons;
	private ArrayList<Callable> hoverCommands;
	private ArrayList<Callable> unhoverCommands;

	private ArrayList<Button> pressButtons;
	private ArrayList<Callable> pressCommands;

	private ArrayList<Button> releaseButtons;
	private ArrayList<Callable> releaseCommands;
	
	public RenderPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		
		renders = new ArrayList<Render>();
		stringRenders = new ArrayList<StringRender>();
		buttons = new ArrayList<Button>();
		
		hoverButtons = new ArrayList<Button>();
		hoverCommands = new ArrayList<Callable>();
		unhoverCommands = new ArrayList<Callable>();
		
		pressButtons = new ArrayList<Button>();
		pressCommands = new ArrayList<Callable>();
		
		releaseButtons = new ArrayList<Button>();
		releaseCommands = new ArrayList<Callable>();
	}
	
	public void onHover(Button button, Callable hoverCommand, Callable unhoverCommand)
	{
		hoverButtons.add(button);
		hoverCommands.add(hoverCommand);
		unhoverCommands.add(unhoverCommand);
	}
	
	public void onPress(Button button, Callable pressCommand)
	{
		pressButtons.add(button);
		pressCommands.add(pressCommand);
	}

	public void onRelease(Button button, Callable releaseCommand)
	{
		releaseButtons.add(button);
		releaseCommands.add(releaseCommand);
	}
	
	public void click(int x, int y, boolean isRightMouseButton) {}
   	public void move(int x, int y) {}    
   	public void press(int x, int y) {}
	public void release(int x, int y) {}
	public void preRender() {}
    
	final public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        
		if (renders == null || stringRenders == null)
			return;
		
        preRender();
        
		// copy everything to an immutable array
		// so we don't have concurrent modification
		Render[] renderArray = new Render[renders.size() + buttons.size()];
		StringRender[] stringRenderArray = new StringRender[stringRenders.size()];		
		for (int a = 0; a < renders.size(); a++)
		{
			renderArray[a] = renders.get(a);	
		}		
		
		for (int a = 0, b = 0; a < buttons.size(); a++, b++)
		{
			renderArray[a + renders.size()] = buttons.get(b).getRender();
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
		
    	for (int a = 0; a < hoverButtons.size(); a++)
    	{
    		Button button = hoverButtons.get(a);
    		
    		if (button.inBounds(e.getX(), e.getY()))
    		{
    			hoverCommands.get(a).call();
    		}
    		else
    		{
    			unhoverCommands.get(a).call();
    		}
    	}
    	
    	repaint();
	}
	
	final public void mouseClicked(MouseEvent e) 
	{
		click(e.getX(), e.getY(), !SwingUtilities.isRightMouseButton(e));
	}

	final public void mousePressed(MouseEvent e) 
	{
		press(e.getX(), e.getY());
		
    	for (int a = 0; a < pressButtons.size(); a++)
    	{
    		Button button = pressButtons.get(a);
    		
    		if (button.inBounds(e.getX(), e.getY()))
    		{
    			pressCommands.get(a).call();
    		}
    	}
    	
    	repaint();
	}

	final public void mouseReleased(MouseEvent e) 
	{
		release(e.getX(), e.getY());
		
    	for (int a = 0; a < releaseButtons.size(); a++)
    	{
    		Button button = releaseButtons.get(a);
    		
    		if (button.inBounds(e.getX(), e.getY()))
    		{
    			releaseCommands.get(a).call();
    		}
    		else
    		{
				button.setState(ButtonState.DEFAULT);
    		}
    	}
    	
    	repaint();
	}
	
	final public void mouseDragged(MouseEvent e) {}
	final public void mouseEntered(MouseEvent e) {}
	final public void mouseExited(MouseEvent e) {}
}
