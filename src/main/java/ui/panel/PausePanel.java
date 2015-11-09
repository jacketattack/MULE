package ui.panel;

import game.Session;
import game.state.GameState;
import game.state.MenuState;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import ui.Button;
import ui.Window;
import core.Callable;
import core.StateSelector;

/**
 * This panel is triggered in the game when any player
 * hits the 'p' game and wants to pause the game.
 * 
 * It gives them a few different options to do while 
 * in pause mode.
 * 
 * @author trevor
 *
 */
public class PausePanel extends RenderPanel
{
    private static final long serialVersionUID = -3532616433483019497L;

    /**
     * Like other JPanels we have, constructor does all hard work
     * of laying out images, text boxes and buttons.
     */
    public PausePanel()
    {
        StateSelector stateSelector = StateSelector.getInstance();
        GameState state = (GameState) stateSelector.getState();
        Session session = state.getSession();

        JTextField idTextField = new JTextField(40);
        idTextField.setEditable(false);
        idTextField.setText(session.getId());
        add(idTextField);
        
        StringSelection stringSelection = new StringSelection(session.getId());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        
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
                window.setPanel(new GamePanel());
                    
            }
        });
        buttons.add(backButton);

        JButton quitBtn = new JButton("quit");
        quitBtn.addActionListener(new QuitListener());
        add(quitBtn);
    }
    
    /**
     * One button that is important is to quit the game. We simply 
     * change the state back to MenuState and we have quit out!
     * 
     * @author trevor
     *
     */
    private class QuitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            StateSelector stateSelector = StateSelector.getInstance();
            stateSelector.setState(new MenuState());

            Window window = Window.getInstance();
            window.setPanel(new MenuPanel());
        }
    }
}
