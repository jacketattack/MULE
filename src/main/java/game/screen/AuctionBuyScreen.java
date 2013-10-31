package game.screen;

import ui.Window;
import game.Character;
import game.Map;
import game.Plot;
import game.Session;

/**
 * The AuctionBuy screen is almost identical to AuctionSell except for buying
 * @author nteissler
 */

public class AuctionBuyScreen extends Screen 
{	
	public AuctionBuyScreen(Session session) 
	{
		super(session);
	}

    @Override
    public void update() 
    {
    }

    @Override
    public boolean shouldSwitch() 
    {
        return false;
    }

}