package game.screen;

import game.Session;

/**
 *
 * @author nteissler
 */
public class AuctionSellScreen extends AuctionScreen
{

    public AuctionSellScreen(Session session) {
        super(session);
    }

    @Override
    public void update() {
    }

    @Override
    public boolean shouldSwitch() 
    {
        return false;
    }
    
}
