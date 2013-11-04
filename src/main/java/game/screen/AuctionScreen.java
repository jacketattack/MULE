package game.screen;

import game.Session;
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
	private SimpleRender upArrowOre;
	private SimpleRender downArrowOre;
	private SimpleRender upArrowCrystite;
	private SimpleRender downArrowCrystite;
	private SimpleRender upArrowEnergy;
	private SimpleRender downArrowEnergy;
	private SimpleRender upArrowFood;
	private SimpleRender downArrowFood;
	private SimpleRender storeItemNames;
	private SimpleRender playerItemNames;
	private SimpleRender buyButton;
	private SimpleRender sellButton;
	private SimpleRender storeText;
	private SimpleRender titleBar;
	private RenderableString characterName;

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
    		
    		downArrowOre = new SimpleRender("assets/images/auction/downArrow.gif");
    		downArrowOre.setX(80);
    		downArrowOre.setY(235);
    		renderables.add(downArrowOre);
    		
    		upArrowOre = new SimpleRender("assets/images/auction/upArrow.gif");
    		upArrowOre.setX(80);
    		upArrowOre.setY(144);
    		renderables.add(upArrowOre);
    		
    		downArrowCrystite = new SimpleRender("assets/images/auction/downArrow.gif");
    		downArrowCrystite.setX(130);
    		downArrowCrystite.setY(235);
    		renderables.add(downArrowCrystite);
    		
    		upArrowCrystite = new SimpleRender("assets/images/auction/upArrow.gif");
    		upArrowCrystite.setX(130);
    		upArrowCrystite.setY(144);
    		renderables.add(upArrowCrystite);
    		
    		downArrowEnergy = new SimpleRender("assets/images/auction/downArrow.gif");
    		downArrowEnergy.setX(180);
    		downArrowEnergy.setY(235);
    		renderables.add(downArrowEnergy);
    		
    		upArrowEnergy = new SimpleRender("assets/images/auction/upArrow.gif");
    		upArrowEnergy.setX(180);
    		upArrowEnergy.setY(144);
    		renderables.add(upArrowEnergy);
    		
    		downArrowFood = new SimpleRender("assets/images/auction/downArrow.gif");
    		downArrowFood.setX(230);
    		downArrowFood.setY(235);
    		renderables.add(downArrowFood);
    		
    		upArrowFood = new SimpleRender("assets/images/auction/upArrow.gif");
    		upArrowFood.setX(230);
    		upArrowFood.setY(144);
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
		renderableStrings.add(new RenderableString("Current   ->", 5, 305));
		renderableStrings.add(new RenderableString("Quantity", 5, 315));
	
		renderableStrings.add(new RenderableString("Buy Price ->", 285, 160));
		renderableStrings.add(new RenderableString("Sell Price ->", 285, 215));
		renderableStrings.add(new RenderableString("Quantity \nto \nSell ->", 280, 260));
		
		


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