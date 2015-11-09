package ui.panel;

import game.PlayerType;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ui.ToggleButton;
import ui.Window;

/**
 * This class holds the JButtons for the different races
 * that the user can pick. It is put within the PlayerCreationPanel.
 */
public class PlayerTypePanel extends JPanel 
{   
    private static final long serialVersionUID = 7882709249182293283L;
    
    private ToggleButton previousButton;
    
    /**
     * This constructor contains all the display of the JButtons
     * and adds their respective actionListeners as well.
     */
    public PlayerTypePanel() 
    {   
        setPreferredSize(new Dimension(Window.WIDTH, 200));
        
        ToggleButton humanButton = new ToggleButton("human", "assets/images/player/robotPortrait.png", "assets/images/player/robotPortraitTransparent.png");
        humanButton.setTitle(humanButton.getAttribute("id"));
        humanButton.addActionListener(new PlayerListener());
        add(humanButton);

        ToggleButton flapperButton = new ToggleButton("flapper", "assets/images/player/robotPortrait.png", "assets/images/player/robotPortraitTransparent.png");
        flapperButton.setTitle(flapperButton.getAttribute("id"));
        flapperButton.addActionListener(new PlayerListener());
        add(flapperButton);

        ToggleButton bonzoidButton = new ToggleButton("bonzoid", "assets/images/player/robotPortrait.png", "assets/images/player/robotPortraitTransparent.png");
        bonzoidButton.setTitle(bonzoidButton.getAttribute("id"));
        bonzoidButton.addActionListener(new PlayerListener());
        add(bonzoidButton);

        ToggleButton ugaiteButton = new ToggleButton("ugaite", "assets/images/player/robotPortrait.png", "assets/images/player/robotPortraitTransparent.png");
        ugaiteButton.setTitle(ugaiteButton.getAttribute("id"));
        ugaiteButton.addActionListener(new PlayerListener());
        add(ugaiteButton);

        ToggleButton buzziteButton = new ToggleButton("buzzite", "assets/images/player/robotPortrait.png", "assets/images/player/robotPortraitTransparent.png");
        buzziteButton.setTitle(buzziteButton.getAttribute("id"));
        buzziteButton.addActionListener(new PlayerListener());
        add(buzziteButton);
        
        previousButton = humanButton;
        previousButton.turnOn();
    }
    
    /**
     * This private inner class represents the actionListeners for the buttons
     * that apply to each of the buttons.
     * @author trevor
     * @author grant
     */
    private class PlayerListener implements ActionListener 
    {   
        /**
         * This action Listener makes sure to capture the correct
         * type of race picked and pass that onto the GameSetupState
         * so that the Session can be created later on.
         */
        public void actionPerformed(ActionEvent e)
        {           
            ToggleButton button = (ToggleButton)e.getSource();
            String name = button.getAttribute("id");
            
            if (previousButton == button)
            {
                return;
            }
            
            previousButton.toggle();
            button.toggle();
            
            PlayerType type = getType(name);
            
            Window window = Window.getInstance();
            PlayerCreationPanel panel = (PlayerCreationPanel)window.getPanel();
            panel.setPlayerType(type);
            
            previousButton = button;    
        }       
    }
    
    private PlayerType getType(String name)
    {
        PlayerType type = PlayerType.HUMAN;
        
        switch (name)
        {
            case "human":
                type = PlayerType.HUMAN;
                break;
            case "flapper":
                type = PlayerType.FLAPPER;
                break;
            case "bonzoid":
                type = PlayerType.BONZOID;
                break;
            case "ugaite":
                type = PlayerType.UGAITE;
                break;
            case "buzzite":
                type = PlayerType.BUZZITE;
                break;
        }
        
        return type;
    }
}
