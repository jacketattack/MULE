package game;

import java.util.HashMap;

/**
 * This is a singleton class that controls the store's stock
 * of items and how much they are exchanged for. 
 * 
 * The class contains an inventory that is stocked from the beginning
 * of the game, then changes based on what other characters buy and sell from
 * the store
 * @author nicholast
 */
public class AuctionStore 
{
    private static AuctionStore instance;
    private Inventory inventory;
    private HashMap<String,Integer> sellPrices;
    private HashMap<String,Integer> buyPrices;
    
    private AuctionStore()
    {
        //think about implementing price randomization as long
        //as sell prices remain higher than buy prices
        inventory = new Inventory();
        
        inventory.food = 10;
        inventory.crystite = 10;
        inventory.energy = 10;
        inventory.ore = 10;
        inventory.money = 12000;
        
        // @Trevor, I made prices now change with supply..more supply, cheaper price
        sellPrices = new HashMap<>(inventory.itemsCount());
        sellPrices.put("food", (int) (40 / (inventory.food + 10)) );
        sellPrices.put("crystite", (int) (70 / (inventory.crystite + 10) ));
        sellPrices.put("energy", (int) (80 / (inventory.energy + 10) ));
        sellPrices.put("ore", (int) (60 / (inventory.ore + 10) ));
        
        buyPrices = new HashMap<>(inventory.itemsCount());
        buyPrices.put("food", (int) (50 / (inventory.food + 1)) );
        buyPrices.put("crystite", (int) (100 / (inventory.crystite + 1) ));
        buyPrices.put("energy", (int) (100 / (inventory.energy + 1) ));
        buyPrices.put("ore", (int) (75 / (inventory.ore+ 1) ) );
                

    }
    
    public static AuctionStore getInstance()
    {
        if (instance == null)
        {
            return new AuctionStore();
        }
        else 
        {
            return instance;
        }
    }
    
    public boolean checkSell(String resource,int quantity)
    {
            boolean canSell = false;
            switch (resource) 
            {
                    case "ore":
                            canSell = inventory.ore >= quantity;
                            break;
                    case "crystite":
                            canSell = inventory.crystite >= quantity;
                            break;
                    case "food":
                            canSell = inventory.food >= quantity;
                            break;
                    case "energy":
                            canSell = inventory.energy >= quantity;
                            break;
            }
            return canSell;
    }
	
    public void sellResource(String resource, int quantity)
    {
            switch (resource) 
            {
                    case "ore":
                            incrementOre(-quantity);
                            incrementMoney(sellPrices.get(resource) * quantity);
                            break;
                    case "crystite":
                            incrementCrystite(-quantity);
                            incrementMoney(sellPrices.get(resource) * quantity);
                            break;
                    case "food":
                            incrementFood(-quantity);
                            incrementMoney(sellPrices.get(resource) * quantity);
                            break;
                    case "energy":
                            incrementEnergy(-quantity);
                            incrementMoney(sellPrices.get(resource) * quantity);
                            break;
            }
            updatePrices();
    }

    public void buyResource(String resource, int quantity) 
    {
            switch (resource) 
            {
                    case "ore":
                            incrementOre(quantity);
                            incrementMoney( -(buyPrices.get(resource) * quantity) );
                            break;
                    case "crystite":
                            incrementCrystite(quantity);
                            incrementMoney(- (buyPrices.get(resource) * quantity));
                            break;
                    case "food":
                            incrementFood(quantity);
                            incrementMoney( -(buyPrices.get(resource) * quantity));
                            break;
                    case "energy":
                            incrementEnergy(quantity);
                            incrementMoney( -(buyPrices.get(resource) * quantity) );
                            break;
              }
            updatePrices();
    }
    
    /**
     * Every time we actually do a sell or buy, supply will change,
     * and thus prices should change as well.
     */
    private void updatePrices()
    {
    	sellPrices.clear();
        sellPrices.put("food", (int) (40 / (inventory.food + 10)) );
        sellPrices.put("crystite", (int) (70 / (inventory.crystite + 10) ));
        sellPrices.put("energy", (int) (80 / (inventory.energy + 10) ));
        sellPrices.put("ore", (int) (60 / (inventory.ore + 10) ));
        
        buyPrices.clear();
        buyPrices.put("food", (int) (50 / (inventory.food + 1)) );
        buyPrices.put("crystite", (int) (100 / (inventory.crystite + 1) ));
        buyPrices.put("energy", (int) (100 / (inventory.energy + 1) ));
        buyPrices.put("ore", (int) (75 / (inventory.ore+ 1) ) );	
    }
    

    public int getSellPrice(String resource)
    {
        return sellPrices.get(resource);
    }
    
    public int getBuyPrice(String resource)
    {
        return buyPrices.get(resource);
    }
    
    public int getQuantity(String resource)
    {
        switch(resource)
        {
                case "money":
                    return inventory.money;
                case "crystite":
                    return inventory.crystite;
                case "food":
                    return inventory.food;
                case "energy":
                    return inventory.energy;
                case "ore":
                    return inventory.ore;                           
        }
        return 0;   
    }

    public void incrementOre(int ore)
    {
            inventory.ore += ore;
    }

    public void incrementCrystite(int crystite)
    {
            inventory.crystite += crystite;
    }

    public void incrementFood(int food)
    {
            inventory.food += food;
    }

    public void incrementEnergy(int energy)
    {
            inventory.energy += energy;
    }

    public void incrementMoney(int money)
    {
            inventory.money += money;
    }

    public boolean checkBuy(String resource, int quantity)
    {
            return buyPrices.get(resource)*quantity<= inventory.money;
    }
        
        
}
