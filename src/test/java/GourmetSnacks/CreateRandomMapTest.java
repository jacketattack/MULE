package GourmetSnacks;

import static org.junit.Assert.assertEquals;
import game.Map;
import game.Plot;
import game.PlotType;
import junit.framework.TestCase;

import org.junit.Test;


/**
 * This is a JUnit test case for how we generate a random map. 
 * 
 * One of the key rules in the algorithm for generating a map in MULE
 * is that every row must contain total mountains among plots that 
 * cumulatively sum to 4, and exactly 4.
 * 
 * @author trevor
 *
 */
public class CreateRandomMapTest extends TestCase {

	/**
	 * This is the test as described in part above that 
	 * makes sure that each row has 4 mountains total.
	 */
	@Test
	public void testMapBoolean() {
		
		// Let's test creating a Random map many, many times to greatly increase chance of catching any errors!
		
		for (int i = 0; i < 10000; i++) {
			
			boolean makeRandom = true;
			Map map = new Map(makeRandom);
			
			int sumMountains = 0; // this keeps track of number of individual mountains in each row, should be 4
			Plot currPlot;
			
			for (int row = 0; row < 5; row++) {
				sumMountains = 0; // reset counter for each new row
				for (int col = 0; col < 9; col++) {
					currPlot = map.getPlot(row, col);
					if (currPlot.getType() == PlotType.MOUNTAIN_1) {
						sumMountains += 1;
					} else if (currPlot.getType() == PlotType.MOUNTAIN_2) {
						sumMountains += 2;
					} else if (currPlot.getType() == PlotType.MOUNTAIN_3) {
						sumMountains += 3;
					}
				}
				assertEquals(4, sumMountains);
			}
		}
	}
		
}
