package game.screen;

import game.Session;

import java.util.ArrayList;

import core.render.Renderable;
import core.render.RenderableString;

public abstract class Screen
{	
	protected Session session;	
	protected ArrayList<Renderable> renderables;
	protected ArrayList<RenderableString> renderableStrings;
	protected String playerId;
	
	public Screen(Session session)
	{
		this.session = session;
		
		renderables = new ArrayList<Renderable>();
		renderableStrings = new ArrayList<RenderableString>();
	}
	
	public abstract void update();
	public abstract boolean shouldSwitch();
	
	public void setPlayerId(String id)
	{
		playerId = id;
	}
	
	public ArrayList<Renderable> getRenderables()
	{
		return renderables;
	}
	
	public ArrayList<RenderableString> getRenderableStrings()
	{
		return renderableStrings;
	}
}
