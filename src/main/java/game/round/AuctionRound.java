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
import game.screen.Screen;
import java.util.ArrayList;
import java.util.Comparator;
import game.Character;

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
    

    private Screen currentScreen;
    private AuctionBuyScreen auctionBuyScreen;
    private AuctionSellScreen auctionSellScreen;

    private Comparator<game.Character> turnOrderCalculator; 

    @Override
    public void update() 
    {
        
    }

    @Override
    public void click(int x, int y, boolean leftMouse) 
    {
        
    }

    @Override
    public boolean isDone() 
    {
        return false;
    }

    @Override
    public void init() 
    {
        
    }
    
}
