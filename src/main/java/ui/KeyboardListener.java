package ui;

import game.Callable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardListener implements KeyListener
{
	private static KeyboardListener instance;
	
	private ArrayList<Callable> listeners;
	
	private KeyboardListener()
	{
		listeners = new ArrayList<Callable>();
	}
	
	public static KeyboardListener getInstance()
	{
		if (instance == null)
		{
			instance = new KeyboardListener();
		}
		
		return instance;
	}
	
	public void addListener(Callable object)
	{
		listeners.add(object);
	}
	
	public void keyTyped(KeyEvent e) 
	{
	}

	public void keyPressed(KeyEvent e) 
	{
	}

	public void keyReleased(KeyEvent e) 
	{
		for (Callable listener : listeners)
		{
			listener.call(e.getKeyChar());
		}
	}
}
