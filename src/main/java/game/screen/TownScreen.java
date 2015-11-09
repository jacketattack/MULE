package game.screen;

import game.Follower;
import game.ImprovementType;
import game.Mule;
import game.Player;
import game.Plot;
import game.RandomEvent;
import game.Session;
import game.store.MuleStore;
import game.store.Pub;
import game.store.Store;
import game.store.UpgradeStore;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ui.Window;
import ui.render.Render;
import ui.render.StringRender;
import core.Keyboard;

public class TownScreen extends Screen 
{   
    private ArrayList<Store> stores;
    private Keyboard keyboard;
    private int storeTimer;
    private ArrayList<Mule> badMules;
    private boolean shouldShowRandomEvent;
    private String playerRandomId;
    private StringRender eventStringTop;
    private StringRender eventStringBottom;
    private Render backgroundRender;

    /**
     * Default constructor for TownScreen class. Sets the session and adds
     * base graphics needed.
     * @param session the current game session for use by TownScreen
     */
    public TownScreen(Session session)
    {
        super(session);

        backgroundRender = new Render();
        backgroundRender.addImage("assets/images/gameBackground.png");
        renders.add(backgroundRender);
        
        playerRandomId = "";
        badMules = new ArrayList<>();


        shouldShowRandomEvent = false;
        eventStringTop = new StringRender("", 30, 327, Color.BLACK);
        eventStringBottom = new StringRender("", 30, 345, Color.BLACK);
        
        stores = new ArrayList<>();
        
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

    /**
     * Called everytime the thread ticks
     */
    public void update() 
    {
        renders.clear();
        stringRenders.clear();      
        
        renders.add(backgroundRender);
        
        boolean inStore = false;
            
        for (Store store : stores)
        {
            renders.add(store.getRender());
            
            if (store.inBounds(session.getPlayerX(playerId), session.getPlayerY(playerId)))
            {
                inStore = true;
                
                if (keyboard.isDown(KeyEvent.VK_SPACE) && storeTimer <= 0)
                {
                    store.act();
                    storeTimer = 15;
                }
            }
        }

        if (!playerRandomId.equals(session.getCurrentPlayerId()))
        {
            eventStringTop.text = "";
            eventStringBottom.text = "";
            playerRandomId = session.getCurrentPlayerId();
            RandomEvent.generateEvent(session, shouldShowRandomEvent, eventStringTop, eventStringBottom);
            shouldShowRandomEvent = true;
        }

        stringRenders.add(eventStringTop);
        stringRenders.add(eventStringBottom);
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
        
        if (inStore)
        {
            Render spaceBarAlert = new Render();
            spaceBarAlert.x = session.getPlayerX(playerId) - 20;
            spaceBarAlert.y = session.getPlayerY(playerId) - 40;
            spaceBarAlert.addImage("assets/images/spaceBarAlert.png");
            renders.add(spaceBarAlert); 
        }
    }

    /**
     * Determines if the player is trying to go between the town screen and the map screen
     *
     * @return true if the player is in set bounds areas.
     */
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
        
        if (session.getPlayerY(playerId) > Window.HEIGHT - 88 - Player.HEIGHT)
        {
            session.setPlayerX(playerId, Plot.SIZE * 4 + Plot.SIZE / 2- Player.WIDTH / 2);
            session.setPlayerY(playerId, Plot.SIZE * 3 + Player.HEIGHT);
            switching = true;
        }
        
        return switching;
    }
}
