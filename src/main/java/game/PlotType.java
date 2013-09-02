package game;

/**
 * 
 * @author grant
 * @author
 */
public enum PlotType 
{
	RIVER,
	PLAIN,
	MOUNTAIN_1, 
	MOUNTAIN_2, 
	MOUNTAIN_3;
	
	public static int getDefaultFoodProduction(PlotType type)
	{
		int amount = -1;
		
		switch (type)
		{
			case RIVER:
				amount = 4;
				break;
			case PLAIN: 
				amount = 2;
				break;
			case MOUNTAIN_1:
				amount = 1;
				break;
			case MOUNTAIN_2:
				amount = 1;
				break;
			case MOUNTAIN_3:
				amount = 1;
				break;
		}
		
		return amount;
	}

	public static int getDefaultEnergyProduction(PlotType type)
	{
		int amount = -1;
		
		switch (type)
		{
			case RIVER:
				amount = 2;
				break;
			case PLAIN: 
				amount = 3;
				break;
			case MOUNTAIN_1:
				amount = 1;
				break;
			case MOUNTAIN_2:
				amount = 1;
				break;
			case MOUNTAIN_3:
				amount = 1;
				break;
		}
		
		return amount;
	}
	
	public static int getDefaultOreProduction(PlotType type)
	{
		int amount = -1;
		
		switch (type)
		{
			case RIVER:
				amount = 0;
				break;
			case PLAIN: 
				amount = 1;
				break;
			case MOUNTAIN_1:
				amount = 2;
				break;
			case MOUNTAIN_2:
				amount = 3;
				break;
			case MOUNTAIN_3:
				amount = 4;
				break;
		}
		
		return amount;
	}

	public static int getDefaultCrystiteProduction(PlotType type)
	{
		return -1;
	}
}
