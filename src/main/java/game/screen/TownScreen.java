package game.screen;

import game.Player;
import game.Plot;
import game.Session;
import game.store.CrystiteStore;
import game.store.EnergyStore;
import game.store.FoodStore;
import game.store.MuleStore;
import game.store.OreStore;
import game.store.Pub;
import game.store.Store;

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
		
		stores = new ArrayList<Store>();
		
		Pub pub = new Pub();
		pub.setSession(session);
		pub.setX(362);
		pub.setY(200);
		pub.setWidth(130);
		pub.setHeight(148);
		stores.add(pub);
		
		FoodStore foodStore = new FoodStore();
		foodStore.setSession(session);
		foodStore.setX(138);
		foodStore.setY(0);
		foodStore.setWidth(130);
		foodStore.setHeight(148);
		stores.add(foodStore);
		
		EnergyStore energyStore = new EnergyStore();
		energyStore.setSession(session);
		energyStore.setX(496);
		energyStore.setY(0);
		energyStore.setWidth(130);
		energyStore.setHeight(148);
		stores.add(energyStore);
		
		OreStore oreStore = new OreStore();
		oreStore.setSession(session);
		oreStore.setX(362);
		oreStore.setY(0);
		oreStore.setWidth(130);
		oreStore.setHeight(148);
		stores.add(oreStore);
		
		CrystiteStore crystiteStore = new CrystiteStore();
		crystiteStore.setSession(session);
		crystiteStore.setX(4);
		crystiteStore.setY(0);
		crystiteStore.setWidth(130);
		crystiteStore.setHeight(148);
		stores.add(crystiteStore);
		
		MuleStore muleStore = new MuleStore();
		muleStore.setSession(session);
		muleStore.setX(496);
		muleStore.setY(200);
		muleStore.setWidth(130);
		muleStore.setHeight(148);
		stores.add(muleStore);

		// LandStore landStore = new LandStore();
		// landStore.setSession(session);
		// landStore.setX(138);
		// landStore.setY(200);
		// landStore.setWidth(130);
		// landStore.setHeight(148);
		// stores.add(landStore);

		// AssayStore assayStore = new AssayStore();
		// assayStore.setSession(session);
		// assayStore.setX(4);
		// assayStore.setY(200);
		// assayStore.setWidth(130);
		// assayStore.setHeight(148);
		// stores.add(assayStore);
		
		keyboard = Keyboard.getInstance();
	}
	
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();
		
		for (Store store : stores)
		{
			renderables.add(store);
			
			if (store.inBounds(session.getPlayerX(playerId), session.getPlayerY(playerId)))
			{
				SimpleRender spaceBarAlert = new SimpleRender("assets/images/spaceBarAlert.png");
				spaceBarAlert.setX(session.getPlayerX(playerId) - 20);
				spaceBarAlert.setY(session.getPlayerY(playerId) - 40);
				renderables.add(spaceBarAlert);
				
				if (keyboard.isDown(KeyEvent.VK_SPACE))
				{
					store.act();
				}
			}
		}
	}

	public boolean shouldSwitch() 
	{
		boolean switching = false;
		
		if (session.getPlayerX(playerId) < 0)
		{
			session.setPlayerX(playerId, Plot.SIZE * 4 - Player.WIDTH * 2);
			session.setPlayerY(playerId, Plot.SIZE * 2 + Plot.SIZE / 2 - Player.HEIGHT / 2);
			switching = true;
		}
		
		if (session.getPlayerX(playerId) > Window.WIDTH - Player.WIDTH)
		{
			session.setPlayerX(playerId, Plot.SIZE * 5 + Player.WIDTH);
			session.setPlayerY(playerId, Plot.SIZE * 2 + Plot.SIZE / 2 - Player.HEIGHT / 2);
			switching = true;
		}

		if (session.getPlayerY(playerId) < 0)
		{
			session.setPlayerX(playerId, Plot.SIZE * 4 + Plot.SIZE / 2 - Player.WIDTH / 2);
			session.setPlayerY(playerId, Plot.SIZE * 2 - Player.HEIGHT);
			switching = true;
		}
		
		if (session.getPlayerY(playerId) > Window.HEIGHT - 90)
		{
			session.setPlayerX(playerId, Plot.SIZE * 4 + Plot.SIZE / 2- Player.WIDTH / 2);
			session.setPlayerY(playerId, Plot.SIZE * 3 + Player.HEIGHT);
			switching = true;
		}
		
		return switching;
	}
}
