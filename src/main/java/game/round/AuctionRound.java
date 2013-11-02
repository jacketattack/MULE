/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.round;

import core.Keyboard;
import game.AuctionStore;
import game.screen.AuctionBuyScreen;
import game.screen.AuctionSellScreen;
import java.util.ArrayList;
import java.util.Comparator;
import game.Character;
import game.TurnOrderCalculator;
import game.screen.AuctionScreen;
import java.util.Collections;

/**
 *
 * @author nicholast
 */
public class AuctionRound extends Round 
{
    private Keyboard keyboard;
    private ArrayList<game.Character> characters;
    private Character current;
    private AuctionStore store;
    

    private AuctionScreen currentScreen;
    private AuctionBuyScreen auctionBuyScreen;
    private AuctionSellScreen auctionSellScreen;

    private Comparator<game.Character> turnOrderCalculator; 

    public AuctionRound()
    {
        keyboard = Keyboard.getInstance();
        store = AuctionStore.getInstance();
        //keyboard may be necessary, but I think this 
        //round is fine to handle with only mouse input and the
        //click function within the two Screen classes
    }
    
    
    /**
     * The round initializer. Sets everything up and ready to go.
     */
    @Override
    public void init()
    {
        turnOrderCalculator = new TurnOrderCalculator();
        characters = new ArrayList<>();
        for (Character character : session.getCharacters())
		{
			characters.add(character);
		}
	session.setCurrentCharacterIndex(0);
        Collections.sort(characters, this.turnOrderCalculator);
        auctionBuyScreen = new AuctionBuyScreen(session);
        auctionSellScreen = new AuctionSellScreen(session);
        currentScreen = auctionBuyScreen;

                
    }
    
    @Override
    public void update() 
    {
        renderables.clear();
        renderableStrings.clear();
        
        current = characters.get(session.getCurrentCharacterIndex());
        
        if (currentScreen.shouldSwitch())
        {
            advancePlayer();
        }
        
        
        
        
    }

    @Override
    public void click(int x, int y, boolean leftMouse) 
    {
        currentScreen.click(x,y,leftMouse);
    }
    
    private void switchScreen()
    {
        if (currentScreen instanceof AuctionSellScreen)
	{
		currentScreen = auctionBuyScreen;
	} 
	else 
	{            
            currentScreen = auctionSellScreen;
	}        
    }
    
    private void advancePlayer()
    {
        switchScreen();
        session.incrementCurrentCharacterIndex();
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
        //is this a good way to do this? Danger of indexOutOfBounds exception?
        if (session.getCurrentCharacterIndex() >= characters.size())
	{				
		return true;				
	}
	
	return false;
    }

    
}
