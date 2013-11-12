package game.screen;

import game.*;
import game.store.MuleStore;
import game.store.Pub;
import game.store.Store;
import game.store.UpgradeStore;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ui.Window;
import ui.render.Render;
import core.ImageLoader;
import core.Keyboard;
import ui.render.StringRender;

public class TownScreen extends Screen 
{	
	private ArrayList<Store> stores;
	private Keyboard keyboard;
	private int storeTimer;
	private ArrayList<Mule> badMules;
    private boolean isBeginningOfNotLastPlacePlayersTurn;
    private String playerRandomId;
    private StringRender eventString1;
    private StringRender eventString2;

	
	public TownScreen(Session session)
	{
		super(session);
        playerRandomId = "";
		badMules = new ArrayList<Mule>();
		storeTimer = 15;
        isBeginningOfNotLastPlacePlayersTurn = false;
        eventString1 = new StringRender("",200,367);
        eventString2 = new StringRender("",200,385);
		
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
		renders.clear();
		stringRenders.clear();
		
		boolean inStore = false;
			
		for (Store store : stores)
		{
			renders.add(store.getRender());
			
			if (store.inBounds(session.getPlayerX(playerId), session.getPlayerY(playerId)))
			{
				inStore = true;
				
				Render spaceBarAlert = new Render();
				spaceBarAlert.x = session.getPlayerX(playerId) - 20;
				spaceBarAlert.y = session.getPlayerY(playerId) - 40;
				spaceBarAlert.addImage("assets/images/spaceBarAlert.png");
				renders.add(spaceBarAlert);
				
				if (keyboard.isDown(KeyEvent.VK_SPACE) && storeTimer <= 0)
				{
					store.act();
					storeTimer = 15;
				}
			}
		}

        if (!playerRandomId.equals(session.getCurrentPlayerId()))
        {
            eventString1.setText("");
            eventString2.setText("");
            playerRandomId = session.getCurrentPlayerId();
            RandomEvent.generateEvent(session,isBeginningOfNotLastPlacePlayersTurn, eventString1,eventString2);
            isBeginningOfNotLastPlacePlayersTurn=true;
        }

        stringRenders.add(eventString1);
        stringRenders.add(eventString2);
        if (storeTimer > 0)
		{
			storeTimer--;
		}
		
		if (!badMules.isEmpty())
		{
			for (Mule baddie: badMules)
			{
				baddie.update();
				renders.add(baddie.getRender());
			}
		}
		
		if (!inStore && keyboard.isDown(KeyEvent.VK_SPACE))
		{
			Follower follower = session.getPlayerFollower(playerId);
			if (follower != null && follower instanceof Mule)
			{
				((Mule)follower).runAway();
				badMules.add((Mule)follower);
				session.removePlayerFollower(playerId);
				storeTimer = 15;
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
