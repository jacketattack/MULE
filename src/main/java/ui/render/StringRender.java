package ui.render;

public class StringRender 
{
	private String text;
	private int x;
	private int y;
	
	public StringRender()
	{
		this("", 0, 0);
	}
	
	public StringRender(String text, int x, int y)
	{
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getY()
	{
		return y;
	}
}
