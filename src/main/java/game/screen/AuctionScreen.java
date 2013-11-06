package game.screen;

import game.Session;
import game.store.AuctionStore;

import javax.swing.JOptionPane;

import ui.Button;
import ui.Window;
import ui.render.RenderableString;
import ui.render.SimpleRender;

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
	private Button buyButton;
	private Button sellButton;
	private Button doneButton;
	private SimpleRender storeText;
	private SimpleRender titleBar;
	// these are the current value of the resource show in between up and down arrows for a transaction
	private int currOreAmount; 
	private int currCrystiteAmount;
	private int currEnergyAmount;
	private int currFoodAmount;
	private boolean shouldSwitchPlayer;
	private AuctionStore store;
	
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
    		buyButton = new Button("assets/images/auction/buyBtn.gif");
    		buyButton.setX(40);
    		buyButton.setY(340);
    		buyButton.setWidth(117);
    		buyButton.setHeight(36);
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
    		downArrowEnergy.setWidth(29);
    		downArrowEnergy.setHeight(29);
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
    		downArrowFood.setWidth(29);
    		downArrowFood.setHeight(29);
    		renderables.add(downArrowFood);
    		
    		upArrowFood = new Button("assets/images/auction/upArrow.gif");
    		upArrowFood.setX(230);
    		upArrowFood.setY(144);
    		upArrowFood.setWidth(29);
    		upArrowFood.setHeight(29);
    		renderables.add(upArrowFood);
    		
    		doneButton = new Button("assets/images/auction/Done.png");
    		doneButton.setX(450);
    		doneButton.setY(300);
    		doneButton.setWidth(50);
    		doneButton.setHeight(30);
    		renderables.add(doneButton);
    		
    		storeItemNames = new SimpleRender("assets/images/auction/ItemStrings.gif");
    		storeItemNames.setX(391);
    		storeItemNames.setY(100);
    		renderables.add(storeItemNames);
    		
    		playerItemNames = new SimpleRender("assets/images/auction/ItemStrings.gif");
    		playerItemNames.setX(70);
    		playerItemNames.setY(100);
    		renderables.add(playerItemNames);
    		
    		sellButton = new Button("assets/images/auction/sellBtn.gif");
    		sellButton.setX(160);
    		sellButton.setY(340);
    		sellButton.setWidth(117);
    		sellButton.setHeight(36);
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
    		
    		store = AuctionStore.getInstance();
    		shouldSwitchPlayer = false;
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
		renderables.add(doneButton);
		
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
		
		String playerId = session.getCurrentPlayerId();
		renderableStrings.add(new RenderableString(session.getPlayerOre(playerId) + "", 90, 315));
		renderableStrings.add(new RenderableString(session.getPlayerCrystite(playerId) + "", 140, 315));
		renderableStrings.add(new RenderableString(session.getPlayerEnergy(playerId) + "", 190, 315));
		renderableStrings.add(new RenderableString(session.getPlayerFood(playerId) + "", 240, 315));

		
		renderableStrings.add(new RenderableString("CURRENT MONEY", 310, 300));
		renderableStrings.add(new RenderableString("__________________", 310, 305));
		renderableStrings.add(new RenderableString(session.getPlayerMoney(playerId) + "", 350, 325));
		
		// now for the store's values
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
    	if (shouldSwitchPlayer) {
    		shouldSwitchPlayer = false;
    		return true;
    	}
    	return false;
    }
    
    public void click(int x, int y, boolean isLeftMouse)
    {
        if (upArrowOre.inBounds(x, y)) {
        	currOreAmount++;
        } else if (upArrowCrystite.inBounds(x,y)) {
        	currCrystiteAmount++;
        } else if (upArrowEnergy.inBounds(x, y)) {
        	currEnergyAmount++;
        } else if (upArrowFood.inBounds(x, y)) {
        	currFoodAmount++;
        } else if (downArrowOre.inBounds(x, y) && currOreAmount > 0) {
        	currOreAmount--;
        } else if (downArrowCrystite.inBounds(x, y) && currCrystiteAmount > 0) {
        	currCrystiteAmount--;
        } else if (downArrowEnergy.inBounds(x, y) && currEnergyAmount > 0) {
        	currEnergyAmount--;
        } else if (downArrowFood.inBounds(x, y) && currFoodAmount > 0) {
        	currFoodAmount--;
        } else if (buyButton.inBounds(x, y)) {
        	boolean canBuy = checkPurchase();
        	if (canBuy) {
        		processPurchase();
        	}
        } else if (sellButton.inBounds(x, y)) {
        	boolean canSell = checkSale();
        	if (canSell) {
        		processSale();
        	}
        } else if ( doneButton.inBounds(x, y)) {
        	shouldSwitchPlayer = true;
        }
    }
    
    private boolean checkSale()
    {
    	Window window = Window.getInstance();
    	
    	String playerId = session.getCurrentPlayerId();
    	// now check to see if player has enough of resources to sell
    	if ( currOreAmount > session.getPlayerOre(playerId)) {
    		JOptionPane.showMessageDialog(window, "You do not have as much ore to sell as you want!");
    		return false;
    	}
    	
    	if ( currCrystiteAmount > session.getPlayerCrystite(playerId)) {
    		JOptionPane.showMessageDialog(window, "You do not have as much crystite to sell as you want!");
    		return false;
    	}
    	
    	if ( currEnergyAmount > session.getPlayerEnergy(playerId)) {
    		JOptionPane.showMessageDialog(window, "You do not have as much energy to sell as you want!");
    		return false;
    	}
    	
    	if ( currFoodAmount > session.getPlayerFood(playerId)) {
    		JOptionPane.showMessageDialog(window, "You do not have as much food to sell as you want!");
    		return false;
    	}
    	return true;
    }
    
    private void processSale()
    {
    	String playerId = session.getCurrentPlayerId();
    	session.playerSellResource(playerId, "ore", currOreAmount, store.getSellPrice("ore"));
    	session.playerSellResource(playerId, "crystite", currCrystiteAmount, store.getSellPrice("crystite"));
    	session.playerSellResource(playerId, "energy", currEnergyAmount, store.getSellPrice("energy"));
    	session.playerSellResource(playerId, "food", currFoodAmount, store.getSellPrice("food"));
    	
    	// Now let's increment inventory of store.

    	store.buyResource("ore", currOreAmount);
    	store.buyResource("crystite", currCrystiteAmount);
    	store.buyResource("energy", currEnergyAmount);
    	store.buyResource("food", currFoodAmount);
    	
    	// reset amounts to 0 for next transaction
    	currOreAmount = 0;
    	currCrystiteAmount = 0;
    	currEnergyAmount = 0;
    	currFoodAmount = 0;
    }
    
    private boolean checkPurchase() 
    {
    	// get window so error box is centered over it
    	Window window = Window.getInstance();
    	int cost = calculateTotalCost();
    	
    	String playerId = session.getCurrentPlayerId();
    	if ( cost > session.getPlayerMoney(playerId) ) {
    		JOptionPane.showMessageDialog(window, "Not enough money!");
    		return false;
    	}
    	
    	// now check to see if store has enough of resources to sell
    	if ( currOreAmount > store.getQuantity("ore")) {
    		JOptionPane.showMessageDialog(window, "Store does not have as much ore to sell as you want!");
    		return false;
    	}
    	
    	if ( currCrystiteAmount > store.getQuantity("crystite")) {
    		JOptionPane.showMessageDialog(window, "Store does not have as much crystite to sell as you want!");
    		return false;
    	}
    	
    	if ( currEnergyAmount > store.getQuantity("energy")) {
    		JOptionPane.showMessageDialog(window, "Store does not have as much energy to sell as you want!");
    		return false;
    	}
    	
    	if ( currFoodAmount > store.getQuantity("food")) {
    		JOptionPane.showMessageDialog(window, "Store does not have as much food to sell as you want!");
    		return false;
    	}
    	
    	return true;
    }
    
    private void processPurchase() 
    {
    	String playerId = session.getCurrentPlayerId();
    	session.playerBuyResource(playerId, "ore", currOreAmount, store.getBuyPrice("ore"));
    	session.playerBuyResource(playerId, "crystite", currCrystiteAmount, store.getBuyPrice("crystite"));
    	session.playerBuyResource(playerId, "energy", currEnergyAmount, store.getBuyPrice("energy"));
    	session.playerBuyResource(playerId, "food", currFoodAmount, store.getBuyPrice("food"));
    	
    	// Now let's increment inventory of player, decrease that of store, and reset amount values

    	store.sellResource("ore", currOreAmount);
    	store.sellResource("crystite", currCrystiteAmount);
    	store.sellResource("energy", currEnergyAmount);
    	store.sellResource("food", currFoodAmount);
    	
    	// reset amounts to 0 for next transaction
    	currOreAmount = 0;
    	currCrystiteAmount = 0;
    	currEnergyAmount = 0;
    	currFoodAmount = 0;
    }
    
    private int calculateTotalCost() 
    {
    	int total = 0;
    	total += currOreAmount * store.getBuyPrice("ore");
    	total += currCrystiteAmount * store.getBuyPrice("crystite");
    	total += currEnergyAmount * store.getBuyPrice("energy");
    	total += currFoodAmount * store.getBuyPrice("food");
    	
    	return total;
    }
}