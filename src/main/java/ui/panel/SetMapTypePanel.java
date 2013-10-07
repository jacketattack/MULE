package ui.panel;

import core.StateSelector;
import game.state.GameSetupState;
import ui.Window;

import javax.swing.*;

import java.awt.event.*;

import game.Map;

/**
 *
 * @author Matt
 */
@SuppressWarnings("serial")
public class SetMapTypePanel extends JPanel 
{
    private JLabel title;
    private JButton preDefMap;
    private JButton randomMap;
    
    public SetMapTypePanel() 
    {
        title = new JLabel("Pick a map type");
        add(title);
        
        preDefMap = new JButton("Standard");
        preDefMap.addActionListener(new MapListener(false));
        add(preDefMap);
        
        randomMap = new JButton("Random");
        randomMap.addActionListener(new MapListener(true));
        add(randomMap);
    }
    
    private class MapListener implements ActionListener
    {
        private boolean randomMap;
        
        public MapListener (boolean randomMap)
        {
            this.randomMap = randomMap;
        }
        
        public void actionPerformed (ActionEvent e)
        {
        	Map map = new Map(randomMap);
        	
            Window window = Window.getInstance();
            window.setPanel(new SetNumPlayersPanel());
            	
            StateSelector stateSelector = StateSelector.getInstance();
            GameSetupState state = (GameSetupState)stateSelector.getState();
            state.setMap(map);
        }
    }
}
