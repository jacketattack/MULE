package game;

public class RenderableString 
{
	private String text;
	private int x;
	private int y;
	
	public RenderableString(String text, int x, int y)
	{
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	public String getText()
	{
		return text;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
