package game.round;

import java.awt.Color;
import java.util.ArrayList;

import ui.Button;
import ui.render.Render;
import ui.render.StringRender;

public class ConclusionRound extends Round {
	
	private ArrayList<String> playerIds;
	private Button doneButton; 
	private Render background;
	private StringRender winner;
	private boolean done;
	
	public ConclusionRound() {
		playerIds = new ArrayList<String>();
		
		doneButton = new Button("assets/images/done.png");
		doneButton.setWidth(140);
		doneButton.setHeight(70);
		doneButton.setX(550);
		doneButton.setY(480);
		renders.add(doneButton.getRender());
		
		background = new Render();
		background.addImage("assets/images/winner.png");
		background.x = 0;
		background.y = 0;
		renders.add(background);
		
		done = false;
		
	}
	
	@Override
	public void init() {
		playerIds.clear();
		for (String id: session.getPlayerIds()) {
			playerIds.add(id);
		}
		// setting default for highest scorer
		String winningPlayer = session.getPlayerName(playerIds.get(0));
		double winningScore = session.getPlayerScore(playerIds.get(0));
		for (int i = 1; i < playerIds.size(); i++) 
		{
			if (session.getPlayerScore(playerIds.get(i)) > winningScore) {
				// new highest scorer!
				winningPlayer = session.getPlayerName(playerIds.get(i));
				winningScore = session.getPlayerScore(playerIds.get(i));
			}
		}
		// now let's make String Render for winning player!
		winner = new StringRender(winningPlayer, 100, 30, Color.white);
		stringRenders.add(winner);
	}
	
	@Override
	public void update() {
		renders.clear();
		stringRenders.clear();
		
		renders.add(background);
		renders.add(doneButton.getRender());
		stringRenders.add(winner);

	}

	@Override
	public void click(int x, int y, boolean leftMouse) {
		
		if (doneButton.inBounds(x, y))
		{
			done = true;
		}
	}

	@Override
	public boolean isDone() {
		return done;
	}
}
