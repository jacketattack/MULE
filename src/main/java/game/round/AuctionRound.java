/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.round;

import game.TurnOrderCalculator;
import game.screen.AuctionScreen;

import java.util.ArrayList;

import ui.render.RenderableString;

/**
 *
 * @author nicholast
 */
public class AuctionRound extends Round 
{
    private AuctionScreen screen;

    private ArrayList<String> playerIds;
    private boolean done;
    
    public AuctionRound()
    {
        playerIds = new ArrayList<String>();
    }
    
    
    /**
     * The round initializer. Sets everything up and ready to go.
     */
    @Override
    public void init()
    {
		// sort players
		session.sortPlayers(new TurnOrderCalculator());

		playerIds = new ArrayList<String>();
		// deep copy so we can remove them when we want
		for (String id : session.getPlayerIds())
		{			
			playerIds.add(id);
		}

        screen = new AuctionScreen(session);           
    }
    
    @Override
    public void update() 
    {
        renderables.clear();
        renderableStrings.clear();
        
        if (screen.shouldSwitch())
        {
        	done = session.advancePlayer();
        }
        else
        {
        	String playerId = session.getCurrentPlayerId();
        	
			screen.setPlayerId(playerId);
			screen.update();
			
			RenderableString characterName = new RenderableString(session.getPlayerName(playerId), 160, 80);
			renderableStrings.add(characterName);
	       		
			renderables.addAll(screen.getRenderables());
			renderableStrings.addAll(screen.getRenderableStrings());
    	}
    }

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
     * @return 
     */
    @Override
    public boolean isDone() 
    {
       return done;
    }
}
