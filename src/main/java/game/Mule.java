package game;

/**
 * A Mule that follows a player and can upgrade plots
 */
public class Mule extends Follower
{	
	private static final long serialVersionUID = 736878467961103798L;

	/** Whether the mule is running away */
	private boolean runningAway;
	
	/** The improvement type the mule is carrying */
	private ImprovementType type;
	
	/**
	 * Create a new mule and follower a player
	 * @param playerId The id of the player to follow
	 */
	public Mule(String playerId)
	{
		super(playerId);
		setType(ImprovementType.EMPTY);
		runningAway = false;
	}
	
	/**
	 * Set the improvement type
	 * @param type The improvement type
	 */
	public void setType(ImprovementType type)
	{
		this.type = type;
		imagePath = type.getMuleImagePath();
	}

	/**
	 * Get the improvement type
	 * @return type The improvement type
	 */
	public ImprovementType getType()
	{
		return type;
	}
	
	/**
	 * Tell the mule to run away
	 */
	public void runAway()
	{
		runningAway = true;
	}
	
	/**
	 * Update the mule based on its state
	 */
	public void update() 
	{
		if (!runningAway)
		{
			super.update();
		}
		else 
		{
			x++;
		}
	}
}
