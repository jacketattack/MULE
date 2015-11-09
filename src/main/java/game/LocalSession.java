package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import ui.render.Render;
import core.IdGenerator;

/**
 * The LocalSession stores all game data in memory.
 * All game data goes in and out of the LocalSession.
 * It acts as a Facade for the rest of the application.
 */
public class LocalSession implements Session
{
    /** The game map */
    private Map map;

    /** The index of the current player */
    private int currentPlayerIndex;
    /** Cache the last player accessed */
    private Player lastPlayerAccessed;
    /** List of players */
    private ArrayList<Player> players;

    /** Timer for general use */
    private int timer;
    /** Current round num */
    private int roundNum;

    /** The unique id of the session */
    private String id;

    /**
     * Create a new Session and store it in the database
     */
    public LocalSession()
    {
        players = new ArrayList<>();
        roundNum = 0;
    }

    private LocalSession(LocalSession session)
    {
        this.id = session.id;
        this.timer = session.timer;
        this.roundNum = session.roundNum;
        this.currentPlayerIndex = session.currentPlayerIndex;
        this.lastPlayerAccessed = null;

        if (session.map != null)
                this.map = new Map(session.map);

        ArrayList<Player> copiedPlayers = new ArrayList<>();
        for (Player player : session.players)
        {
                Player copiedPlayer = new Player(player);
                copiedPlayers.add(copiedPlayer);
        }
        this.players = copiedPlayers;
    }

    /**
     * Create a number of players and return a list of their ids
     * @param n The number of players to create
     * @return The list of player ids
     */
    public ArrayList<String> createPlayers(int n)
    {
        players = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();

        PlayerFactory playerFactory = new PlayerFactory();

        for (int a = 0; a < n; a++)
        {
            Player player = playerFactory.createPlayer();
            ids.add(player.getId());
            players.add(player);
        }

        lastPlayerAccessed = players.get(0);
        return ids;
    }

    /**
     * Get a list of the current player ids
     * @return The list of player ids
     */
    public ArrayList<String> getPlayerIds()
    {
        ArrayList<String> ids = new ArrayList<>();
        for (Player player : players)
        {
            ids.add(player.getId());
        }

        return ids;
    }

    /**
     * Get the id of the current player
     *
     */
    public String getCurrentPlayerId()
    {
        if (currentPlayerIndex < 0 || currentPlayerIndex >= players.size())
            return null;

        return players.get(currentPlayerIndex).getId();
    }

    /**
     * Set the current player by passing in a id
     * @param id The player id
     */
    public void setCurrentPlayer(String id)
    {
        for (int a = 0; a < players.size(); a++)
        {
            Player player = players.get(a);
            if (player.getId().equals(id))
            {
                currentPlayerIndex = a;
            }
        }
    }

    /**
     * Advance the current player to the next player in line
     * @return Whether we reached the end of the player list
     */
    public boolean advancePlayer()
    {
        boolean advancedRound = false;

        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size())
        {
            advancedRound = true;
            currentPlayerIndex = 0;
        }

