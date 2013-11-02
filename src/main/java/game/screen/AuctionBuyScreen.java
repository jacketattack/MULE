package game.screen;

import game.Character;
import game.Session;

import ui.Window;
import ui.Button ;
import ui.ToggleButton; //may not need to use
import core.ImageLoader;
import core.render.RenderableString;
import core.render.Renderable; //may not need to use
import core.render.SimpleRender;

/**
 * The AuctionBuy screen is almost identical to AuctionSell except for buying
 * @author nteissler
 */

public class AuctionBuyScreen extends AuctionScreen 
{	
	public AuctionBuyScreen(Session session) 
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
	}

    @Override
    public void update() 
    {
        renderableStrings.clear();
        renderables.clear();
        
        //add all renderables to both arrays here

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