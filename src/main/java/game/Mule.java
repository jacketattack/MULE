package game;

import ui.Window;

public class Mule extends Follower
{
	private ImprovementType type;
	private boolean runningAway;
	
	public Mule(Character character)
	{
		super(character);
		setType(ImprovementType.EMPTY);
		runningAway = false;
	}
	
	public void setType(ImprovementType type)
	{
		this.type = type;
		imagePath = "assets/images/mule" + type + ".png";
	}
	
	public ImprovementType getType()
	{
		return type;
	}
	
	public void runAway()
	{
		runningAway = true;
	}
	
	public void update() {
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
