package game.store;

import game.Inventory;

import java.util.HashMap;

/**
 * This is a singleton class that controls the store's stock
 * of items and how much they are exchanged for. 
 * 
 * The class contains an inventory that is stocked from the beginning
 * of the game, then changes based on what other characters buy and sell from
 * the store
 */
public class AuctionStore 
{
    private static AuctionStore instance;
    private Inventory inventory;
    private HashMap<String,Integer> sellPrices;
    private HashMap<String,Integer> buyPrices;

    /**
     * AuctionStore is a singleton that manages buying and selling of resources with the store, the private constructor sets
     * the initial characteristics of the store.
     */
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
        sellPrices = new HashMap<>(inventory.itemCount());
        sellPrices.put("food", 40 / (inventory.food + 10));
        sellPrices.put("crystite", 70 / (inventory.crystite + 10));
        sellPrices.put("energy", 80 / (inventory.energy + 10));
        sellPrices.put("ore", 60 / (inventory.ore + 10));
        
        buyPrices = new HashMap<>(inventory.itemCount());
        buyPrices.put("food", 50 / (inventory.food + 1));
        buyPrices.put("crystite", 100 / (inventory.crystite + 1));
        buyPrices.put("energy", 100 / (inventory.energy + 1));
        buyPrices.put("ore", 75 / (inventory.ore+ 1));
    }

    /**
     * Grabs the instance of the singleton.
     * @return instance - the instance of the singleton
     */
    public static AuctionStore getInstance()
    {
        if (instance == null)
        {
            instance =  new AuctionStore();
            return instance;
        }
        else 
        {
            return instance;
        }
    }

    /**
     * Checks wether or not the transaction is possible
     * @param resource - the resource to buy or sell
     * @param quantity  - the amount to buy or sell
     * @return bool -  if it is possible to perform.
     */
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

    /**
     * Facilitates the selling of a resource
     * @param resource-the resource to sell
     * @param quantity - the amoount to sell
     */
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

    /**
     * facilitates buying resources between the player and the store
     *
     * @param resource the resource to buy
     * @param quantity the amount to buy
     */
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
        sellPrices.put("food", 40 / (inventory.food + 10));
        sellPrices.put("crystite", 70 / (inventory.crystite + 10));
        sellPrices.put("energy", 80 / (inventory.energy + 10));
        sellPrices.put("ore", 60 / (inventory.ore + 10));
        
        buyPrices.clear();
        buyPrices.put("food", 50 / (inventory.food + 1));
        buyPrices.put("crystite", 100 / (inventory.crystite + 1));
        buyPrices.put("energy", 100 / (inventory.energy + 1));
        buyPrices.put("ore", 75 / (inventory.ore+ 1));
    }

    /**
     * get the prices to sell
     * @param resource the resource
     * @return price - the price per unit
     */
    public int getSellPrice(String resource)
    {
        return sellPrices.get(resource);
    }

    /**
     * getthe price to buy
     *
     * @param resource  the Resource
     * @return  - the price
     */
    public int getBuyPrice(String resource)
    {
        return buyPrices.get(resource);
    }

    /**
     * returns the amount of available of a resource
     * @param resource - the resource
     * @return  - the amount
     */
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

    /**
     * increments the ore
     * @param ore the amount to increment
     */
    public void incrementOre(int ore)
    {
            inventory.ore += ore;
    }

    /**
     * increment the crystite
     * @param crystite the amount to increment
     */
    public void incrementCrystite(int crystite)
    {
            inventory.crystite += crystite;
    }

    /**
     * increment the food
     * @param food  the amount to increment
     */
    public void incrementFood(int food)
    {
            inventory.food += food;
    }

    /**
     * increment the energy
     * @param energy the amount to increment
     */
    public void incrementEnergy(int energy)
    {
            inventory.energy += energy;
    }

    /**
     * increment the money
     * @param money  the amount to increment
     */
    public void incrementMoney(int money)
    {
            inventory.money += money;
    }

    /**
     * checks to see if the buy is possible given amounts
     * @param resource - the resource
     * @param quantity - the quantity to try to buy
     * @return - whether or not buying is possible
     */
    public boolean checkBuy(String resource, int quantity)
    {
            return buyPrices.get(resource)*quantity<= inventory.money;
    }
        
        
}
