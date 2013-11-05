package game.screen;

import game.AuctionStore;
import game.Session;
import ui.Button;
import core.render.RenderableString;
import core.render.SimpleRender;
//may not need to use
//may not need to use

/**
 * Serves as the backdrop for buying and selling
 * @author nteissler
 */

public class AuctionScreen extends Screen 
{	
	private Button upArrowOre;
	private Button downArrowOre;
	private Button upArrowCrystite;
	private Button downArrowCrystite;
	private Button upArrowEnergy;
	private Button downArrowEnergy;
	private Button upArrowFood;
	private Button downArrowFood;
	private SimpleRender storeItemNames;
	private SimpleRender playerItemNames;
	private SimpleRender buyButton;
	private SimpleRender sellButton;
	private SimpleRender storeText;
	private SimpleRender titleBar;
	private RenderableString characterName;
	// these are the current value of the resource show in between up and down arrows for a transaction
	private int currOreAmount; 
	private int currCrystiteAmount;
	private int currEnergyAmount;
	private int currFoodAmount;
	
	public AuctionScreen(Session session) 
	{
            super(session);
            //initialize, but don't add to renderableStrings, all string
            //objects that we need on the screen here.
            //So all the inventory item counts, and the money counts
            //and the prices. All of these numbers are available from
            //The character and AuctionStore class.
            //basically grab the character using the already sorted array
            //list of characters within session and the already set character
            //index within session.
            //Grab the stores inventory using AuctionStore.getInstance();

            //Initialize the SimpleRenderable Objects/Images that will
            //serve as a backdrop for the AuctionScreen
    		buyButton = new SimpleRender("assets/images/auction/buyBtn.gif");
    		buyButton.setX(40);
    		buyButton.setY(340);
    		renderables.add(buyButton);
    		
    		downArrowOre = new Button("assets/images/auction/downArrow.gif");
    		downArrowOre.setX(80);
    		downArrowOre.setY(235);
    		downArrowOre.setWidth(29);
    		downArrowOre.setHeight(29);
    		renderables.add(downArrowOre);
    		
    		upArrowOre = new Button("assets/images/auction/upArrow.gif");
    		upArrowOre.setX(80);
    		upArrowOre.setY(144);
    		upArrowOre.setWidth(29);
    		upArrowOre.setHeight(29);
    		renderables.add(upArrowOre);
    		
    		downArrowCrystite = new Button("assets/images/auction/downArrow.gif");
    		downArrowCrystite.setX(130);
    		downArrowCrystite.setY(235);
    		downArrowCrystite.setWidth(29);
    		downArrowCrystite.setHeight(29);
    		renderables.add(downArrowCrystite);
    		
    		upArrowCrystite = new Button("assets/images/auction/upArrow.gif");
    		upArrowCrystite.setX(130);
    		upArrowCrystite.setY(144);
    		upArrowCrystite.setWidth(29);
    		upArrowCrystite.setHeight(29);
    		renderables.add(upArrowCrystite);
    		
    		downArrowEnergy = new Button("assets/images/auction/downArrow.gif");
    		downArrowEnergy.setX(180);
    		downArrowEnergy.setY(235);
    		renderables.add(downArrowEnergy);
    		
    		upArrowEnergy = new Button("assets/images/auction/upArrow.gif");
    		upArrowEnergy.setX(180);
    		upArrowEnergy.setY(144);
    		upArrowEnergy.setWidth(29);
    		upArrowEnergy.setHeight(29);
    		renderables.add(upArrowEnergy);
    		
    		downArrowFood = new Button("assets/images/auction/downArrow.gif");
    		downArrowFood.setX(230);
    		downArrowFood.setY(235);
    		downArrowEnergy.setWidth(29);
    		downArrowEnergy.setHeight(29);
    		renderables.add(downArrowFood);
    		
    		upArrowFood = new Button("assets/images/auction/upArrow.gif");
    		upArrowFood.setX(230);
    		upArrowFood.setY(144);
    		upArrowFood.setWidth(29);
    		upArrowFood.setHeight(29);
    		renderables.add(upArrowFood);
    		
    		storeItemNames = new SimpleRender("assets/images/auction/ItemStrings.gif");
    		storeItemNames.setX(391);
    		storeItemNames.setY(100);
    		renderables.add(storeItemNames);
    		
    		playerItemNames = new SimpleRender("assets/images/auction/ItemStrings.gif");
    		playerItemNames.setX(70);
    		playerItemNames.setY(100);
    		renderables.add(playerItemNames);
    		
    		sellButton = new SimpleRender("assets/images/auction/sellBtn.gif");
    		sellButton.setX(160);
    		sellButton.setY(340);
    		renderables.add(sellButton);
    		
    		storeText = new SimpleRender("assets/images/auction/StoreText.gif");
    		storeText.setX(465);
    		storeText.setY(60);
    		renderables.add(storeText);
    		
    		titleBar = new SimpleRender("assets/images/auction/titleBar.gif");
    		titleBar.setX(0);
    		titleBar.setY(0);
    		renderables.add(titleBar); 
    		
    		// at start they have not touched buttons so amounts are 0 for transactions
    		currOreAmount = 0; 
    		currCrystiteAmount = 0;
    		currEnergyAmount = 0;
    		currFoodAmount = 0;
    		
          
	}