        return advancedRound;
    }

    /**
     * Update a player based on their id
     * @param id The player id of the player we want to update
     */
    public void updatePlayer(String id)
    {
        Player player = getPlayer(id);
        player.update();
    }

    /**
     * Sell a resource as a player
     * @param id The id of the player
     * @param resource The resource to sell
     * @param quantity The number of resources to sell
     * @param price The price to sell
     */
    public void playerSellResource(String id, String resource, int quantity, int price)
    {
        Player player = getPlayer(id);
        player.sellResource(resource, quantity, price);
    }

    /**
     * Buy a resource as a player
     * @param id The id of the player
     * @param resource The resource to buy
     * @param quantity The number of resources to buy
     * @param price The price to buy
     */
    public void playerBuyResource(String id, String resource, int quantity, int price)
    {
        Player player = getPlayer(id);
        player.buyResource(resource, quantity, price);
    }

    /**
     * Remove the follower of a player
     * @param id The player id
     */
    public void removePlayerFollower(String id)
    {
        Player player = getPlayer(id);
        player.setFollower(null);
    }

    /**
     * Set the follower of a player
     * @param id The player id
     * @param follower The follower
     */
    public void setPlayerFollower(String id, Follower follower)
    {
        Player player = getPlayer(id);
        if (follower != null)
        {
            follower.setSession(this);
            follower.setPlayerId(id);
            follower.reset();
        }
        player.setFollower(follower);
    }

    /**
     * Get the follower of a player
     * @param id The player id
     * @return The follower of that player. If the player
     * doesn't have a follower, null if returned
     */
    public Follower getPlayerFollower(String id)
    {
        Player player = getPlayer(id);
        return player.getFollower();
    }

    /**
     * Get a player's render
     * @param id The player id
     * @return The render of the player
     */
    public Render getPlayerRender(String id)
    {
        Player player = getPlayer(id);
        return player.getRender();
    }

    /**
     * Get a player's follower's render
     * @param id The player id
     * @return The render of the follower. If the player
     * doesn't have a follower, null if returned
     */
    public Render getPlayerFollowerRender(String id)
    {
        Player player = getPlayer(id);
        Follower follower = player.getFollower();
        return follower == null ? null : follower.getRender();
    }

    /**
     * Apply a force to a player (moving the player).
     * @param id The player id
     * @param fx The x force
     * @param fy The y force
     */
    public void applyForceToPlayer(String id, int fx, int fy)
    {
        Player player = getPlayer(id);
        player.applyForce(fx, fy);
    }

    /**
     * Sort the list of players using a comparator
     * @param comp The comparator
     */
    public void sortPlayers(Comparator<Player> comp)
    {
        Collections.sort(players, comp);
    }

    /**
     * Whether a plot is owned by a specific player
     * @param id The player id
     * @param plotId The id of the plot
     * @return Whether the player owns the plot
     */
    public boolean isPlotOwnedByPlayer(String id, String plotId)
    {
        Player player = getPlayer(id);
        for (String playerPlotId : player.getPlotIds())
        {
            if (playerPlotId.equals(plotId))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the ids of all player owned plots
     * @param id The player id
     * @return The list of plot ids the player owns
     */
    public ArrayList<String> getPlayerOwnedPlotIds(String id)
    {
        Player player = getPlayer(id);
        return player.getPlotIds();
    }

    /**
     * Give a plot to a player
     * @param id The player id
     * @param plotId The plot id
     */
    public void addPlotToPlayer(String id, String plotId)
    {
        Player player = getPlayer(id);
        player.addPlot(plotId);
    }

    /**
     * Get the plot at a specific x/y coordinate
     * @param x The x position
     * @param y The y position
     * @return The plot
     */
    public Plot getPlot(int x, int y)
    {
        return map.get(x, y);
    }

    /**
     * Get a plot by its id
     * @param id The plot id
     * @return The plot
     */
    public Plot getPlot(String id)
    {
        return map.get(id);
    }

    /**
     * Get the money of a particular player
     * @param id The player id
     * @return The amount of money the player has
     */
    public int getPlayerMoney(String id)
    {
        Player player = getPlayer(id);
        return player.getMoney();
    }

    /**
     * Set the money of a particular player
     * @param id The player id
     * @param amount The money id
     */
    public void setPlayerMoney(String id, int amount)
    {
        Player player = getPlayer(id);
        player.setMoney(amount);
    }

    /**
     * Increment a player's ore by a certain amount
     * @param id The player id
     * @param amount The amount to give the player
     */
    public void incrementOre(String id, int amount) {
        Player player = getPlayer(id);
        player.incrementOre(amount);
    }

    /**
     * Increment a player's food by a certain amount
     * @param id The player id
     * @param amount The amount to give the player
     */
    public void incrementFood(String id, int amount) {
        Player player = getPlayer(id);
        player.incrementFood(amount);
    }

    /**
     * Increment a player's energy by a certain amount
     * @param id The player id
     * @param amount The amount to give the player
     */
    public void incrementEnergy(String id, int amount) {
        Player player = getPlayer(id);
        player.incrementEnergy(amount);
    }

    /**
     * Increment a player's crystite by a certain amount
     * @param id The player id
     * @param amount The amount to give the player
     */
    public void incrementCrystite(String id, int amount) {
        Player player = getPlayer(id);
        player.incrementCrystite(amount);
    }

    /**
     * Increment a player's money by a certain amount
     * @param id The player id
     * @param amount The amount to give the player
     */
    public void incrementMoney (String id, int amount){
        Player player = getPlayer(id);
        player.incrementMoney(amount);
    }

    /**
     * Get a player's Color
     * @param id The player id
     * @return The player's Color
     */
    public Color getPlayerColor(String id)
    {
        Player player = getPlayer(id);
        return player.getColor();
    }

    /**
     * Set a player's color
     * @param id The player id
     * @param color The player's color
     */
    public void setPlayerColor(String id, Color color)
    {
        Player player = getPlayer(id);
        player.setColor(color);
    }

    /**
     * Get a player's name
     * @param id The player id
     * @return The player's name
     */
    public String getPlayerName(String id)
    {
        Player player = getPlayer(id);
        return player.getName();
    }

    /**
     * Set a player's name
     * @param id The player id
     * @param name the player's name
     */
    public void setPlayerName(String id, String name)
    {
        Player player = getPlayer(id);
        player.setName(name);
    }

    /**
     * Get a player's type
     * @param id The player id
     * @return The player's type
     */
    public PlayerType getPlayerType(String id)
    {
        Player player = getPlayer(id);
        return player.getType();
    }

    /**
     * Set a player's type
     * @param id The player id
     * @param type The player's type
     */
    public void setPlayerType(String id, PlayerType type)
    {
        Player player = getPlayer(id);
        player.setType(type);
    }

    /**
     * Get a player's ore
     * @param id The player id
     * @return The player's ore
     */
    public int getPlayerOre(String id)
    {
        Player player = getPlayer(id);
        return player.getOre();
    }

    /**
     * Get a player's food
     * @param id The player id
     * @return The player's food
     */
    public int getPlayerFood(String id)
    {
        Player player = getPlayer(id);
        return player.getFood();
    }

    /**
     * Get a player's energy
     * @param id The player id
     * @return The player's energy
     */
    public int getPlayerEnergy(String id)
    {
        Player player = getPlayer(id);
        return player.getEnergy();
    }

    /**
     * Get a player's crystite
     * @param id The player id
     * @return The player's crystite
     */
    public int getPlayerCrystite(String id)
    {
        Player player = getPlayer(id);
        return player.getCrystite();
    }

    /**
     * Get a player's x position
     * @param id The player id
     * @return The player's x position
     */
    public int getPlayerX(String id)
    {
        Player player = getPlayer(id);
        return player.getX();
    }

    /**
     * Set a player's x position
     * @param id The player id
     * @param x position
     */
    public void setPlayerX(String id, int x)
    {
        Player player = getPlayer(id);
        player.setX(x);
    }

    /**
     * Get a player's y position
     * @param id The player id
     * @return The player's y position
     */
    public int getPlayerY(String id)
    {
        Player player = getPlayer(id);
        return player.getY();
    }

    /**
     * Set a player's color
     * @param id The player id
     * @param y The y position
     */
    public void setPlayerY(String id, int y)
    {
        Player player = getPlayer(id);
        player.setY(y);
    }

    /**
     * Internal method for retrieving a player based on their id
     * @param id The player id
     * @return The player with the matching id
     */
    private Player getPlayer(String id)
    {
        if (lastPlayerAccessed != null && lastPlayerAccessed.getId().equals(id))
        {
            return lastPlayerAccessed;
        }

        Player matchingPlayer = null;

        for (int a = 0; a < players.size(); a++)
        {
            Player player = players.get(a);
            if (player.getId().equals(id))
            {
                matchingPlayer = player;
            }
        }

        lastPlayerAccessed = matchingPlayer;

        return matchingPlayer;
    }

    /**
     * Get the current round at
     * @return The current round
     */
    public int getCurrentRound()
    {
        return roundNum;
    }

    /**
     * Increment the current round
     */
    public void incrementRound()
    {
        roundNum++;
    }

    /**
     * Set the game's map
     * @param map The map
     */
    public void setMap(Map map)
    {
        this.map = map;
    }

    /**
     * Set the timer. The timer is a general purpose timer
     * @param n The time
     */
    public void setTimer(int n)
    {
        timer = n;
    }

    /**
     * Get the timer's value
     * @return the timer's value
     */
    public int getTimer()
    {
        return timer;
    }

    /**
     * Increment the timer's value
     */
    public void incrementTimer()
    {
        timer++;
    }

    /**
     * Decrement the timer's value
     */
    public void decrementTimer()
    {
        timer--;
    }

    /**
     * Return a copy of the session
     * @return The copied session
     */
    private LocalSession copy()
    {
           return new LocalSession(this);
    }

    /**
     * Get the session's id
     * @return The session's id
     */
    public String getId()
    {
        return id;
    }

    @Override
    public double getPlayerScore(String id)
    {
        Player player = getPlayer(id);
        return player.getScore();
    }
}
