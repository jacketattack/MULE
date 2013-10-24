package game.screen;

import game.Character;
import game.Plot;
import game.Pub;
import game.Session;
import game.Store;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ui.Window;
import core.ImageLoader;
import core.Keyboard;
import core.render.SimpleRender;

public class TownScreen extends Screen 
{	
	private ArrayList<Store> stores;
	private Keyboard keyboard;
	
	public TownScreen(Session session)
	{
		super(session);
		
		// tell the image loader to cache a copy of our images
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.load("assets/images/store/storeEnergy.png");
		imageLoader.load("assets/images/store/storeFood.png");
		imageLoader.load("assets/images/store/storeOre.png");
		imageLoader.load("assets/images/store/storeCrystite.png");
		imageLoader.load("assets/images/store/storeMule.png");
		imageLoader.load("assets/images/store/storeAssay.png");
		imageLoader.load("assets/images/store/storePub.png");
		imageLoader.load("assets/images/store/storeLand.png");
		
		end = false;
		
		stores = new ArrayList<Store>();
		Pub pub = new Pub();
		pub.setX(362);
		pub.setY(200);
		pub.setWidth(130);
		pub.setHeight(148);
		stores.add(pub);
		
		keyboard = Keyboard.getInstance();
	}
	
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();
		
		for (Store store : stores)
		{
			renderables.add(store);
			
			if (store.inBounds(character.getX(), character.getY()))
			{
				SimpleRender spaceBarAlert = new SimpleRender("assets/images/spaceBarAlert.png");
				spaceBarAlert.setX(character.getX() - 20);
				spaceBarAlert.setY(character.getY() - 40);
				renderables.add(spaceBarAlert);
				
				if (keyboard.isDown(KeyEvent.VK_SPACE))
				{
					end = true;
				}
			}
		}
	}

	public boolean shouldSwitch() 
	{
		if (character.getX() < 0)
		{
			character.setX(Plot.SIZE * 4 - Character.WIDTH * 2);
			character.setY(Plot.SIZE * 2 + Plot.SIZE / 2 - Character.HEIGHT / 2);
			return true;
		}
		
		if (character.getX() > Window.WIDTH - Character.WIDTH)
		{
			character.setX(Plot.SIZE * 5 + Character.WIDTH);
			character.setY(Plot.SIZE * 2 + Plot.SIZE / 2 - Character.HEIGHT / 2);
			return true;
		}

		if (character.getY() < 0)
		{
			character.setX(Plot.SIZE * 4 + Plot.SIZE / 2 - Character.WIDTH / 2);
			character.setY(Plot.SIZE * 2 - Character.HEIGHT);
			return true;
		}
		
		if (character.getY() > Window.HEIGHT - 90)
		{
			character.setX(Plot.SIZE * 4 + Plot.SIZE / 2- Character.WIDTH / 2);
			character.setY(Plot.SIZE * 3 + Character.HEIGHT);
			return true;
		}
		
		return false;
	}
}
