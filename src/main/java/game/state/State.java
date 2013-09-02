package game.state;

/**
 * 
 * @author grant
 * @author
 */
public interface State 
{
	/**
	 * This method is called once, every frame by the State Processor. 
	 * The "meat" of every state should reside in this method.
	 */
	public void update();
}
