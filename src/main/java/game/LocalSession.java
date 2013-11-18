package game;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ui.render.Render;

import com.mongodb.BasicDBList;

import core.IdGenerator;
import core.db.DB;
import core.db.DatabaseObject;

public class LocalSession implements Session, Serializable
{	
	private static final long serialVersionUID = -6916970846993796368L;

	private Map map;
	
	private int currentPlayerIndex;
	private Player lastPlayerAccessed;
	private ArrayList<Player> players;

	private int timer;
	private int roundNum;
	
	private LocalSession saveCopy;

    private String id;
	
	public LocalSession()
	{
		players = new ArrayList<>();
		roundNum = 0;

        DB db = DB.getInstance();
        
        id = IdGenerator.getId();
        while (db.exists("save", id))
        {
            id = IdGenerator.getId();
        }
        
        save();
	}

    //check this for code review tonight
	public LocalSession(DatabaseObject data)
	{	
		this.id = (String)data.get("id");
		this.timer = (Integer)data.get("timer");
		this.roundNum = (Integer)data.get("roundNum");
		this.currentPlayerIndex = (Integer)data.get("currentPlayerIndex");
		
		BasicDBList playerIds = (BasicDBList)data.get("playerIds");
		
		players = new ArrayList<>();
		for (Object idObject : playerIds)
		{
			String id = (String)idObject;
			String accessor = "player_" + id + "_";
			Player player = new Player(false);
			player.setId(id);
			player.setName((String)data.get(accessor + "name"));
		
			// set player color
			int red = (Integer)data.get(accessor + "colorRed");
			int green = (Integer)data.get(accessor + "colorGreen");
			int blue = (Integer)data.get(accessor + "colorBlue");
			Color color;
			if (red == 255 && green == 255 && blue == 0)
			{
				color = Color.BLACK;
			}
			else if (red == 255)
			{
				color = Color.RED;
			}
			else if (green == 255)
			{
				color = Color.GREEN;
			}
			else
			{
				color = Color.BLUE;
			}
			player.setColor(color);
			
			String type = (String)data.get(accessor + "type");
			if (type.equals("HUMAN"))
			{
				player.setType(PlayerType.HUMAN);
			} 
			else if (type.equals("FLAPPER"))
			{
				player.setType(PlayerType.FLAPPER);
			} 
			else if (type.equals("BONZOID"))
			{
				player.setType(PlayerType.BONZOID);
			} 
			else if (type.equals("UGAITE"))
			{
				player.setType(PlayerType.UGAITE);
			} 
			else if (type.equals("BUZZITE"))
			{
				player.setType(PlayerType.BUZZITE);
			}
			
			player.setX((Integer)data.get(accessor + "x"));
			player.setY((Integer)data.get(accessor + "y"));
			
			player.incrementMoney((Integer)data.get(accessor + "money"));
			player.incrementOre((Integer)data.get(accessor + "ore"));
			player.incrementFood((Integer)data.get(accessor + "food"));
			player.incrementEnergy((Integer)data.get(accessor + "energy"));
			player.incrementCrystite((Integer)data.get(accessor + "crystite"));
			
			BasicDBList plots = (BasicDBList) data.get(accessor + "plots");
			for (Object plotId : plots)
			{
				player.addPlot((String)plotId);

			}
			
			players.add(player);
		}
		
		map = new Map(false);
		
		for (int a = 0; a < Map.HEIGHT; a++)
		{
			for (int b = 0; b < Map.WIDTH; b++)
			{
				String accessor = "plot_" + a + "x" + b;
				String typeString = (String)data.get(accessor);				
				PlotType type;
				if (typeString == null)
				{
					type = PlotType.PLAIN;
				}
				else if (typeString.equals("PLAIN"))
				{
					type = PlotType.PLAIN;
				} 
				else if (typeString.equals("RIVER"))
				{
					type = PlotType.RIVER;
				} 
				else if (typeString.equals("MOUNTAIN_1"))
				{
					type = PlotType.MOUNTAIN_1;
				} 
				else if (typeString.equals("MOUNTAIN_2"))
				{
					type = PlotType.MOUNTAIN_2;
				} 
				else if (typeString.equals("MOUNTAIN_3"))
				{
					type = PlotType.MOUNTAIN_3;
				}
				else
				{
					type = PlotType.TOWN;
				}

				Plot plot = new Plot(type, a, b);	
				
				ImprovementType improvementType;
				String improvementTypeString = (String)data.get(accessor + "_improvement");
				if (improvementTypeString == null)
				{
					improvementType = ImprovementType.EMPTY;
				} 
				else if (improvementTypeString.equals("EMPTY"))
				{
					improvementType = ImprovementType.EMPTY;
				}
				else if (improvementTypeString.equals("FOOD"))
				{
					improvementType = ImprovementType.FOOD;
				}
				else if (improvementTypeString.equals("ENERGY"))
				{
					improvementType = ImprovementType.ENERGY;
				}
				else if (improvementTypeString.equals("ORE"))
				{
					improvementType = ImprovementType.ORE;
				}
				else if (improvementTypeString.equals("CRYSTITE"))
				{
					improvementType = ImprovementType.CRYSTITE;
				}
				else
				{
					improvementType = ImprovementType.EMPTY;
				}
				plot.setImprovementType(improvementType);

				// set plot color
				if (data.get(accessor + "_colorRed") != null)
				{
					int red = (Integer)data.get(accessor + "_colorRed");
					int green = (Integer)data.get(accessor + "_colorGreen");
					int blue = (Integer)data.get(accessor + "_colorBlue");
					Color color;
					if (red == 255 && green == 255 && blue == 0)
					{
						color = Color.BLACK;
					}
					else if (red == 255)
					{
						color = Color.RED;
					}
					else if (green == 255)
					{
						color = Color.GREEN;
					}
					else
					{
						color = Color.BLUE;
					}
					plot.setColor(color);
				}
				
				map.set(b, a, plot);
			}
		}
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
    
	
	public ArrayList<String> createPlayers(int n)
	{
		players = new ArrayList<>();
		ArrayList<String> ids = new ArrayList<>();
		
		for (int a = 0; a < n; a++)
		{
			String id = "" + (3 * a);
			ids.add(id);

			Player player = new Player();
			player.setId(id);
			players.add(player);			
		}
		
		lastPlayerAccessed = players.get(0);
		return ids;
	}
	
	public ArrayList<String> getPlayerIds()
	{
		ArrayList<String> ids = new ArrayList<>();
		for (Player player : players)
		{
			ids.add(player.getId());
		}
		
		return ids;
	}

	public String getCurrentPlayerId()
	{
		if (currentPlayerIndex < 0 || currentPlayerIndex >= players.size())
			return null;
		
		return players.get(currentPlayerIndex).getId();
	}
	
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
	
	public void updatePlayer(String id)
	{
		Player player = getPlayer(id);
		player.update();
	}
	
	public void playerSellResource(String id, String resource, int quantity, int price)
	{
		Player player = getPlayer(id);
		player.sellResource(resource, quantity, price);
	}
	
	public void playerBuyResource(String id, String resource, int quantity, int price)
	{
		Player player = getPlayer(id);
		player.buyResource(resource, quantity, price);
	}
	
	public void removePlayerFollower(String id)
	{
		Player player = getPlayer(id);
		player.setFollower(null);
	}
	
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

	@Deprecated
	public Follower getPlayerFollower(String id)
	{
		Player player = getPlayer(id);
		return player.getFollower();
	}

	public Render getPlayerRender(String id)
	{
		Player player = getPlayer(id);
		return player.getRender();
	}

	public Render getPlayerFollowerRender(String id)
	{
		Player player = getPlayer(id);
		Follower follower = player.getFollower();
		return follower == null ? null : follower.getRender();
	}
	
	public void applyForceToPlayer(String id, int fx, int fy)
	{
		Player player = getPlayer(id);
		player.applyForce(fx, fy);
	}
	
	public void sortPlayers(Comparator<Player> comp)
	{
		Collections.sort(players, comp);
	}
	
	
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

	public ArrayList<String> getPlayerOwnedPlotIds(String id)
	{
		Player player = getPlayer(id);
		return player.getPlotIds();
	}

	public void addPlotToPlayer(String id, String plotId)
	{
		Player player = getPlayer(id);
		player.addPlot(plotId);
	}


	public Plot getPlot(int x, int y) 
	{
		return map.get(x, y);
	}
	
	public Plot getPlot(String id)
	{
		return map.get(id);
	}
	
	
	
	public int getPlayerMoney(String id)
	{
		Player player = getPlayer(id);
		return player.getMoney();
	}
	
	public void setPlayerMoney(String id, int amount)
	{
		Player player = getPlayer(id);
		player.setMoney(amount);
	}

    @Override
    public void incrementOre(String id, int amount) {
        Player player = getPlayer(id);
        player.incrementOre(amount);
    }

    @Override
    public void incrementFood(String id, int amount) {
        Player player = getPlayer(id);
        player.incrementFood(amount);
    }

    @Override
    public void incrementEnergy(String id, int amount) {
        Player player = getPlayer(id);
        player.incrementEnergy(amount);
    }

    @Override
    public void incrementCrystite(String id, int amount) {
        Player player = getPlayer(id);
        player.incrementCrystite(amount);
    }

    public void incrementMoney (String id, int amount){
        Player player = getPlayer(id);
        player.incrementMoney(amount);
    }

    public Color getPlayerColor(String id)
	{
		Player player = getPlayer(id);
		return player.getColor();
	}
		
	public void setPlayerColor(String id, Color color)
	{	
		Player player = getPlayer(id);
		player.setColor(color);
	}
	
	public String getPlayerName(String id)
	{
		Player player = getPlayer(id);
		return player.getName();
	}
	
	public void setPlayerName(String id, String name)
	{
		Player player = getPlayer(id);
		player.setName(name);
	}
	
	public PlayerType getPlayerType(String id)
	{
		Player player = getPlayer(id);
		return player.getType();
	}
	
	public void setPlayerType(String id, PlayerType type)
	{
		Player player = getPlayer(id);
		player.setType(type);
	}

	public int getPlayerOre(String id)
	{
		Player player = getPlayer(id);
		return player.getOre();
	}
	
	public int getPlayerFood(String id)
	{
		Player player = getPlayer(id);
		return player.getFood();
	}
	
	public int getPlayerEnergy(String id)
	{
		Player player = getPlayer(id);
		return player.getEnergy();
	}
	
	public int getPlayerCrystite(String id)
	{
		Player player = getPlayer(id);
		return player.getCrystite();
	}

	public int getPlayerX(String id)
	{
		Player player = getPlayer(id);
		return player.getX();
	}
	
	public void setPlayerX(String id, int x)
	{
		Player player = getPlayer(id);
		player.setX(x);
	}
	
	public int getPlayerY(String id)
	{
		Player player = getPlayer(id);
		return player.getY();
	}
	
	public void setPlayerY(String id, int y)
	{
		Player player = getPlayer(id);
		player.setY(y);
	}
	
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

	public int getCurrentRound() 
	{
		return roundNum;
	}

	public void incrementRound() 
	{
		roundNum++;
		saveCopy = copy();
		save();
	}

	public void setMap(Map map) 
	{
		this.map = map;
	}

	public void setTimer(int n) 
	{
		timer = n;
	}

	public int getTimer() 
	{
		return timer;
	}

	public void incrementTimer() 
	{
		timer++;
	}

	public void decrementTimer() 
	{
		timer--;
	}
	
	public Session getSaveCopy()
	{
		return saveCopy;
	}
	
	public void forceSave()
	{
		saveCopy = copy();
		save();
	}
	public DatabaseObject getDatabaseObject()
    {
        DatabaseObject save = new DatabaseObject();
        
        save.put("id", id);
        save.put("timer", timer);
        save.put("roundNum", roundNum);
        save.put("currentPlayerIndex", currentPlayerIndex);
        save.put("playerIds", getPlayerIds());
        
        for (Player player : players)
        {                        
            String id = "player_" + player.getId() + "_";
            save.put(id + "id", player.getId());
            save.put(id + "name", player.getName());
            save.put(id + "colorRed", player.getColor().getRed());
            save.put(id + "colorGreen", player.getColor().getGreen());
            save.put(id + "colorBlue", player.getColor().getBlue());
            save.put(id + "type", player.getType().toString());
            save.put(id + "x", player.getX());
            save.put(id + "y", player.getY());
            
            save.put(id + "money", player.getMoney());
            save.put(id + "ore", player.getOre());
            save.put(id + "food", player.getFood());
            save.put(id + "energy", player.getEnergy());
            save.put(id + "crystite", player.getCrystite());
            
            ArrayList<String> plotIds = new ArrayList<>();
            for (String plotId : getPlayerOwnedPlotIds(player.getId()))
            {
            	plotIds.add(plotId);
            }
            save.put(id + "plots", plotIds);
        }
        
        if (map != null)
        {
            for (int a = 0; a < Map.HEIGHT; a++)
            {
                for (int b = 0; b < Map.WIDTH; b++)
                {
                    Plot plot = map.get(b, a);
                    save.put("plot_" + plot.getId(), plot.getPlotType().toString());
                    save.put("plot_" + plot.getId() + "_improvement", plot.getImprovementType().toString());
                    if (plot.getColor() != null)
                    {
                        save.put("plot_" + plot.getId() + "_colorRed", plot.getColor().getRed());
                        save.put("plot_" + plot.getId() + "_colorGreen", plot.getColor().getGreen());
                        save.put("plot_" + plot.getId() + "_colorBlue", plot.getColor().getBlue());
                    }
                }
            }        
        }
        
        return save;
    }
    
    private void save()
    {
        if (saveCopy == null)
        {
            saveCopy = copy();
        }
        
        DB db = DB.getInstance();
        db.put("saves", id, saveCopy.getDatabaseObject());
    }
    
    private LocalSession copy()
    {
           return new LocalSession(this);
    }

    public String getID() 
    {
        return id;
    }
}
