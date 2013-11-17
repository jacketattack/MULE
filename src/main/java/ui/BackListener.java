package ui;

import game.state.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import core.StateSelector;

public class BackListener<T extends JPanel, S extends State> implements ActionListener 
{
	private Class<T> panelClass;
	private Class<S> stateClass;
	
	public BackListener(Class<T> panelClass)
	{
		this(panelClass, null);
	}
	
	public BackListener(Class<T> panelClass, Class<S> stateClass)
	{
		this.panelClass = panelClass;
		this.stateClass = stateClass;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Window window = Window.getInstance();
		try 
		{
			window.setPanel(panelClass.newInstance());
		} 
		catch (InstantiationException e2) 
		{
		} 
		catch (IllegalAccessException e2) 
		{
		}
		
		if (stateClass != null)
		{
			StateSelector stateSelector = StateSelector.getInstance();
			
			try 
			{
				stateSelector.setState(stateClass.newInstance());
			} 
			catch (InstantiationException e1)
			{
			} 
			catch (IllegalAccessException e1) 
			{
			}
		}
	}
}
