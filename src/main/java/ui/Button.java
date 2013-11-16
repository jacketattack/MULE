package ui;

import ui.render.Render;
import ui.render.Renderable;
import core.Callable;

public class Button implements Renderable 
{
	public enum ButtonState 
	{
		DEFAULT, HOVER, CLICK;
	}
	
	public final Callable HOVER_COMMAND = new Callable()
			{
				public void call()
				{
					setState(ButtonState.HOVER);
				}
			};
	
	public final Callable UNHOVER_COMMAND = new Callable()
	{
		public void call()
		{
			setState(ButtonState.DEFAULT);
		}
	};
	
	public final Callable PRESS_COMMAND = new Callable()
	{
		public void call()
		{
			setState(ButtonState.CLICK);
		}
	};
	
	private ButtonState state;
	
	private Render defaultRender;
	private Render hoverRender;
	private Render clickRender;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Button(String defaultRenderPath) 
	{	
		this(defaultRenderPath, defaultRenderPath, defaultRenderPath);
	}

	public Button(String defaultRenderPath, String hoverRenderPath)
	{
		this(defaultRenderPath, hoverRenderPath, hoverRenderPath);
	}
	
	public Button(String defaultRenderPath, String hoverRenderPath, String clickRenderPath)
	{
		state = ButtonState.DEFAULT;
		
		defaultRender = new Render();
		defaultRender.addImage(defaultRenderPath);

		hoverRender = new Render();
		hoverRender.addImage(hoverRenderPath);
		
		clickRender = new Render();
		clickRender.addImage(clickRenderPath);
	}
	
	public void setState(ButtonState state)
	{
		this.state = state;
	}
	
	public boolean inBounds(int x, int y)
	{
		if ( x > this.x && x < this.x + width && y > this.y && y < this.y + height)
		{
			return true;
		}
		
		return false;
	}
	
	public void setWidth(int width) 
	{
		this.width = width;
	}
	
	public void setHeight(int height) 
	{
		this.height = height;
	}
	
	public Render getRender()
	{
		Render render = defaultRender;
		
		switch (state)
		{
			case DEFAULT:
				render = defaultRender;
				break;
			case HOVER:
				render = hoverRender;
				break;
			case CLICK:
				render = clickRender;
				break;
		}
		
		render.x = x;
		render.y = y;
		render.width = width;
		render.height = height;
		return render;
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
