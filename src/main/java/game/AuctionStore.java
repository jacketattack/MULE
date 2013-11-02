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
        sellPrices = new HashMap<>(inventory.itemsCount());
        sellPrices.put("food", 50);
        sellPrices.put("crystite",100);
        sellPrices.put("energy", 100);
        sellPrices.put("ore", 75);
        
        buyPrices = new HashMap<>(inventory.itemsCount());
        buyPrices.put("food", 40);
        buyPrices.put("crystite",70);
        buyPrices.put("energy", 80);
        buyPrices.put("ore", 60);
                
        inventory.food = 10;
        inventory.crystite = 10;
        inventory.energy = 10;
        inventory.ore = 10;
        inventory.money = 12000;
    }
    
    public AuctionStore getInstance()
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
