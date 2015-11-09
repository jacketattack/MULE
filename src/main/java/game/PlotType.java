package game;

/**
 * PlotType is an enum that stores the different possible plot types
 */
public enum PlotType 
{   
    RIVER (4, 0, 2, "assets/images/plot/river.png"),
    PLAIN (2, 1, 3, "assets/images/plot/plain.png"),
    MOUNTAIN_1 (1, 2, 1, "assets/images/plot/mountain_1.png"), 
    MOUNTAIN_2 (1, 3, 1, "assets/images/plot/mountain_2.png"), 
    MOUNTAIN_3 (1, 4, 1, "assets/images/plot/mountain_3.png"),
    TOWN (0, 0, 0, "assets/images/plot/town.png");

    private int food;
    private int ore;
    private int energy;
    
    private String imagePath;
    
    /**
     * Define a plot type with a certain amount of food, ore, energy and an image
     * @param food Amount of available food
     * @param ore Amount of available ore
     * @param energy Amount of available energy
     * @param imagePath The image
     */
    private PlotType (int food, int ore, int energy, String imagePath)
    {
        this.food = food;
        this.ore = ore;
        this.energy = energy;
        this.imagePath = imagePath;
    }
    
    /**
     * Get the food production of this tile
     * @return The food production
     */
    public int getFood()
    {
        return food;
    }

    /**
     * Get the ore production of this tile
     * @return The ore production
     */
    public int getOre()
    {
        return ore;
    }

    /**
     * Get the energy production of this tile
     * @return The energy production
     */
    public int getEnergy()
    {
        return energy;
    }

    /**
     * Get the image path
     * @return The image path
     */
    public String getImagePath()
    {
        return imagePath;
    }
}
