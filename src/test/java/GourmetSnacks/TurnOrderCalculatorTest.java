package GourmetSnacks;

import static org.junit.Assert.assertEquals;
import game.LocalSession;
import game.PlayerType;
import game.TurnOrderCalculator;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: nickt
 * Date: 11/11/13
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TurnOrderCalculatorTest
{



    @Test
    public void basicTurnOrderTest()
    {
        LocalSession session = new LocalSession();
        ArrayList<String> ids = session.createPlayers(4);
        ArrayList<PlayerType> typeSets = new ArrayList<>();
        typeSets.add(PlayerType.BONZOID);
        typeSets.add(PlayerType.HUMAN);
        typeSets.add(PlayerType.BUZZITE);
        typeSets.add(PlayerType.FLAPPER);
        String bonzoid =  ids.get(0);
        String human = ids.get(1);
        String buzzite = ids.get(2);
        String flapper = ids.get(3);
        PlayerType type;

        for (String id : ids)
        {
            type = typeSets.remove(0);
            session.setPlayerType(id,type);

        }

        session.sortPlayers(new TurnOrderCalculator());
        ArrayList<String> sortedIds = session.getPlayerIds();
        ArrayList<String> expectedSorts = new ArrayList<>();
        expectedSorts.add(human);
        expectedSorts.add(bonzoid);
        expectedSorts.add(buzzite);
        expectedSorts.add(flapper);

        assertEquals(expectedSorts,sortedIds);
    }

    @Test
    public void identicalCharacterTest()
    {
        LocalSession session = new LocalSession();
        ArrayList<String> ids = session.createPlayers(4);
        ArrayList<PlayerType> typeSets = new ArrayList<>();
        typeSets.add(PlayerType.BONZOID);
        typeSets.add(PlayerType.BONZOID);
        typeSets.add(PlayerType.BONZOID);
        typeSets.add(PlayerType.BONZOID);
        String bonzoid0 =  ids.get(0);
        String bonzoid1 = ids.get(1);
        String bonzoid2 = ids.get(2);
        String bonzoid3 = ids.get(3);
        PlayerType type;

        for (String id : ids)
        {
            type = typeSets.remove(0);
            session.setPlayerType(id,type);

        }

        session.sortPlayers(new TurnOrderCalculator());
        ArrayList<String> sortedIds = session.getPlayerIds();
        ArrayList<String> expectedSorts = new ArrayList<>();
        expectedSorts.add(bonzoid0);
        expectedSorts.add(bonzoid1);
        expectedSorts.add(bonzoid2);
        expectedSorts.add(bonzoid3);

        assertEquals(expectedSorts,sortedIds);
    }

}
