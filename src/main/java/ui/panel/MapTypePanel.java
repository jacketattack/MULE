package ui.panel;

import game.Map;
import game.state.GameSetupState;
import game.state.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.BackListener;
import ui.Window;
import core.StateSelector;

/**
 * This JPanel is apart of the many used 
 * to let the user choose settings for the game.
 * In this case, the player simply has two JButtons
 * to choose whether the preset or random map should 
 * be used.
 */
public class MapTypePanel extends JPanel 
{
	private static final long serialVersionUID = 6764801338557620126L;

	private JLabel title;
    private JButton preDefMap;
    private JButton randomMap;
    
    /**
     * This display simply has a JLabel intructing the 
     * user to select one of the two map types represented
     * as JButtons: Standard or Random.
     */
    public MapTypePanel() 
    {
        title = new JLabel("pick a map type");
        add(title);
        
        preDefMap = new JButton("standard");
        preDefMap.addActionListener(new MapListener(false));
        add(preDefMap);
        
        randomMap = new JButton("random");
        randomMap.addActionListener(new MapListener(true));
        add(randomMap);
        
        JButton backBtn = new JButton("back");
        backBtn.addActionListener(new BackListener<DifficultyPanel, State>(DifficultyPanel.class));
        add(backBtn);
    }
    
    /**
     * This inner class is for responding to the user clicking
     * either of the buttons for map type to use.
     * 
     * @author Matt
     *
     */
    private class MapListener implements ActionListener
    {
        private boolean randomMap;

        /**
         * This constructor only defines a boolean on whether or not
         * we will be making a random map. This allows us to
         * use the same listener for both JButtons by just flipping
         * the value of this boolean.
         * 
         * @param randomMap whether or not to create random map
         */
        public MapListener (boolean randomMap)
        {
            this.randomMap = randomMap;
        }
        
        /**
         * This contains the logic for responding to a click.
         * Depending on the button clicked, a standard or random
         * map will be created and that will be passed onto GameSetupState.
         * Also, we change panel in Window to the next step in GameSetup.
         * 
         * @param e The ActionEvent containing info on the click
         */
        public void actionPerformed (ActionEvent e)
        {
        	Map map = new Map(randomMap);
        	
            Window window = Window.getInstance();
            window.setPanel(new NumPlayersPanel());
            	
            StateSelector stateSelector = StateSelector.getInstance();
            GameSetupState state = (GameSetupState)stateSelector.getState();
            state.setMap(map);
        }
    }
}
