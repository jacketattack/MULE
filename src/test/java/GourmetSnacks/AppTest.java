package GourmetSnacks;

import core.StateProcessor;
import core.Utils;
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

    public void testRiverPlotDefaults()
    {
    		Plot river = new Plot(PlotType.RIVER);
    		
    		assertTrue(river.getFoodProduction() == 4);
    		assertTrue(river.getEnergyProduction() == 2);
    		assertTrue(river.getOreProduction() == 0);
    }

    public void testPlainPlotDefaults()
    {
    		Plot plain = new Plot(PlotType.PLAIN);
    		
    		assertTrue(plain.getFoodProduction() == 2);
    		assertTrue(plain.getEnergyProduction() == 3);
    		assertTrue(plain.getOreProduction() == 1);
    }

    public void testMountain_1_PlotDefaults()
    {
    		Plot mountain = new Plot(PlotType.MOUNTAIN_1);
    		
    		assertTrue(mountain.getFoodProduction() == 1);
    		assertTrue(mountain.getEnergyProduction() == 1);
    		assertTrue(mountain.getOreProduction() == 2);
    }

    public void testMountain_2_PlotDefaults()
    {
    		Plot mountain = new Plot(PlotType.MOUNTAIN_2);
    		
    		assertTrue(mountain.getFoodProduction() == 1);
    		assertTrue(mountain.getEnergyProduction() == 1);
    		assertTrue(mountain.getOreProduction() == 3);
    }


    public void testMountain_3_PlotDefaults()
    {
    		Plot mountain = new Plot(PlotType.MOUNTAIN_3);
    		
    		assertTrue(mountain.getFoodProduction() == 1);
    		assertTrue(mountain.getEnergyProduction() == 1);
    		assertTrue(mountain.getOreProduction() == 4);
    }
    
    
    public void testStateProcessorSingleton()
    {
    		assertTrue(StateProcessor.getInstance() == StateProcessor.getInstance());
    }
}
