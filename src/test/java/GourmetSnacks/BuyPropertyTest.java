package GourmetSnacks;

import game.*;
import game.round.LandGrantRound;
import junit.framework.TestCase;
import game.Session;
import game.LocalSession;
import game.Map;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.matchers.JUnitMatchers;

import java.util.ArrayList;


/**
 *
 */
public class BuyPropertyTest extends TestCase
{
    LandGrantRound round = new LandGrantRound();
    Session session = new LocalSession();
    ArrayList<String> ids = session.createPlayers(2);

    String id1 = ids.get(0);
    String id2 = ids.get(1);



    @Test
    public void testNoMoney()
    {

        session.setMap(new Map(true));
        session.setPlayerType(id1, PlayerType.FLAPPER);
        session.setPlayerType(id2, PlayerType.HUMAN);
        round.setSession(session);
        round.init();



        //Player 1

        round.click(0,0,true);
        round.click(0,Plot.SIZE*1+1,true);
        round.click(Plot.SIZE + 1 , 0 , true);
        round.click(Plot.SIZE+1, Plot.SIZE+1,true);
        round.click(Plot.SIZE*2 + 1, Plot.SIZE*2 + 1,true );
        round.click(Plot.SIZE*3 + 1, Plot.SIZE*1 + 1,true );
        round.click(Plot.SIZE*3 + 1, 1,true );
        round.click(Plot.SIZE*3 + 1, Plot.SIZE*3 + 1,true );

        round.click(Plot.SIZE*4+1,1,true);


        boolean bought = session.isPlotOwnedByPlayer(id2,session.getPlot(4,0));
        assertEquals(bought ,false);

    }
    @Test
    public void testKickOutPoorPlayers () {
        session.setMap(new Map(true));
        session.setPlayerType(id1, PlayerType.FLAPPER);
        session.setPlayerType(id2, PlayerType.HUMAN);
        round.setSession(session);
        round.init();



        //Player 1

        round.click(0,0,true);
        round.click(0,Plot.SIZE*1+1,true);
        round.click(Plot.SIZE + 1 , 0 , true);
        round.click(Plot.SIZE+1, Plot.SIZE+1,true);
        round.click(Plot.SIZE*2 + 1, Plot.SIZE*2 + 1,true );
        round.click(Plot.SIZE*3 + 1, Plot.SIZE*1 + 1,true );
        round.click(Plot.SIZE*3 + 1, 1,true );
        round.click(Plot.SIZE*3 + 1, Plot.SIZE*3 + 1,true );

        round.click(Plot.SIZE*4+1,1,true);//player is now kicked out

        round.click(Plot.SIZE*4+1,Plot.SIZE*1+1,true);
        round.click(Plot.SIZE*4+1,Plot.SIZE*2+1,true);
        int moneyBefore = session.getPlayerMoney(id1) - 300;
        round.click(1,Plot.SIZE *3,true);
        int moneyAfter = (session.getPlayerMoney(id1));
        assertEquals(moneyAfter,moneyBefore);
    }


    @Test
    public void testFreePlots(){
        session.setMap(new Map(true));
        session.setPlayerType(id1, PlayerType.FLAPPER);
        session.setPlayerType(id2, PlayerType.HUMAN);
        round.setSession(session);
        round.init();

        int moneyBeforeA = session.getPlayerMoney(id1);
        int moneyBeforeB = session.getPlayerMoney(id2);

        round.click(0,0,true);
        round.click(0,Plot.SIZE*1+1,true);
        round.click(Plot.SIZE + 1 , 0 , true);
        round.click(Plot.SIZE+1, Plot.SIZE+1,true);

        int moneyAfterA = session.getPlayerMoney(id1);
        int moneyAfterB = session.getPlayerMoney(id2);

        assertEquals(moneyBeforeA,moneyAfterA);
        assertEquals(moneyBeforeB,moneyAfterB);
    }


    @Test
    public void testOtherPeoplesPlots () {
        session.setMap(new Map(true));
        session.setPlayerType(id1, PlayerType.FLAPPER);
        session.setPlayerType(id2, PlayerType.HUMAN);
        round.setSession(session);
        round.init();

        round.click(0,0,true);
        round.click(0,Plot.SIZE*1+1,true);
        round.click(Plot.SIZE + 1 , 0 , true);
        String current = session.getCurrentPlayerId();
        round.click(Plot.SIZE+1, 0,true);
        String after = session.getCurrentPlayerId();
        assertEquals(current,after);
    }


}
