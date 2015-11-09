package game.round;

import game.ImprovementType;
import game.Plot;
import game.PlotType;

import java.util.ArrayList;

import ui.Button;
import ui.render.Render;
import ui.render.StringRender;


/**
 * This simple round calculates the production yields of each plot that
 * a player has placed a MULE. It occurs at the start of every round
 * except for the first round.
 */
public class ProductionRound extends Round 
{
    private ArrayList<String> playerIds;
    private boolean done;
    
    private Render title;
    private Render resourcesTitle1;
    private Render resourcesTitle2;
    private Render resourcesTitle3;
    private Render resourcesTitle4;
    private Button doneButton;
    
    private boolean totalDone; // checks to see if total needs to be computed
    private int[] playerResources;

    /**
     * Default constructor for ProductionRound. Initializes the graphics at their proper locations.
     */
    public ProductionRound() 
    {
        playerIds = new ArrayList<>();
        
        title = new Render();
        title.x = 0;
        title.y = 0;
        title.addImage("assets/images/production/titleBar.png");
        renders.add(title);
        
        doneButton = new Button("assets/images/auction/Done.png");
        doneButton.setX(500);
        doneButton.setY(375);
        doneButton.setWidth(50);
        doneButton.setHeight(30);
        renders.add(doneButton.getRender());
        
        resourcesTitle1 = new Render();
        resourcesTitle1.x = 145;
        resourcesTitle1.y = 140;
        resourcesTitle1.addImage("assets/images/production/ItemStrings.gif");
        renders.add(resourcesTitle1);
        
        resourcesTitle2 = new Render();
        resourcesTitle2.x = 260;
        resourcesTitle2.y = 140;
        resourcesTitle2.addImage("assets/images/production/ItemStrings.gif");
        renders.add(resourcesTitle2);
        
        resourcesTitle3 = new Render();
        resourcesTitle3.x = 385;
        resourcesTitle3.y = 140;
        resourcesTitle3.addImage("assets/images/production/ItemStrings.gif");
        renders.add(resourcesTitle3);
        
        resourcesTitle4 = new Render();
        resourcesTitle4.x = 510;
        resourcesTitle4.y = 140;
        resourcesTitle4.addImage("assets/images/production/ItemStrings.gif");
        renders.add(resourcesTitle4);

        Render dividerLine = new Render();
        dividerLine.x = 0;
        dividerLine.y = 275;
        dividerLine.addImage("assets/images/production/line.gif");
        done = false;
    }


    /**
     * Initializes necessary instance variable for the ProductionRound
     */
    @Override
    public void init() 
    {   
        playerIds.clear(); // to avoid over-counting if init() called too many times
        for (String id: session.getPlayerIds()) 
        {
            playerIds.add(id);
        }
        playerResources = new int[playerIds.size() * 4]; // this keeps track of each player's resources gained
        
        calculateEnergy();
        calculateProduction();
        
        if (!totalDone)
        {
            for (int i = 0; i < playerIds.size(); i++)
            {
                totalDone = true;
                // now we will update inventories
                int foodQuantity = playerResources[0 + (i * 4)];
                int oreQuantity = playerResources[1 + (i * 4)];
                int crystiteQuantity = playerResources[2 + (i * 4)];
                int energyQuantity = playerResources[3 + (i * 4)];
                session.playerBuyResource(playerIds.get(i), "food", foodQuantity, 0);
                session.playerBuyResource(playerIds.get(i), "ore", oreQuantity, 0);
                session.playerBuyResource(playerIds.get(i), "crystite", crystiteQuantity, 0);
                session.playerBuyResource(playerIds.get(i), "energy", energyQuantity, 0);
            }
        }
    }
    
    /**
     * This method will go through each player's owned plots 
     * and, for each one with a MULE, will increment the inventory 
     * of that player for the resources yielded, given the player
     * has enough energy to do so.
     */
    private void calculateProduction()
    {
        for (int i = 0; i < playerIds.size(); i++) 
        {
            ArrayList<String> ownedPlots = session.getPlayerOwnedPlotIds(playerIds.get(i));
            int energyToSpend = session.getPlayerEnergy(playerIds.get(i)) + playerResources[3 + (4 * i)];
            
            int counter = 0;

            while (counter < ownedPlots.size() && energyToSpend > 0) 
            {
                String currentPlotId = ownedPlots.get(counter);
                Plot currentPlot = session.getPlot(currentPlotId);
                PlotType currentPlotType = currentPlot.getPlotType();
                int quantity;
                
                if (currentPlot.hasMule()) 
                {   
                    // we don't include energy because already accounted for in calculateEnergy()
                    if (currentPlot.getMule().getType() == ImprovementType.CRYSTITE) 
                    {

                        quantity = currentPlot.getCrystite();
                        playerResources[2 + (4 * i)] += quantity;
                        energyToSpend--;
                    }
                    else if (currentPlot.getMule().getType() == ImprovementType.FOOD) 
                    {
                        quantity = currentPlotType.getFood();
                        playerResources[0 + (4 * i)] += quantity;
                        energyToSpend--;
                        
                    }
                    else if (currentPlot.getMule().getType() == ImprovementType.ORE) 
                    {
                        quantity = currentPlotType.getOre();
                        playerResources[1 + (4 * i)] += quantity;
                        energyToSpend--;
                    }
                    
                }
                counter++;
            }
            
            // playerResources has resources gained this round..so subtract energy from that amount that was spent
            // players can have negative energy gained - they spent more than they produced
            int energySpent = (session.getPlayerEnergy(playerIds.get(i)) + playerResources[3 + (4 * i)]) - energyToSpend;
            playerResources[3 + (i * 4)] -= energySpent;
        }
    }
    
