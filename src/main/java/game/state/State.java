package game.state;

/**
 * The state interface
 */
public interface State 
{
    /**
     * This method is called every frame by the 
     * StateUpdater. The majority of the work will
     * run through this method.
     */
    public void update();
}