    @Override
    public void update() 
    {
        renderableStrings.clear();
        renderables.clear();
        
        //add all renderables to both arrays here
		renderables.add(buyButton);
		renderables.add(downArrowOre);
		renderables.add(downArrowCrystite);
		renderables.add(downArrowEnergy);
		renderables.add(downArrowFood);
		renderables.add(upArrowOre);
		renderables.add(upArrowCrystite);
		renderables.add(upArrowEnergy);
		renderables.add(upArrowFood);
		renderables.add(storeItemNames);
		renderables.add(playerItemNames);
		renderables.add(sellButton);
		renderables.add(storeText);
		renderables.add(titleBar);
		
		// Adding Renderable Strings that never change
		renderableStrings.add(new RenderableString("Amount", 5, 195));
		renderableStrings.add(new RenderableString("     in    ->", 5, 205));
		renderableStrings.add(new RenderableString("Transaction", 5, 215));
		renderableStrings.add(new RenderableString("Your", 5, 295));
		renderableStrings.add(new RenderableString("Current ->", 5, 305));
		renderableStrings.add(new RenderableString("Quantity", 5, 315));
	
		renderableStrings.add(new RenderableString("Buy Price ->", 285, 160));
		renderableStrings.add(new RenderableString("Sell Price ->", 285, 215));
		renderableStrings.add(new RenderableString("Quantity \nto \nSell ->", 270, 260));
		
		// now need to add renderable strings that are number values for certain resources
		renderableStrings.add(new RenderableString(currOreAmount + "", 90, 205));
		renderableStrings.add(new RenderableString(currCrystiteAmount + "", 140, 205));
		renderableStrings.add(new RenderableString(currEnergyAmount + "", 190, 205));
		renderableStrings.add(new RenderableString(currFoodAmount + "", 240, 205));
		
		renderableStrings.add(new RenderableString(session.getCurrentCharacter().getOre() + "", 90, 315));
		renderableStrings.add(new RenderableString(session.getCurrentCharacter().getCrystite() + "", 140, 315));
		renderableStrings.add(new RenderableString(session.getCurrentCharacter().getEnergy() + "", 190, 315));
		renderableStrings.add(new RenderableString(session.getCurrentCharacter().getFood() + "", 240, 315));

		// now for the store's values
		AuctionStore store = AuctionStore.getInstance();
		renderableStrings.add(new RenderableString(store.getBuyPrice("ore") + "", 400, 160));
		renderableStrings.add(new RenderableString(store.getBuyPrice("crystite") + "", 450, 160));
		renderableStrings.add(new RenderableString(store.getBuyPrice("energy") + "", 500, 160));
		renderableStrings.add(new RenderableString(store.getBuyPrice("food") + "", 550, 160));
		
		renderableStrings.add(new RenderableString(store.getSellPrice("ore") + "", 400, 215));
		renderableStrings.add(new RenderableString(store.getSellPrice("crystite") + "", 450, 215));
		renderableStrings.add(new RenderableString(store.getSellPrice("energy") + "", 500, 215));
		renderableStrings.add(new RenderableString(store.getSellPrice("food") + "", 550, 215));
		
		renderableStrings.add(new RenderableString(store.getQuantity("ore") + "", 400, 260));
		renderableStrings.add(new RenderableString(store.getQuantity("crystite") + "", 450, 260));
		renderableStrings.add(new RenderableString(store.getQuantity("energy") + "", 500, 260));
		renderableStrings.add(new RenderableString(store.getQuantity("food") + "", 550, 260));
		

    }

    @Override
    public boolean shouldSwitch() 
    {
        //This class and its accompanying sell screen need to
        //Implements a mouse listener for buttons that read
        //Done Selling, and Done Buying
        //This way the auction rounnd knows whether to advance
        //to the buy round, or next character's sell round
        //respectively.
        return false;
    }
    
    public void click(int x, int y, boolean isLeftMouse)
    {
        //implement all options of buttons being clicked in here
    }

}