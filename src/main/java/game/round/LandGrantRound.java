package game.round;

import game.Map;
import game.Plot;
import game.TurnOrderCalculator;

import java.util.ArrayList;

import ui.Button;
import core.render.RenderableString;
import core.render.SimpleRender;

/**
 * This class represents the logic and painting for the LandGrantRound
 * which occurs once at the very start of the game.
 * 
 * @author grant
 * @author
 */
public class LandGrantRound extends Round
{	
	public static final int PLOT_COST = 300;
	
	private SimpleRender playerOverview;
	private RenderableString prompt;
	
	private int metaRound;
	
	private Button passButton;
	
	private ArrayList<String> playerIds;	
	private int currentPlayerIndex;
	
	/**
	 * This instantiates some constant images and Renderable objects 
	 * present throughout round as well as prepare ArrayList of 
	 * Players for use in the round. 
	 * 
	 * @param session Contains all info about game like players, round, etc
	 */
	public LandGrantRound()
	{	
		metaRound = 1;
		
		playerOverview = new SimpleRender("assets/images/playerStatBackground.png");
		playerOverview.setX(0);
		playerOverview.setY(350);

		currentPlayerIndex = 0;
		playerIds = new ArrayList<String>();
	}
	
	public void init()
	{		
		// sort players
		session.sortPlayers(new TurnOrderCalculator());

		playerIds = new ArrayList<String>();
		// deep copy so we can remove them when we want
		for (String id : session.getPlayerIds())
		{			
			playerIds.add(id);
		}
		                
		prompt = new RenderableString();
		prompt.setX(250);
		prompt.setY(390);
		
		passButton = new Button("assets/images/passButton.png");
		passButton.setX(550);
		passButton.setY(360);
		passButton.setWidth(50);
		passButton.setHeight(30);
	}

	/**
	 * Alert the state that an mouse click occurred
	 * 
	 * @param x The x pos in pixels
	 * @param y The y pos in pixels
	 * @param leftMouse Whether the left mouse was pressed
	 */
	public void click(int x, int y, boolean leftMouse)
	{
		int xGridPos = (int)Math.floor(x / Plot.SIZE);
		int yGridPos = (int)Math.floor(y / Plot.SIZE);
		
		if (metaRound <= 2)
		{	
			if (validClick(x, y))
			{
				buyProperty(xGridPos, yGridPos, 0); // first two rounds offer plots for free!
			}
		} 
		else 
		{
			if (passButton.inBounds(x,y))
			{		
	               playerIds.remove(currentPlayerIndex);
	               currentPlayerIndex = currentPlayerIndex++;
	               // handle when index exceeds bounds of ArrayList
	               if (currentPlayerIndex >= playerIds.size() && playerIds.size() != 0)
	            	   currentPlayerIndex %= playerIds.size();
	               // If we arrive back at first person (index 0), we move on to next metaround
	               if (currentPlayerIndex == 0) 
	               {
	            	   metaRound++;
	               }
	        }
			else 
	        {
	        	if (validClick(x, y)) 
	        	{
	        		buyProperty(xGridPos, yGridPos, 300); // $300 for each plot after first 2 rounds
	        	}
	        }
		}
	}
	
	/**
	 * Since this same code occurred twice in click method in the conditionals,
	 * I decided to seperate it out. This handles all the necessary work whenever
	 * a player decides to buy a plot at any cost. For example, update index to
	 * next player, add plot to list of plots owned by player, and subtract cost from
	 * player's money amount.
	 * 
	 * @param xGridPos index of row in array of plots
	 * @param yGridPos index of column in array of plots
	 * @param cost cost of plot (in this round either $0 or $300)
	 */
	private void buyProperty(int xGridPos, int yGridPos, int cost) 
	{
        Plot plot = session.getPlot(xGridPos,yGridPos);
        
        if (plot.isOwned())
        {
        	return;
        }
                
        String id = playerIds.get(currentPlayerIndex);
        
        plot.setIsOwned(true);
        plot.setColor(session.getPlayerColor(id));
        session.addPlotToPlayer(id, plot);
        
        int currentMoney = session.getPlayerMoney(id);
        session.setPlayerMoney(id, currentMoney - cost);
        
        // if player does not have enough money to even purchase one more plot, then remove from ArrayList
        if (session.getPlayerMoney(id) < PLOT_COST) 
 		{
 			playerIds.remove(currentPlayerIndex);
 		}
        else
        {
     		currentPlayerIndex++;
        }
 		
        if (currentPlayerIndex >= playerIds.size() && playerIds.size() != 0) 
        {
     	   currentPlayerIndex %= playerIds.size();
        }
 		if (currentPlayerIndex == 0) 
 		{
 			metaRound++;
 		}
	}
	
	/**
	 * This simply takes in x and y coordinates and checks to see if 
	 * those coordinates are within the plot taken up by the "Town."
	 * 
	 * @param x Horizontal distance from left edge of window in pixels
	 * @param y Vertical distance from top of window in pixels
	 * @return Whether or not those coordinates are within the "Town"
	 */
	private boolean validClick(int x, int y)
	{
		if( y > Plot.SIZE * 5)
		{
			return false;
		}
		else 
		{
			if (y > Plot.SIZE * 2 && y < Plot.SIZE * 3)
			{
				if(x > Plot.SIZE * 4 && x < Plot.SIZE * 5)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Update called every EventLoop cycle. Continually repaints 
	 * things on JPanel in order to keep screen updated and current.
	 */
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();
		
		// plots
		for (int a = 0; a < Map.HEIGHT; a++)
		{
			for (int b = 0; b < Map.WIDTH; b++)
			{
				Plot plot = session.getPlot(b, a);
				renderables.add(plot);
			}
		}

		renderables.add(playerOverview);
				
		// Players should not be able to pass if plots are free!
		if (metaRound > 2) 
		{
			renderables.add(passButton);
		}
		
		String id = playerIds.get(currentPlayerIndex);
		
		prompt.setText(session.getPlayerName(id) + " please select a plot");
		renderableStrings.add(prompt);
		
		RenderableString name = new RenderableString(session.getPlayerName(id), 15, 364);
		renderableStrings.add(name);
		
		RenderableString money = new RenderableString("$" + session.getPlayerMoney(id), 30, 380);
		renderableStrings.add(money);

		RenderableString ore = new RenderableString("" + session.getPlayerOre(id), 30, 395);
		renderableStrings.add(ore);

		RenderableString food = new RenderableString("" + session.getPlayerFood(id), 30, 410);
		renderableStrings.add(food);

		RenderableString energy = new RenderableString("" + session.getPlayerEnergy(id), 90, 395);
		renderableStrings.add(energy);

		RenderableString crystite = new RenderableString("" + session.getPlayerCrystite(id), 90, 410);
		renderableStrings.add(crystite);
	}

	/**
	 * Whenever a player passes or no longer has enough money for a new 
	 * plot, they are removed from ArrayList. When no players left, the 
	 * round is over.
	 */
	public boolean isDone() 
	{
		if (0 == playerIds.size())
		{
			return true;
		}
		
		return false;
	}
}
