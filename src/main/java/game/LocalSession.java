package game;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LocalSession implements Session, Serializable
{	
	private Map map;
	
	private int currentPlayerIndex;
	private Player lastPlayerAccessed;
	private ArrayList<Player> players;

	private int timer;
	private int roundNum;
	
	public LocalSession()
	{
		players = new ArrayList<Player>();
	}
	
	public ArrayList<String> createPlayers(int n)
	{
		players = new ArrayList<Player>();
		ArrayList<String> ids = new ArrayList<String>();
		
		for (int a = 0; a < n; a++)
		{
			String id = "" + Math.random() + a;
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
		ArrayList<String> ids = new ArrayList<String>();
		for (Player player : players)
		{
			ids.add(player.getId());
		}
		
		return ids;
	}
	
	@Deprecated
	public Player getPlayerById(String id)
	{
		return this.getPlayer(id);
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
			incrementRound();
			currentPlayerIndex = 0;
		}
		
		return advancedRound;
	}
	
	public void updatePlayer(String id)
	{
		Player player = getPlayer(id);
		player.update();
	}
	
	public boolean isPlotOwnedByPlayer(String id, Plot plot)
	{
		boolean owned = false;
		
		Player player = getPlayer(id);
		
		for (Plot playerPlot : player.getPlots())
		{
        	if (playerPlot == plot)
        	{
        		owned = true;
        	}
		}
		
		return owned;
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
			follower.init();
		}
		player.setFollower(follower);
	}

	@Deprecated
	public Follower getPlayerFollower(String id)
	{
		Player player = getPlayer(id);
		return player.getFollower();
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
	
	public void addPlotToPlayer(String id, Plot plot)
	{
		Player player = getPlayer(id);
		player.addPlot(plot);
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
		if (lastPlayerAccessed.getId().equals(id))
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
	}

	public void setMap(Map map) 
	{
		this.map = map;
	}

	public Plot getPlot(int x, int y) 
	{
		return map.get(x, y);
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
}
