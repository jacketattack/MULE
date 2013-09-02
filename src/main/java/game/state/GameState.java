package game.state;

import game.Session;
import game.round.DefaultRound;
import game.round.DevelopmentRound;
import game.round.ItemAuctionRound;
import game.round.LandAuctionRound;
import game.round.LandGrantRound;
import game.round.ProductionRound;
import game.round.RandomEventRound;
import game.round.Round;
import game.round.SetupRound;
import game.round.SummaryRound;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author grant
 * @author
 */
public class GameState implements State 
{
	private int gameCycleAt;
	private Queue<Round> rounds;
	
	public GameState()
	{
		gameCycleAt = 0;

		rounds = new LinkedList<Round>();
		rounds.add(new DefaultRound());
	}
	
	public void update() 
	{	
		Round currentRound = rounds.peek();
		
		if (currentRound == null)
		{
			advanceCycle();
		}
		
		// if (game should end)
		// {
		// 	set state finish
		// }
		
		if (currentRound.isDone())
		{
			advanceRound();
		}
	}
	
	public void advanceRound()
	{
		Round currentRound = rounds.peek();
		Session session = currentRound.getSession();
		rounds.remove();
		
		currentRound = rounds.peek();
		currentRound.setSession(session);
	}
	
	public void advanceCycle()
	{
		gameCycleAt++;
		
		Round landGrantRound = new LandGrantRound();
		landGrantRound.setSession(new Session());
		rounds.add(landGrantRound);
		
		// these rounds receive data from previous round, so session is not initially set
		rounds.add(new LandAuctionRound());
		rounds.add(new RandomEventRound());
		rounds.add(new DevelopmentRound());
		rounds.add(new ProductionRound());
		rounds.add(new ItemAuctionRound());
		rounds.add(new SummaryRound());
	}
	
	public void setInitialSession(Session session)
	{
		gameCycleAt = 0;

		SetupRound setupRound = new SetupRound();
		setupRound.setSession(session);
		
		rounds = new LinkedList<Round>();
		rounds.add(setupRound);
	}
}
