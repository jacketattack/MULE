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
	private SimpleRender characterOverview;
	private ArrayList<Character> characters;
	private RenderableString prompt;
	
	
	public LandGrantRound(Session session)
	{	
		super(session);
		
		characterOverview = new SimpleRender("assets/images/characterStatBackground.png");
		characterOverview.setX(0);
		characterOverview.setY(350);
		
		characters = new ArrayList<Character>();
		for (Character character : session.getCharacters())
		{
			// can make random here
			characters.add(character);
		}
		
		prompt = new RenderableString();
		prompt.setX(250);
		prompt.setY(390);
	}

	/**
	 * Alert the state that an mouse click occurred
	 * @param x The x pos in pixels
	 * @param y The y pos in pixels
	 * @param leftMouse Whether the left mouse was pressed
	 */
	public void click(int x, int y, boolean leftMouse)
	{
		int xGridPos = (int)Math.floor(x / Plot.SIZE);
		int yGridPos = (int)Math.floor(y / Plot.SIZE);

		System.out.println("currentPlayer selected plot(x:" + xGridPos + ", y:" + yGridPos + ")");
	}
	
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();
		
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

		renderables.add(characterOverview);
		
		Character character = characters.get(0);
		
		prompt.setText(character.getName() + " please select a plot");
		renderableStrings.add(prompt);
		
		RenderableString name = new RenderableString(character.getName(), 15, 364);
		renderableStrings.add(name);
		
		RenderableString money = new RenderableString("$" + character.getMoney(), 30, 380);
		renderableStrings.add(money);

		RenderableString ore = new RenderableString("" + character.getOre(), 30, 395);
		renderableStrings.add(ore);

		RenderableString food = new RenderableString("" + character.getFood(), 30, 410);
		renderableStrings.add(food);

		RenderableString energy = new RenderableString("" + character.getEnergy(), 90, 395);
		renderableStrings.add(energy);

		RenderableString crystite = new RenderableString("" + character.getCrystite(), 90, 410);
		renderableStrings.add(crystite);
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
