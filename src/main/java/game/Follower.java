package game;

import java.io.Serializable;

import ui.render.Render;
import ui.render.Renderable;

/**
 * Abstract class for a Player Follower. A follower follows a single player.
 */
public abstract class Follower implements Renderable, Serializable
{	
	private static final long serialVersionUID = -5206525600475535289L;
	
	/** x position */
	protected int x;
	/** y position */
	protected int y;
	/** follower image path */
	protected String imagePath;
	/** id of the player following */
	protected String playerId;
	/** the game session */
	protected Session session;
	
	/**
	 * Create a new follower and begin following a player
	 * @param playerId
	 */
	public Follower(String playerId)
	{
		this.playerId = playerId;
	}
	
	public void setPlayerId(String id)
	{
		this.playerId = id;
	}

	/**
	 * Reset the followers x/y position
	 */
	public void reset()
	{
		setX(session.getPlayerX(playerId) + 30);
		setY(session.getPlayerY(playerId));
	}
	
	/**
	 * Get a render of the follower
	 * @return Render the render
	 */
	public Render getRender()
	{
		Render render = new Render();
		render.x = this.x;
		render.y = this.y;
		render.addImage(imagePath);
		return render;
	}

	/**
	 * Get x position
	 * @return x position
	 */
	public int getX() 
	{
		return x;
	}

	/**
	 * Set x position
	 * @param x position
	 */
	public void setX(int x) 
	{
		this.x = x;
	}

	/**
	 * Get y position
	 * @return y position
	 */
	public int getY() 
	{
		return y;
	}
	
	/**
	 * Get y position
	 * @param y position
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
	
	/**
	 * Adjust the followers x/y coordinates to follower the player
	 */
	public void update() 
	{
		setX(session.getPlayerX(playerId) + 30);
		setY(session.getPlayerY(playerId));
	}
	
	/**
	 * Set the game session
	 * @param session The session
	 */
	public void setSession(Session session)
	{
		this.session = session;
	}
}
