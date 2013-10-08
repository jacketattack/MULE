package game.state;

import game.Character;
import game.Map;
import game.Plot;
import game.Renderable;
import game.RenderableString;
import game.Session;
import game.SimpleRender;

import java.util.ArrayList;

import ui.Window;
import ui.panel.GamePanel;

/**
 * GameState runs the entire in-game experience. 
 * This class will manage the game 'rounds'
 * @author grant
 */
public class GameState implements State 
{
	/** The current game session*/
	private Session session;
	
	private ArrayList<SimpleRender> characterOverviews;
	
	/**
	 * The game session is given to the GameState
	 * through this constructor
	 */
	public GameState(Session session)
	{	
		this.session = session;

		characterOverviews = new ArrayList<SimpleRender>();
		characterOverviews.add(new SimpleRender("assets/images/characterStatBackground.png"));
		characterOverviews.add(new SimpleRender("assets/images/characterStatBackground.png"));
		characterOverviews.add(new SimpleRender("assets/images/characterStatBackground.png"));
		characterOverviews.add(new SimpleRender("assets/images/characterStatBackground.png"));
	}
	
	/**
	 * All game functionality starts in this method
	 */
	public void update() 
	{	
		// get panel
		
		Window window = Window.getInstance();
		if (window.getPanel() instanceof GamePanel == false)
		{
			return;
		}
		GamePanel panel = (GamePanel)window.getPanel();
		
		// add renderable objects
		
		ArrayList<Renderable> renderables = new ArrayList<Renderable>();
		ArrayList<RenderableString> renderableStrings = new ArrayList<RenderableString>();

		for (int a = 0; a < 5; a++)
		{
			for (int b = 0; b < 9; b++)
			{
				Map map = session.getMap();
				Plot plot = map.getPlot(a, b);
				renderables.add(plot);
			}
		}
		
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

		panel.draw(renderables);
		panel.drawStrings(renderableStrings);
	}
	
	/** 
	 * Get the current game session
	 * @return the current game session
	 */
	public Session getSession()
	{
		return session;
	}
}
