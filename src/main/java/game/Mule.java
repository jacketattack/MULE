package game;

import ui.Window;

public class Mule extends Follower
{	
	private static final long serialVersionUID = 736878467961103798L;

	private boolean runningAway;
	private ImprovementType type;
	
	public Mule(String playerId)
	{
		super(playerId);
		setType(ImprovementType.EMPTY);
		runningAway = false;
	}
	
	public void setType(ImprovementType type)
	{
		this.type = type;
		imagePath = type.getMuleImageName();
	}
	
	public ImprovementType getType()
	{
		return type;
	}
	
	public void runAway()
	{
		runningAway = true;
	}
	
	public void update() 
	{
		if (!runningAway)
		{
			super.update();
		}
		else 
		{
			x++;
			
			// if far off screen, remove
			if (x > Window.WIDTH + 100)
			{
				
			}
		}
	}
}
