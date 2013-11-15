package ui.panel;

import game.state.GameState;

import java.util.ArrayList;

import ui.render.Render;
import ui.render.StringRender;
import core.StateSelector;

public class GamePanel extends RenderPanel 
{	
	private static final long serialVersionUID = -8162980471814220010L;

	public void draw(ArrayList<Render> renders)
	{	
		this.renders = renders;
	}
	
	public void drawStrings(ArrayList<StringRender> stringRenders)
	{
		this.stringRenders = stringRenders;
	}

	/**
	 * Forward mouse clicks to the current state
	 */
	public void click (int x, int y, boolean isRightMouseButton)
	{
		StateSelector stateSelector = StateSelector.getInstance();
		GameState state = (GameState)stateSelector.getState();
    		if (state != null)
			state.click(x, y, isRightMouseButton);
	}
}
