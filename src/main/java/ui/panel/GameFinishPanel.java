package ui.panel;

import game.Session;
import game.state.MenuState;

import java.awt.Color;
import java.util.ArrayList;

import ui.Button;
import ui.Window;
import ui.render.Render;
import ui.render.StringRender;
import core.Callable;
import core.StateSelector;

public class GameFinishPanel extends RenderPanel 
{
    private static final long serialVersionUID = -1973074364841268269L;

    private ArrayList<String> playerIds;
    private Render background;
    private Render winnerSprite;
    private StringRender winner;
    private Session session;    
    
    public GameFinishPanel(Session session) 
    {
        this.session = session;
        
        background = new Render();
        background.addImage("assets/images/background.png");
        renders.add(background);
    
        winnerSprite = new Render();
        winnerSprite.addImage("assets/images/congrats.png");
        winnerSprite.x = 150;
        winnerSprite.y = 100;
        renders.add(winnerSprite);
        
        Button doneButton = new Button("assets/images/done.png");
        doneButton.setWidth(140);
        doneButton.setHeight(70);
        doneButton.setX(450);
        doneButton.setY(300);
        buttons.add(doneButton);
        onRelease(doneButton, new Callable()
        {
                public void call()
                {
                        resetGame();
                }
        });

        
        playerIds = new ArrayList<String>();
        calculateWinner();
    }
    
    private void resetGame() 
    {
        Window window = Window.getInstance();
        StateSelector stateSelector = StateSelector.getInstance();
        MenuState state = new MenuState();
        stateSelector.setState(state);
        
        window.setPanel(new MenuPanel());
    }
    
    public void calculateWinner() 
    {
        playerIds.clear();
        for (String id: session.getPlayerIds()) 
        {
            playerIds.add(id);
        }
        
        // setting default for highest scorer
        String winningPlayer = session.getPlayerName(playerIds.get(0));
        double winningScore = session.getPlayerScore(playerIds.get(0));
        if (playerIds.size() > 1) 
        {
            for (int i = 1; i < playerIds.size(); i++) 
            {
                if (session.getPlayerScore(playerIds.get(i)) > winningScore) 
                {
                    // new highest scorer!
                    winningPlayer = session.getPlayerName(playerIds.get(i));
                    winningScore = session.getPlayerScore(playerIds.get(i));
                }
            }
        }
        // now let's make String Render for winning player!
        winner = new StringRender(winningPlayer, 300, 325, Color.white);
        stringRenders.add(winner);
    }
    
    public void preRender()
    {
        renders.add(background);
        renders.add(winnerSprite);
    }
}
