package GourmetSnacks;

import core.StateProcessor;
import game.Player;
import game.Plot;
import game.PlotType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testPlayer()
    {
    		Player player = new Player();
    		player.name = "Grant";
    		assertTrue(player.name.equals("Grant"));
    }

    public void testPlotDefaults()
    {
    		Plot river = new Plot(PlotType.RIVER);
    		assertTrue(river.getFoodProduction() == 8);
    }
    
    public void testStateProcessorSingleton()
    {
    		assertTrue(StateProcessor.getInstance() == StateProcessor.getInstance());
    }
}
