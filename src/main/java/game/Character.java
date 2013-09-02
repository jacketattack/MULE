package game;

import core.Utils;

/**
 * 
 * @author grant
 * @author
 */
public class Character 
{
	private Inventory inventory;
	private Plot centerPlot;
	
	public Character()
	{
		inventory = new Inventory();
	}
	
	public void update()
	{
		
	}
	
	public void moveLeft()
	{
		Utils.trace("move left");
	}
	
	public void moveRight()
	{
		Utils.trace("move right");
	}
	
	public void moveUp()
	{
		Utils.trace("move up");
	}
	
	public void moveDown()
	{
		Utils.trace("move down");
	}
	
	public void act()
	{
		Utils.trace("act");
	}
}
