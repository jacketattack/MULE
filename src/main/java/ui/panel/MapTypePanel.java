package ui.panel;

import game.Map;
import game.state.GameSetupState;

import javax.swing.JLabel;

import ui.Button;
import ui.Window;
import core.Callable;
import core.StateSelector;

/**
 * This JPanel is apart of the many used 
 * to let the user choose settings for the game.
 * In this case, the player simply has two JButtons
 * to choose whether the preset or random map should 
 * be used.
 */
public class MapTypePanel extends RenderPanel 
{
    private static final long serialVersionUID = 6764801338557620126L;
    
    /**
     * This display simply has a JLabel instructing the
     * user to select one of the two map types represented
     * as JButtons: Standard or Random.
     */
    public MapTypePanel() 
    {
        JLabel title = new JLabel("pick a map type");
        add(title);
        
        Button standardMapButton = new Button("assets/images/buttons/standardDefault.png", "assets/images/buttons/standardHover.png", "assets/images/buttons/standardClick.png");
        standardMapButton.setWidth(160);
        standardMapButton.setHeight(50);
        standardMapButton.setX(230);
        standardMapButton.setY(120);
        onHover(standardMapButton, standardMapButton.HOVER_COMMAND, standardMapButton.UNHOVER_COMMAND);
        onPress(standardMapButton, standardMapButton.PRESS_COMMAND);
        onRelease(standardMapButton, new Callable()
        {
            public void call()
            {
                Map map = new Map(false);
                
                Window window = Window.getInstance();
                window.setPanel(new NumPlayersPanel());
                    
                StateSelector stateSelector = StateSelector.getInstance();
                GameSetupState state = (GameSetupState)stateSelector.getState();
                state.setMap(map);
            }
        });
        buttons.add(standardMapButton);
        
        Button randomMapButton = new Button("assets/images/buttons/randomDefault.png", "assets/images/buttons/randomHover.png", "assets/images/buttons/randomClick.png");
        randomMapButton.setWidth(160);
        randomMapButton.setHeight(50);
        randomMapButton.setX(230);
        randomMapButton.setY(190);
        onHover(randomMapButton, randomMapButton.HOVER_COMMAND, randomMapButton.UNHOVER_COMMAND);
        onPress(randomMapButton, randomMapButton.PRESS_COMMAND);
        onRelease(randomMapButton, new Callable()
        {
            public void call()
            {
                Map map = new Map(true);
                Window window = Window.getInstance();
                window.setPanel(new NumPlayersPanel());
                    
                StateSelector stateSelector = StateSelector.getInstance();
                GameSetupState state = (GameSetupState)stateSelector.getState();
                state.setMap(map);
            }
        });
        buttons.add(randomMapButton);
        
        Button backButton = new Button("assets/images/buttons/backDefault.png", "assets/images/buttons/backHover.png", "assets/images/buttons/backClick.png");
        backButton.setWidth(71);
        backButton.setHeight(33);
        backButton.setX(539);
        backButton.setY(367);
        onHover(backButton, backButton.HOVER_COMMAND, backButton.UNHOVER_COMMAND);
        onPress(backButton, backButton.PRESS_COMMAND);
        onRelease(backButton, new Callable()
        {
            public void call()
            {
                Window window = Window.getInstance();
                window.setPanel(new DifficultyPanel());
                    
            }
        });
        buttons.add(backButton);
    }
}
