package game.round;

import game.Character;
import game.Map;
import game.Plot;
import game.RenderableString;
import game.Session;
import game.SimpleRender;

import java.util.ArrayList;

/**
 * 
 * @author grant
 * @author
 */
public class LandGrantRound extends Round
{	
	private ArrayList<SimpleRender> characterOverviews;
	
	public LandGrantRound(Session session)
	{	
		super(session);
		
		characterOverviews = new ArrayList<SimpleRender>();
		characterOverviews.add(new SimpleRender("assets/images/characterStatBackground.png"));
		characterOverviews.add(new SimpleRender("assets/images/characterStatBackground.png"));
		characterOverviews.add(new SimpleRender("assets/images/characterStatBackground.png"));
		characterOverviews.add(new SimpleRender("assets/images/characterStatBackground.png"));
	}

	/**
	 * Alert the state that an mouse click occurred
	 * @param x The x pos in pixels
	 * @param y The y pos in pixels
	 * @param leftMouse Whether the left mouse was pressed
	 */
	public void click(int x, int y, boolean leftMouse)
	{
		System.out.println("click " + x + "  " +y);
	}
	
	public void update() 
	{
		// plots
		for (int a = 0; a < 5; a++)
		{
			for (int b = 0; b < 9; b++)
			{
				Map map = session.getMap();
				Plot plot = map.getPlot(a, b);
				renderables.add(plot);
			}
		}
		
		// bottom overview panel
		ArrayList<Character> characters = session.getCharacters();
		for (int i = 0 ; i < characters.size(); i++) 
		{
			SimpleRender r = characterOverviews.get(i);
			r.setX(i * 126);
			r.setY(350);
			renderables.add(r);
			
			Character character = characters.get(i);
			
			RenderableString name = new RenderableString(character.getName(), (i * 126) + 15, 364);
			renderableStrings.add(name);
			
			RenderableString money = new RenderableString("$" + character.getMoney(), (i * 126) + 40, 380);
			renderableStrings.add(money);

			RenderableString ore = new RenderableString("" + character.getOre(), (i * 126) + 25, 395);
			renderableStrings.add(ore);

			RenderableString food = new RenderableString("" + character.getFood(), (i * 126) + 25, 410);
			renderableStrings.add(food);

			RenderableString energy = new RenderableString("" + character.getEnergy(), (i * 126) + 90, 395);
			renderableStrings.add(energy);

			RenderableString crystite = new RenderableString("" + character.getCrystite(), (i * 126) + 90, 410);
			renderableStrings.add(crystite);
		}
	}

	public boolean isDone() 
	{
		return false;
	}

	public void setSession(Session session) 
	{
		this.session = session;
	}

	public Session getSession() 
	{
		return session;
	}
}
