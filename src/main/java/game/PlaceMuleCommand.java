package game;

public class PlaceMuleCommand implements Command
{
	private Mule mule;
	private Plot plot;
	private Session session;
	
	public PlaceMuleCommand(Mule mule, Plot plot, Session session)
	{
		this.mule = mule;
		this.plot = plot;
		this.session = session;
	}
	
	public boolean execute() 
	{
		if (!plot.hasMule() && mule != null && mule.getType() != ImprovementType.EMPTY)
		{
        	plot.setMule(mule);
            session.setPlayerFollower(session.getCurrentPlayerId(), null);
            return true;
        }
	
		return false;
	}

	public boolean undo() 
	{
		
	}
}
