package core.db;

import game.Session;

public interface Database 
{
    public boolean isConnected();
    public boolean exists(String collectionId, String objectId);
	public String save(String id, Session session);
	public Session load(String id);
}
