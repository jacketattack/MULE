package ui.panel;

import game.CharacterType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ui.ToggleButton;
import ui.Window;

/**
 * This class holds the JButtons for the different races
 * that the user can pick. It is put within the CharacterCreationPanel.
 * 
 * @author trevor
 * @author grant
 */
@SuppressWarnings("serial")
public class CharacterTypePanel extends JPanel 
{	
	private ToggleButton previousButton;
	
	/**
	 * This constructor contains all the display of the JButtons
	 * and adds their respective actionListeners as well.
	 */
	public CharacterTypePanel() 
	{	
		ToggleButton humanButton = new ToggleButton("Human", "assets/images/character/robotPortrait.png", "assets/images/character/robotPortraitTransparent.png");
		humanButton.addActionListener(new CharacterListener());
		add(humanButton);

		ToggleButton flapperButton = new ToggleButton("Flapper", "assets/images/character/robotPortrait.png", "assets/images/character/robotPortraitTransparent.png");
		flapperButton.addActionListener(new CharacterListener());
		add(flapperButton);

		ToggleButton bonzoidButton = new ToggleButton("Bonzoid", "assets/images/character/robotPortrait.png", "assets/images/character/robotPortraitTransparent.png");
		bonzoidButton.addActionListener(new CharacterListener());
		add(bonzoidButton);

		ToggleButton ugaiteButton = new ToggleButton("Ugaite", "assets/images/character/robotPortrait.png", "assets/images/character/robotPortraitTransparent.png");
		ugaiteButton.addActionListener(new CharacterListener());
		add(ugaiteButton);

		ToggleButton buzziteButton = new ToggleButton("Buzzite", "assets/images/character/robotPortrait.png", "assets/images/character/robotPortraitTransparent.png");
		buzziteButton.addActionListener(new CharacterListener());
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
	private class CharacterListener implements ActionListener 
	{	
		/**
		 * This action Listener makes sure to capture the correct
		 * type of race picked and pass that onto the GameSetupState
		 * so that the Session can be created later on.
		 */
		public void actionPerformed(ActionEvent e)
		{			
			ToggleButton button = (ToggleButton)e.getSource();
			String name = button.getText();
			
			if (previousButton == button)
			{
				return;
			}
			
			previousButton.toggle();
			button.toggle();
			
			CharacterType type = getType(name);
			
			Window window = Window.getInstance();
			CharacterCreationPanel panel = (CharacterCreationPanel)window.getPanel();
			panel.setCharacterType(type);
			
			previousButton = button;	
		}		
	}
	
	private CharacterType getType(String name)
	{
		CharacterType type = CharacterType.HUMAN;
		
		switch (name)
		{
			case "Human":
				type = CharacterType.HUMAN;
				break;
			case "Flapper":
				type = CharacterType.FLAPPER;
				break;
			case "Bonzoid":
				type = CharacterType.BONZOID;
				break;
			case "Ugaite":
				type = CharacterType.UGAITE;
				break;
			case "Buzzite":
				type = CharacterType.BUZZITE;
				break;
		}
		
		return type;
	}
}
