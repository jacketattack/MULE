package game.screen;

import game.Character;
import game.Session;

import java.util.ArrayList;

import core.render.Renderable;
import core.render.RenderableString;

public abstract class Screen
{	
	protected Session session;	
	protected ArrayList<Renderable> renderables;
	protected ArrayList<RenderableString> renderableStrings;
	
	public Screen(Session session)
	{
		this.session = session;
		
		renderables = new ArrayList<Renderable>();
		renderableStrings = new ArrayList<RenderableString>();
	}
	
	public abstract void update();
	public abstract boolean shouldSwitch(Character character);
	
	public ArrayList<Renderable> getRenderables()
	{
		return renderables;
	}
	
	public ArrayList<RenderableString> getRenderableStrings()
	{
		return renderableStrings;
	}
}
