package ui.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.Button;
import ui.Button.ButtonState;
import ui.render.Render;
import ui.render.StringRender;
import core.Callable;

/**
 * A RenderPanel is a JPanel that supports rendering 'Render'
 * objects and 'StringRender's. MouseInput is handle by this Panel
 * and can be captured in extending panels.
 */
public class RenderPanel extends JPanel implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 2374888784220516769L;
	
	/** List of Renders that are rendered this frame */
	protected CopyOnWriteArrayList<Render> renders;	
	
	/** List of StringRenders that are rendered this frame */
	protected CopyOnWriteArrayList<StringRender> stringRenders;	
	
	/** List of buttons to render */
	protected CopyOnWriteArrayList<Button> buttons;
	
	/** List of buttons that have mouse hover actions */
	private ArrayList<Button> hoverButtons;
	
	/** 
	 * List of commands that are executed on hover. 
	 * This list is parallel to hoverButtons. hoverButtons.get(0) will
	 * execute the command hoverCommands.get(0)
	 */
	private ArrayList<Callable> hoverCommands;
	
	/** 
	 * List of commands that are executed when the mouse is not hovering over this button. 
	 * This list is parallel to hoverButtons. hoverButtons.get(0) will
	 * execute the command hoverCommands.get(0)
	 */
	private ArrayList<Callable> unhoverCommands;

	/** List of buttons that have mouse press actions */
	private ArrayList<Button> pressButtons;
	
	/** 
	 * List of commands that are executed on press. 
	 * This list is parallel to hoverButtons. pressButtons.get(0) will
	 * execute the command pressButtons.get(0)
	 */
	private ArrayList<Callable> pressCommands;

	/** List of buttons that have mouse release actions */
	private ArrayList<Button> releaseButtons;
	
	/** 
	 * List of commands that are executed on release. 
	 * This list is parallel to releaseButtons. releaseButtons.get(0) will
	 * execute the command releaseButtons.get(0)
	 */
	private ArrayList<Callable> releaseCommands;
	
	public RenderPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		
		renders = new CopyOnWriteArrayList<>();
		stringRenders = new CopyOnWriteArrayList<>();
		buttons = new CopyOnWriteArrayList<>();
		
		hoverButtons = new ArrayList<>();
		hoverCommands = new ArrayList<>();
		unhoverCommands = new ArrayList<>();
		
		pressButtons = new ArrayList<>();
		pressCommands = new ArrayList<>();
		
		releaseButtons = new ArrayList<>();
		releaseCommands = new ArrayList<>();
	}
	
	/**
	 * Add a hover listener to a button. The commands to execute for each state are
	 * passed in as parameters
	 * @param button The button
	 * @param hoverCommand The command to execute on mouse hover
	 * @param unhoverCommand The command to execute on !mouse hover
	 */
	public void onHover(Button button, Callable hoverCommand, Callable unhoverCommand)
	{
		hoverButtons.add(button);
		hoverCommands.add(hoverCommand);
		unhoverCommands.add(unhoverCommand);
	}

	/**
	 * Add a press listener to a button. The commands to execute for each state are
	 * passed in as parameters
	 * @param button The button
	 * @param pressCommand The command to execute on mouse press
	 */
	public void onPress(Button button, Callable pressCommand)
	{
		pressButtons.add(button);
		pressCommands.add(pressCommand);
	}

	/**
	 * Add a release listener to a button. The commands to execute for each state are
	 * passed in as parameters
	 * @param button The button
	 * @param releaseCommand The command to execute on mouse release
	 */
	public void onRelease(Button button, Callable releaseCommand)
	{
		releaseButtons.add(button);
		releaseCommands.add(releaseCommand);
	}
	
	/** 
	 * Overridable method that is called on mouse clicks 
	 * @param x x position
	 * @param y y position
	 * @param isRightMouseButton Whether the right mouse button is down
	 */
	public void click(int x, int y, boolean isRightMouseButton) {}
	
	/**
	 * Overridable method that is called on mouse move
	 * @param x x position
	 * @param y y position
	 */
   	public void move(int x, int y) {}    
   	
   	/**
   	 * Overridable method that is called on mouse press
   	 * @param x x position
   	 * @param y y position
   	 */
   	public void press(int x, int y) {}
 
   	/**
   	 * Overridable method that is called on mouse release
   	 * @param x x position
   	 * @param y y position
   	 */
   	public void release(int x, int y) {}
	
   	/**
   	 * Overridable method that is called prior to paintComponent
   	 */
	public void preRender() {}
    
	/**
	 * Renders all objects in the render and string render lists
	 * @param g Graphics
	 */
	final public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        
		if (renders == null || stringRenders == null || buttons == null)
			return;
		
        preRender();
        
        for (Button button : buttons)
        {
        	renders.add(button.getRender());
        }
        
		for (Render render : renders)
		{
			for (Image image : render.getImages())
			{
				g.drawImage(image, render.x, render.y, null);
			}
		}
			
		for (StringRender string : stringRenders)
		{
			g.setColor(string.color);
			g.drawString(string.text, string.x, string.y);
		}
	
		renders.clear();
		stringRenders.clear();
	}

	/**
	 * Mouse release listener. Calls move(...) and 
	 * watches for button move commands
	 * @param e MouseEvent
	 */
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
    	
    	if (hoverButtons.size() > 0)
    		repaint();
	}

	/**
	 * Mouse release listener. Calls click(...) and 
	 * watches for button click commands
	 * @param e MouseEvent
	 */
	final public void mouseClicked(MouseEvent e) 
	{
		click(e.getX(), e.getY(), !SwingUtilities.isRightMouseButton(e));
	}

	/**
	 * Mouse release listener. Calls press(...) and 
	 * watches for button press commands
	 * @param e MouseEvent
	 */
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

    	if (pressButtons.size() > 0)
    		repaint();
	}

	/**
	 * Mouse release listener. Calls release(...) and 
	 * watches for button release commands
	 * @param e MouseEvent
	 */
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

    	if (releaseButtons.size() > 0)
    		repaint();
	}
	
	/** Not used */
	final public void mouseDragged(MouseEvent e) {}
	
	/** Not used */
	final public void mouseEntered(MouseEvent e) {}
	
	/** Not used */
	final public void mouseExited(MouseEvent e) {}
}
