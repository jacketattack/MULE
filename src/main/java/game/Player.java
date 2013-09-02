package game;

/**
 * 
 * @author grant
 * @author
 */
public class Player 
{
	public String name;
	public Character character;
	
	public Player()
	{
		name = "";
		character = new Character();
	}
	
	public void inputLeft()
	{
		character.moveLeft();
	}

	public void inputRight()
	{
		character.moveRight();
	}

	public void inputUp()
	{
		character.moveUp();
	}

	public void inputDown()
	{
		character.moveDown();
	}
	
	public void inputAct()
	{
		character.act();
	}
}
