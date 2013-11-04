package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Keyboard class handles all keyboard input during the game
 *
 *
 * @author Grant
 * @author Matt
 */

public class Keyboard implements KeyListener
{
	private static Keyboard instance;

	private boolean[] keys;
	
	private Keyboard()
	{
		keys = new boolean[256];
	}

    /**
     * Gets the Keyboard singleton
     * @return  the Singleton
     */
	public static Keyboard getInstance()
	{
		if (instance == null)
		{
			instance = new Keyboard();
		}
		
		return instance;
	}

    /**
     * Handles the even when a key is pressed
     * @param KeyEvent e the key event thrown when a key is pressed
     */
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length)
		{
			keys[e.getKeyCode()] = true;
		}
	}
    /**
     * Handles the even when a key is released
     * @param KeyEvent e the key event thrown when a key is released
     */
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length)
		{
			keys[e.getKeyCode()] = false;
		}
	}
    /**
     * Handles the even when a key is typed
     * @param KeyEvent e the key event thrown when a key is typed
     */
	public void keyTyped(KeyEvent e) {}


    /**
     * the isDown  method checks the backing array to see if an element is being pressed
     * @param n   the keycode to see if a given element is down
     * @return  whether or not the key is pressed down
     */
	public boolean isDown(int n)
	{
		if (n >= 0 && n < keys.length)
		{
			return keys[n];
		}
		
		return false;
	}
}
