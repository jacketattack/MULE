package game.screen;

import game.Character;
import game.Follower;
import game.ImprovementType;
import game.Mule;
import game.MuleStore;
import game.Plot;
import game.Pub;
import game.Session;
import game.Store;
import game.UpgradeStore;

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
	private int storeTimer;
	private ArrayList<Mule> badMules;
	
	public TownScreen(Session session)
	{
		super(session);
		badMules = new ArrayList<Mule>();
		storeTimer=15;
		
		// tell the image loader to cache a copy of our images
		/*ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.load("assets/images/store/storeEnergy.png");
		imageLoader.load("assets/images/store/storeFood.png");
		imageLoader.load("assets/images/store/storeOre.png");
		imageLoader.load("assets/images/store/storeCrystite.png");
		imageLoader.load("assets/images/store/storeMule.png");
		imageLoader.load("assets/images/store/storeAssay.png");
		imageLoader.load("assets/images/store/storePub.png");
		imageLoader.load("assets/images/store/storeLand.png"); */
		
		stores = new ArrayList<Store>();
		
		Pub pub = new Pub();
		pub.setSession(session);
		pub.setX(362);
		pub.setY(200);
		pub.setWidth(130);
		pub.setHeight(148);
		stores.add(pub);
		
		UpgradeStore foodStore = new UpgradeStore(ImprovementType.FOOD);
		foodStore.setSession(session);
		foodStore.setX(138);
		foodStore.setY(0);
		foodStore.setWidth(130);
		foodStore.setHeight(148);
		stores.add(foodStore);
		
		UpgradeStore energyStore = new UpgradeStore(ImprovementType.ENERGY);
		energyStore.setSession(session);
		energyStore.setX(496);
		energyStore.setY(0);
		energyStore.setWidth(130);
		energyStore.setHeight(148);
		stores.add(energyStore);
		
		UpgradeStore oreStore = new UpgradeStore(ImprovementType.ORE);
		oreStore.setSession(session);
		oreStore.setX(362);
		oreStore.setY(0);
		oreStore.setWidth(130);
		oreStore.setHeight(148);
		stores.add(oreStore);
		
		UpgradeStore crystiteStore = new UpgradeStore(ImprovementType.CRYSTITE);
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
		boolean inStore = false;
		renderables.clear();
		renderableStrings.clear();
		
		for (Store store : stores)
		{
			renderables.add(store);
			
			if (store.inBounds(character.getX(), character.getY()))
			{
				inStore=true;
				
				SimpleRender spaceBarAlert = new SimpleRender("assets/images/spaceBarAlert.png");
				spaceBarAlert.setX(character.getX() - 20);
				spaceBarAlert.setY(character.getY() - 40);
				renderables.add(spaceBarAlert);
				
				if (keyboard.isDown(KeyEvent.VK_SPACE) && storeTimer <= 0)
				{
					store.act();
					storeTimer = 15;
				}
			}
		}
		
		if (storeTimer > 0)
		{
			storeTimer--;
		}
		if (!badMules.isEmpty()){
			renderables.addAll(badMules);
			for(Mule baddie: badMules){
				baddie.update();
			}
		}
		if(!inStore && keyboard.isDown(KeyEvent.VK_SPACE)){
			Follower follower = session.getCurrentCharacter().getFollower();
			if(follower !=null && follower instanceof Mule){
				((Mule)follower).runAway();
				badMules.add((Mule)follower);
				session.getCurrentCharacter().setFollower(null);
				storeTimer=15;
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
