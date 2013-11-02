package game.screen;

import ui.Window;
import game.Character;
import game.Map;
import game.Plot;
import game.Session;

/**
 * Screen that serves as the backdrop for both buying and selling.
 * Only difference should really be whether the title says buy or sell
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

    /**
     * Is called when the player is finished buying and should move to sell round
     * @return the boolean whether or not the player is done buying
     */
    public abstract boolean shouldSwitch();
    
    public void click()
    {
        //implement all options of buttons being clicked in here
    }
}