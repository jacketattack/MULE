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
    private HashMap<String,Integer> prices;
    
    private AuctionStore()
    {
        inventory = new Inventory();
        prices = new HashMap<>(inventory.itemsCount());
        prices.put("food", 50);
        prices.put("crystite",100);
        prices.put("energy", 100);
        prices.put("ore", 75);
                
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
    
    public boolean checkSell(String resource, int sellAmount)
	{
		boolean canSell = false;
		switch (resource) {
			case "ore":
				canSell = inventory.ore >= sellAmount;
				break;
			case "crystite":
				canSell = inventory.crystite >= sellAmount;
				break;
			case "food":
				canSell = inventory.food >= sellAmount;
				break;
			case "energy":
				canSell = inventory.energy >= sellAmount;
				break;
		}
		return canSell;
	}
	
	public void sellResource(String resource, int quantity, int price)
	{
		switch (resource) {
			case "ore":
				incrementOre(-quantity);
				incrementMoney(price * quantity);
				break;
			case "crystite":
				incrementCrystite(-quantity);
				incrementMoney(price * quantity);
				break;
			case "food":
				incrementFood(-quantity);
				incrementMoney(price * quantity);
				break;
			case "energy":
				incrementEnergy(-quantity);
				incrementMoney(price * quantity);
				break;
		}
	}
	
	public void buyResource(String resource, int quantity, int price) 
	{
		switch (resource) {
		case "ore":
			incrementOre(quantity);
			incrementMoney( -(price * quantity) );
			break;
		case "crystite":
			incrementCrystite(quantity);
			incrementMoney(- (price * quantity));
			break;
		case "food":
			incrementFood(quantity);
			incrementMoney( -(price * quantity));
			break;
		case "energy":
			incrementEnergy(quantity);
			incrementMoney( -(price * quantity) );
			break;
		}
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
	
	public boolean checkBuy(int cost)
	{
		return cost >= inventory.money;
	}
}
