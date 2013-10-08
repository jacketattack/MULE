package ui.panel;

import game.CharacterType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Window;

/**
 * This class holds the JButtons for the different races
 * that the user can pick. It is put within the CharacterCreationPanel.
 * 
 * @author trevor
 *
 */
@SuppressWarnings("serial")
public class CharacterTypePanel extends JPanel 
{	
	private JButton humanButton;
	private JButton flapperButton;
	private JButton bonzoidButton;
	private JButton ugaiteButton;
	private JButton buzziteButton;

	
	/**
	 * This constructor contains all the display of the JButtons
	 * and adds their respective actionListeners as well.
	 */
	public CharacterTypePanel() 
	{
		humanButton = new JButton("Human");
		humanButton.addActionListener(new CharacterListener());
		add(humanButton);
		
		flapperButton = new JButton("Flapper");
		flapperButton.addActionListener(new CharacterListener());
		add(flapperButton);
		
		bonzoidButton = new JButton("Bonzoid");
		bonzoidButton.addActionListener(new CharacterListener());
		add(bonzoidButton);
		
		ugaiteButton = new JButton("Ugaite");
		ugaiteButton.addActionListener(new CharacterListener());
		add(ugaiteButton);
		
		buzziteButton = new JButton("Buzzite");
		buzziteButton.addActionListener(new CharacterListener());
		add(buzziteButton);
	}
	
	/**
	 * This private inner class represents the actionListeners for the buttons
	 * that apply to each of the buttons.
	 * @author trevor
	 *
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
			JButton button = (JButton)e.getSource();
			String name = button.getText();
			
			CharacterType type = CharacterType.HUMAN;
			
			switch (name)
			{
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
			
			Window window = Window.getInstance();
			CharacterCreationPanel panel = (CharacterCreationPanel)window.getPanel();
			
			panel.setCharacterType(type);
		}	
	}
}
