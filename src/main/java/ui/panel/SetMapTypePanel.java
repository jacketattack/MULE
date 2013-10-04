/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SetMapTypePanel extends JPanel {
    private JLabel title;
    private JButton preDefMap;
    private JButton randomMap;
    public SetMapTypePanel() {
        title=new JLabel("Pick a map type");
        preDefMap=new JButton("Standard");
        preDefMap.addActionListener(new MapListener(false));
        randomMap=new JButton("Random");
        preDefMap.addActionListener(new MapListener(true));
        add(title);
        add(preDefMap);
        add(randomMap);
    }
    private class MapListener implements ActionListener{
        boolean standOrRand;
        public MapListener(boolean standOrRand){
            this.standOrRand=standOrRand;
        }
        public void actionPerformed(ActionEvent e){
            Window window=Window.getInstance();
            StateSelector stateSelector = StateSelector.getInstance();
            GameSetupState state = (GameSetupState)stateSelector.getState();
            state.setMap(new Map(standOrRand));
            window.setPanel(new SetNumPlayersPanel());
        }
    }
         
    
    
}
