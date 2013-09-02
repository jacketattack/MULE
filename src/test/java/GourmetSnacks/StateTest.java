package GourmetSnacks;

import game.state.GameState;
import junit.framework.TestCase;

import org.junit.Test;

import core.StateSelector;

/**
 * 
 * @author grant
 * @author
 */
public class StateTest extends TestCase
{
    public StateTest( String testName )
    {
        super( testName );
    }

    @Test
    public void testStateEquality()
    {
		GameState gameState = new GameState();
		
		StateSelector stateSelector = StateSelector.getInstance();
		stateSelector.setState(gameState);
		
		assertEquals(gameState, stateSelector.getState());
    }
}
