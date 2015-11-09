package ui.panel;

import game.LocalSession;
import game.state.GameSetupState;
import ui.Button;
import ui.Window;
import ui.render.Render;
import core.Callable;
import core.StateSelector;

/**
 * MenuPanel is the view for the main menu
 */
public class MenuPanel extends RenderPanel
{
    private static final long serialVersionUID = -2030311112997794025L;

    private Render logo;
    private Render backgroundRender;

    private GameSetupState gameSetupState;

    public MenuPanel()
    {
        gameSetupState = new GameSetupState(new LocalSession());

        backgroundRender = new Render();
        backgroundRender.addImage("assets/images/background.png");
        renders.add(backgroundRender);

        logo = new Render();
        logo.x = 210;
        logo.y = 40;
        logo.addImage("assets/images/logo.png");
        renders.add(logo);

        Button newGame = new Button("assets/images/buttons/startDefault.png", "assets/images/buttons/startHover.png", "assets/images/buttons/startClick.png");
        newGame.setWidth(160);
        newGame.setHeight(50);
        newGame.setX(230);
        newGame.setY(150);
        onHover(newGame, newGame.HOVER_COMMAND, newGame.UNHOVER_COMMAND);
        onPress(newGame, newGame.PRESS_COMMAND);
        onRelease(newGame, new Callable()
        {
            public void call()
            {
                createGame();
            }
        });
        buttons.add(newGame);

        Button instructions = new Button("assets/images/buttons/instructionsDefault.png","assets/images/buttons/instructionsHover.png","assets/images/buttons/instructionsClick.png");
        instructions.setWidth(160);
        instructions.setHeight(50);
        instructions.setX(230);
        instructions.setY(230);
        onHover(instructions,instructions.HOVER_COMMAND,instructions.UNHOVER_COMMAND);
        onPress(instructions,instructions.PRESS_COMMAND);
        onRelease(instructions, new Callable()
        {
            public void call()
            {
                goToInstructions();
            }
        });
        buttons.add(instructions);

        for (Button button : buttons)
        {
            renders.add(button.getRender());
        }
    }

    private void createGame()
    {
        StateSelector stateSelector = StateSelector.getInstance();
        stateSelector.setState(gameSetupState);

        DifficultyPanel panel = new DifficultyPanel();
        Window window = Window.getInstance();
        window.setPanel(panel);
    }

    private void goToInstructions()
    {
        Window window = Window.getInstance();
        window.setPanel(new InstructionsPanel());
    }

    public void preRender()
    {
        renders.add(backgroundRender);
        renders.add(logo);
    }
}
