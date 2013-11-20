package game.round;

import game.TurnOrderCalculator;
import game.screen.AuctionScreen;

import java.util.ArrayList;

import ui.render.StringRender;

public class AuctionRound extends Round 
{
	private AuctionScreen screen;

    private ArrayList<String> playerIds;
    private boolean done;

    /**
     * Default constructor, initiates the player list
     */
    public AuctionRound()
    {
        playerIds = new ArrayList<>();
    }
    
    /**
     * The round initializer. Sets everything up and ready to go.
     */
    @Override
    public void init()
    {
		// sort players
		session.sortPlayers(new TurnOrderCalculator());

		playerIds = new ArrayList<>();
		// deep copy so we can remove them when we want
		for (String id : session.getPlayerIds())
		{			
			playerIds.add(id);
		}

        screen = new AuctionScreen(session);           
    }


    /**
     * Update method called each frame of the game. Adds all objects and strings that need
     * to be drawn on the screen.
     */
    @Override
    public void update() 
    {
        renders.clear();
        stringRenders.clear();

        if (screen.shouldSwitch())
        {
        	done = session.advancePlayer();
        }
        else
        {
        	String playerId = session.getCurrentPlayerId();
        	
			screen.setPlayerId(playerId);
			screen.update();
			
			StringRender characterName = new StringRender(session.getPlayerName(playerId), 160, 80);
			stringRenders.add(characterName);
	       		
			renders.addAll(screen.getRenders());
			stringRenders.addAll(screen.getStringRenders());
    	}
    }


    /**
     * Click method that passes handling the responsibility of the click
     * to the screen that is currently displayed for the round.
     * @param x The x pos in pixels
     * @param y The y pos in pixels
     * @param leftMouse Whether the left mouse was pressed
     */
    @Override
    public void click(int x, int y, boolean leftMouse) 
    {
        screen.click(x,y,leftMouse);
    }
    
    /**
     * Determines whether round has finished or not.
     * More specifically, since every character only has the chance
     * to go once, this round is over when the character index
     * has passed through each character one time.
     * @return whether the auction round is done or not
     */
    @Override
    public boolean isDone() 
    {
       return done;
    }
}
