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

public abstract class AuctionScreen extends Screen 
{	
	public AuctionScreen(Session session) 
	{
		super(session);
	}

    /**
     * Update refreshes the screen and draws the different entities
     */
    public abstract void update();

            //Clear the renderables array so things aren't being drawn on
            //top of everything
            //renderables.clear();
            //renderableStrings.clear();

            //add the proper simpleRender images and buttons to the scree
            //in here. Use characters from session to populate the inventory


    /**
     * Is called when the player is finished buying and should move to sell round
     * @return the boolean whether or not the player is done buying
     */
    public abstract boolean shouldSwitch();
}