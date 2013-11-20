package game.screen;

import game.Session;
import game.store.AuctionStore;

import javax.swing.JOptionPane;

import ui.Button;
import ui.Window;
import ui.render.Render;
import ui.render.StringRender;

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
	private Render storeItemNames;
	private Render playerItemNames;
	private Button buyButton;
	private Button sellButton;
	private Button doneButton;
	private Render storeText;
	private Render titleBar;
	private int currentOreAmount; 
	private int currentCrystiteAmount;
	private int currentEnergyAmount;
	private int currentFoodAmount;
	private boolean shouldSwitchPlayer;
	private AuctionStore store;

    /**
     * Constructor for an AuctionScreen, adds all images to the object
     * and sets up all the buttons.
     * @param session
     */
	public AuctionScreen(Session session) 
	{
            super(session);

    		buyButton = new Button("assets/images/auction/buyBtn.gif");
    		buyButton.setX(40);
    		buyButton.setY(340);
    		buyButton.setWidth(117);
    		buyButton.setHeight(36);
    		renders.add(buyButton.getRender());
    		
    		downArrowOre = new Button("assets/images/auction/downArrow.gif");
    		downArrowOre.setX(80);
    		downArrowOre.setY(235);
    		downArrowOre.setWidth(29);
    		downArrowOre.setHeight(29);
    		renders.add(downArrowOre.getRender());
    		
    		upArrowOre = new Button("assets/images/auction/upArrow.gif");
    		upArrowOre.setX(80);
    		upArrowOre.setY(144);
    		upArrowOre.setWidth(29);
    		upArrowOre.setHeight(29);
    		renders.add(upArrowOre.getRender());
    		
    		downArrowCrystite = new Button("assets/images/auction/downArrow.gif");
    		downArrowCrystite.setX(130);
    		downArrowCrystite.setY(235);
    		downArrowCrystite.setWidth(29);
    		downArrowCrystite.setHeight(29);
    		renders.add(downArrowCrystite.getRender());
    		
    		upArrowCrystite = new Button("assets/images/auction/upArrow.gif");
    		upArrowCrystite.setX(130);
    		upArrowCrystite.setY(144);
    		upArrowCrystite.setWidth(29);
    		upArrowCrystite.setHeight(29);
    		renders.add(upArrowCrystite.getRender());
    		
    		downArrowEnergy = new Button("assets/images/auction/downArrow.gif");
    		downArrowEnergy.setX(180);
    		downArrowEnergy.setY(235);
    		downArrowEnergy.setWidth(29);
    		downArrowEnergy.setHeight(29);
    		renders.add(downArrowEnergy.getRender());
    		
    		upArrowEnergy = new Button("assets/images/auction/upArrow.gif");
    		upArrowEnergy.setX(180);
    		upArrowEnergy.setY(144);
    		upArrowEnergy.setWidth(29);
    		upArrowEnergy.setHeight(29);
    		renders.add(upArrowEnergy.getRender());
    		
    		downArrowFood = new Button("assets/images/auction/downArrow.gif");
    		downArrowFood.setX(230);
    		downArrowFood.setY(235);
    		downArrowFood.setWidth(29);
    		downArrowFood.setHeight(29);
    		renders.add(downArrowFood.getRender());
    		
    		upArrowFood = new Button("assets/images/auction/upArrow.gif");
    		upArrowFood.setX(230);
    		upArrowFood.setY(144);
    		upArrowFood.setWidth(29);
    		upArrowFood.setHeight(29);
    		renders.add(upArrowFood.getRender());
    		
    		doneButton = new Button("assets/images/auction/Done.png");
    		doneButton.setX(450);
    		doneButton.setY(300);
    		doneButton.setWidth(50);
    		doneButton.setHeight(30);
    		renders.add(doneButton.getRender());
    		
    		storeItemNames = new Render();
    		storeItemNames.x = 391;
    		storeItemNames.y = 100;
    		storeItemNames.addImage("assets/images/auction/ItemStrings.gif");
    		renders.add(storeItemNames);
    		
    		playerItemNames = new Render();
    		playerItemNames.x = 70;
    		playerItemNames.y = 100;
    		playerItemNames.addImage("assets/images/auction/ItemStrings.gif");
    		renders.add(playerItemNames);
    		
    		sellButton = new Button("assets/images/auction/sellBtn.gif");
    		sellButton.setX(160);
    		sellButton.setY(340);
    		sellButton.setWidth(117);
    		sellButton.setHeight(36);
    		renders.add(sellButton.getRender());
    		
    		storeText = new Render();
    		storeText.x = 465;
    		storeText.y = 60;
    		storeText.addImage("assets/images/auction/StoreText.gif");
    		renders.add(storeText);
    		
    		titleBar = new Render();
    		titleBar.x = 0;
    		titleBar.y = 0;
    		titleBar.addImage("assets/images/auction/titleBar.gif");
    		renders.add(titleBar); 
    		
    		// at start they have not touched buttons so amounts are 0 for transactions
    		currentOreAmount = 0; 
    		currentCrystiteAmount = 0;
    		currentEnergyAmount = 0;
    		currentFoodAmount = 0;
    		
    		store = AuctionStore.getInstance();
    		shouldSwitchPlayer = false;
	}

    /**
     * Called every time the thread ticks.
     */
    @Override
    public void update() 
    {
        stringRenders.clear();
        renders.clear();
        
        //add all renders to both arrays here
		renders.add(buyButton.getRender());
		renders.add(downArrowOre.getRender());
		renders.add(downArrowCrystite.getRender());
		renders.add(downArrowEnergy.getRender());
		renders.add(downArrowFood.getRender());
		renders.add(upArrowOre.getRender());
		renders.add(upArrowCrystite.getRender());
		renders.add(upArrowEnergy.getRender());
		renders.add(upArrowFood.getRender());
		renders.add(storeItemNames);
		renders.add(playerItemNames);
		renders.add(sellButton.getRender());
		renders.add(storeText);
		renders.add(titleBar);
		renders.add(doneButton.getRender());
		
		// Adding Renderable Strings that never change
		stringRenders.add(new StringRender("Amount", 5, 195));
		stringRenders.add(new StringRender("     in    ->", 5, 205));
		stringRenders.add(new StringRender("Transaction", 5, 215));
		stringRenders.add(new StringRender("Your", 5, 295));
		stringRenders.add(new StringRender("Current ->", 5, 305));
		stringRenders.add(new StringRender("Quantity", 5, 315));
	
		stringRenders.add(new StringRender("Buy Price ->", 285, 160));
		stringRenders.add(new StringRender("Sell Price ->", 285, 215));
		stringRenders.add(new StringRender("Quantity \nto \nSell ->", 270, 260));
		
		// now need to add renderable strings that are number values for certain resources
		stringRenders.add(new StringRender(currentOreAmount + "", 90, 205));
		stringRenders.add(new StringRender(currentCrystiteAmount + "", 140, 205));
		stringRenders.add(new StringRender(currentEnergyAmount + "", 190, 205));
		stringRenders.add(new StringRender(currentFoodAmount + "", 240, 205));
		
		String playerId = session.getCurrentPlayerId();
		stringRenders.add(new StringRender(session.getPlayerOre(playerId) + "", 90, 315));
		stringRenders.add(new StringRender(session.getPlayerCrystite(playerId) + "", 140, 315));
		stringRenders.add(new StringRender(session.getPlayerEnergy(playerId) + "", 190, 315));
		stringRenders.add(new StringRender(session.getPlayerFood(playerId) + "", 240, 315));

		
		stringRenders.add(new StringRender("CURRENT MONEY", 310, 300));
		stringRenders.add(new StringRender("__________________", 310, 305));
		stringRenders.add(new StringRender(session.getPlayerMoney(playerId) + "", 350, 325));
		
		// now for the store's values
		stringRenders.add(new StringRender(store.getBuyPrice("ore") + "", 400, 160));
		stringRenders.add(new StringRender(store.getBuyPrice("crystite") + "", 450, 160));
		stringRenders.add(new StringRender(store.getBuyPrice("energy") + "", 500, 160));
		stringRenders.add(new StringRender(store.getBuyPrice("food") + "", 550, 160));
		
		stringRenders.add(new StringRender(store.getSellPrice("ore") + "", 400, 215));
		stringRenders.add(new StringRender(store.getSellPrice("crystite") + "", 450, 215));
		stringRenders.add(new StringRender(store.getSellPrice("energy") + "", 500, 215));
		stringRenders.add(new StringRender(store.getSellPrice("food") + "", 550, 215));
		
		stringRenders.add(new StringRender(store.getQuantity("ore") + "", 400, 260));
		stringRenders.add(new StringRender(store.getQuantity("crystite") + "", 450, 260));
		stringRenders.add(new StringRender(store.getQuantity("energy") + "", 500, 260));
		stringRenders.add(new StringRender(store.getQuantity("food") + "", 550, 260));
    }

    /**
     * Checks to see if the player's turn in the auction round should
     * advance
     * @return true if the current player is done auctioning.
     */
    @Override
    public boolean shouldSwitch() 
    {
    	if (shouldSwitchPlayer) 
    	{
    		shouldSwitchPlayer = false;
    		return true;
    	}
    	return false;
    }

    /**
     * Determines what button was clicked in the auction round.
     * @param x
     * @param y
     * @param isLeftMouse
     */
    public void click(int x, int y, boolean isLeftMouse)
    {
        if (!isLeftMouse) return;
        if (upArrowOre.inBounds(x, y)) 
        {
        	currentOreAmount++;
        } 
        else if (upArrowCrystite.inBounds(x,y)) 
        {
        	currentCrystiteAmount++;
        } 
        else if (upArrowEnergy.inBounds(x, y)) 
        {
        	currentEnergyAmount++;
        } 
        else if (upArrowFood.inBounds(x, y)) 
        {
        	currentFoodAmount++;
        } 
        else if (downArrowOre.inBounds(x, y) && currentOreAmount > 0)
        {
        	currentOreAmount--;
        } 
        else if (downArrowCrystite.inBounds(x, y) && currentCrystiteAmount > 0) 
        {
        	currentCrystiteAmount--;
        } 
        else if (downArrowEnergy.inBounds(x, y) && currentEnergyAmount > 0) 
        {
        	currentEnergyAmount--;
        } 
        else if (downArrowFood.inBounds(x, y) && currentFoodAmount > 0) 
        {
        	currentFoodAmount--;
        } 
        else if (buyButton.inBounds(x, y)) 
        {
        	if (checkPurchase()) 
        	{
        		processPurchase();
        	}
        } 
        else if (sellButton.inBounds(x, y)) 
        {
        	if (checkSale()) 
        	{
        		processSale();
        	}
        } 
        else if (doneButton.inBounds(x, y)) 
        {
        	shouldSwitchPlayer = true;
        }
    }
    
    private boolean checkSale()
    {
    	Window window = Window.getInstance();
    	
    	String playerId = session.getCurrentPlayerId();
    	// now check to see if player has enough of resources to sell
    	if (currentOreAmount > session.getPlayerOre(playerId)) 
    	{
    		JOptionPane.showMessageDialog(window, "You do not have as much ore to sell as you want!");
    		return false;
    	}
    	
    	if (currentCrystiteAmount > session.getPlayerCrystite(playerId)) 
    	{
    		JOptionPane.showMessageDialog(window, "You do not have as much crystite to sell as you want!");
    		return false;
    	}
    	
    	if (currentEnergyAmount > session.getPlayerEnergy(playerId)) 
    	{
    		JOptionPane.showMessageDialog(window, "You do not have as much energy to sell as you want!");
    		return false;
    	}
    	
    	if (currentFoodAmount > session.getPlayerFood(playerId)) 
    	{
    		JOptionPane.showMessageDialog(window, "You do not have as much food to sell as you want!");
    		return false;
    	}
    	return true;
    }
    
    private void processSale()
    {
    	String playerId = session.getCurrentPlayerId();
    	session.playerSellResource(playerId, "ore", currentOreAmount, store.getSellPrice("ore"));
    	session.playerSellResource(playerId, "crystite", currentCrystiteAmount, store.getSellPrice("crystite"));
    	session.playerSellResource(playerId, "energy", currentEnergyAmount, store.getSellPrice("energy"));
    	session.playerSellResource(playerId, "food", currentFoodAmount, store.getSellPrice("food"));
    	
    	// Now let's increment inventory of store.

    	store.buyResource("ore", currentOreAmount);
    	store.buyResource("crystite", currentCrystiteAmount);
    	store.buyResource("energy", currentEnergyAmount);
    	store.buyResource("food", currentFoodAmount);
    	
    	// reset amounts to 0 for next transaction
    	currentOreAmount = 0;
    	currentCrystiteAmount = 0;
    	currentEnergyAmount = 0;
    	currentFoodAmount = 0;
    }
    
    private boolean checkPurchase() 
    {
    	// get window so error box is centered over it
    	Window window = Window.getInstance();
    	int cost = calculateTotalCost();
    	
    	String playerId = session.getCurrentPlayerId();
    	if (cost > session.getPlayerMoney(playerId)) 
    	{
    		JOptionPane.showMessageDialog(window, "Not enough money!");
    		return false;
    	}
    	
    	// now check to see if store has enough of resources to sell
    	if (currentOreAmount > store.getQuantity("ore")) 
    	{
    		JOptionPane.showMessageDialog(window, "Store does not have as much ore to sell as you want!");
    		return false;
    	}
    	
    	if (currentCrystiteAmount > store.getQuantity("crystite")) 
    	{
    		JOptionPane.showMessageDialog(window, "Store does not have as much crystite to sell as you want!");
    		return false;
    	}
    	
    	if (currentEnergyAmount > store.getQuantity("energy")) 
    	{
    		JOptionPane.showMessageDialog(window, "Store does not have as much energy to sell as you want!");
    		return false;
    	}
    	
    	if (currentFoodAmount > store.getQuantity("food")) 
    	{
    		JOptionPane.showMessageDialog(window, "Store does not have as much food to sell as you want!");
    		return false;
    	}
    	
    	return true;
    }
    
    private void processPurchase() 
    {
    	String playerId = session.getCurrentPlayerId();
    	session.playerBuyResource(playerId, "ore", currentOreAmount, store.getBuyPrice("ore"));
    	session.playerBuyResource(playerId, "crystite", currentCrystiteAmount, store.getBuyPrice("crystite"));
    	session.playerBuyResource(playerId, "energy", currentEnergyAmount, store.getBuyPrice("energy"));
    	session.playerBuyResource(playerId, "food", currentFoodAmount, store.getBuyPrice("food"));
    	
    	// Now let's increment inventory of player, decrease that of store, and reset amount values

    	store.sellResource("ore", currentOreAmount);
    	store.sellResource("crystite", currentCrystiteAmount);
    	store.sellResource("energy", currentEnergyAmount);
    	store.sellResource("food", currentFoodAmount);
    	
    	// reset amounts to 0 for next transaction
    	currentOreAmount = 0;
    	currentCrystiteAmount = 0;
    	currentEnergyAmount = 0;
    	currentFoodAmount = 0;
    }
    
    private int calculateTotalCost() 
    {
    	int total = 0;
    	total += currentOreAmount * store.getBuyPrice("ore");
    	total += currentCrystiteAmount * store.getBuyPrice("crystite");
    	total += currentEnergyAmount * store.getBuyPrice("energy");
    	total += currentFoodAmount * store.getBuyPrice("food");
    	
    	return total;
    }
}