    /**
     * Energy is special since it powers all MULES and allows for 
     * all other resources to be harvested. MULEs harvesting energy
     * will always first yield energy. One of the energy yielded will
     * be immediately consumed to power that energy MULE. Then, excess
     * energy will be added to a player's inventory for powering other
     * MULES.
     */
    private void calculateEnergy()
    {
        for (int i = 0; i < playerIds.size(); i++) 
        {
            ArrayList<String> ownedPlots = session.getPlayerOwnedPlotIds(playerIds.get(i));
            
            for (int j = 0; j < ownedPlots.size(); j++) 
            {
                String currentPlotId = ownedPlots.get(j);
                Plot currentPlot = session.getPlot(currentPlotId);
                
                // only looking for energy plots
                if (currentPlot.hasMule() && currentPlot.getMule().getType() == ImprovementType.ENERGY) 
                {
                    int energyGained = currentPlot.getPlotType().getEnergy() - 1; // -1 because this plot needs 1 energy    
                    playerResources[3 + (4 * i)] += energyGained;
                }
            }
        }
    }

    /**
     * Called each time the tread ticks. Ensures data on screen is representative of game variables.
     */
    @Override
    public void update() 
    {   
        renders.clear();
        stringRenders.clear();
        
        renders.add(title);
        renders.add(doneButton.getRender());
        renders.add(resourcesTitle1);
        renders.add(resourcesTitle2);
        renders.add(resourcesTitle3);
        renders.add(resourcesTitle4);
        
        stringRenders.add(new StringRender("Previous Amount", 1, 200));
        stringRenders.add(new StringRender("Produced", 1, 250));
        stringRenders.add(new StringRender("Total:", 1, 300));
        
        for (int i = 0; i < playerIds.size(); i++) 
        {       
            String playerName = session.getPlayerName(playerIds.get(i));
            stringRenders.add(new StringRender("" + playerName, 170 + (i * 115), 130));
            
            stringRenders.add(new StringRender("" + session.getPlayerOre(playerIds.get(i)), 150 + (i * 120), 200));
            stringRenders.add(new StringRender("" + session.getPlayerCrystite(playerIds.get(i)), 175 + (i * 120), 200));
            stringRenders.add(new StringRender("" + session.getPlayerEnergy(playerIds.get(i)), 205 + (i * 120), 200));
            stringRenders.add(new StringRender("" + session.getPlayerFood(playerIds.get(i)), 225 + (i * 120), 200));
            
            stringRenders.add(new StringRender("" + playerResources[1 + (i * 4)], 150 + (i * 120), 250)); // ore 
            stringRenders.add(new StringRender("" + playerResources[2 + (i * 4)], 175 + (i * 120), 250)); // crystite
            stringRenders.add(new StringRender("" + playerResources[3 + (i * 4)], 205 + (i * 120), 250)); // energy
            stringRenders.add(new StringRender("" + playerResources[0 + (i * 4)], 225 + (i * 120), 250)); // food   
            
            stringRenders.add(new StringRender("" + session.getPlayerOre(playerIds.get(i)), 150 + (i * 120), 300));
            stringRenders.add(new StringRender("" + session.getPlayerCrystite(playerIds.get(i)), 175 + (i * 120), 300));
            stringRenders.add(new StringRender("" + session.getPlayerEnergy(playerIds.get(i)), 205 + (i * 120), 300));
            stringRenders.add(new StringRender("" + session.getPlayerFood(playerIds.get(i)), 225 + (i * 120), 300));
        }
    }

    /**
     * Used to check if player has clicked the only button on the screen:
     * the done button
     * @param x The x pos in pixels
     * @param y The y pos in pixels
     * @param leftMouse Whether the left mouse was pressed
     */
    @Override
    public void click(int x, int y, boolean leftMouse) 
    {   
        if (doneButton.inBounds(x, y)) 
        {
            done = true;
        }
    }

    @Override
    public boolean isDone() 
    {
        return done;
    }
}
