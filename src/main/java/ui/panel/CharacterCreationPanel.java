package ui.panel;

import game.Character;
import game.CharacterType;
import game.state.GameSetupState;
import game.state.State;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StateSelector;

/**
 * This is a JPanel used during MenuState
 * 
 * This panel displays all the buttons and text
 * boxes in order for each player to choose a race,
 * choose color, and set a name.
 * 
 * @author grant
 * @author trevor
 */
@SuppressWarnings("serial")
public class CharacterCreationPanel extends JPanel 
{	
	private int playerAt;
	private int numPlayers;
	private Color currentColor;
	private CharacterType currentCharacterType;
	
	private JTextField nameTextField;
	
	private JButton doneButton;
	
	private ColorPickerPanel colorPickerPanel;
	private CharacterTypePanel characterPanel;

	/**
	 * This constructor displays all the buttons, text boxes,
	 * and internal panels in order for the user to interface
	 * with it and design their character.
	 */
	public CharacterCreationPanel() 
	{   
		StateSelector stateSelector = StateSelector.getInstance();
		State state = stateSelector.getState();
		
		playerAt = 1;
		numPlayers = ((GameSetupState)state).getNumPlayers();
		
		nameTextField = new JTextField("Name", 30);
		nameTextField.addFocusListener(new NameTextFieldListener());
		add(nameTextField);
		
		characterPanel = new CharacterTypePanel();
		add(characterPanel);

		colorPickerPanel = new ColorPickerPanel();
		add(colorPickerPanel);
		
		doneButton = new JButton("Done");
		doneButton.addActionListener(new DoneListener());
		add(doneButton);

		resetInput();
	}
	
	/**
	 * After each user picks their settings for their character,
	 * this method resets the settings for each data value to 
	 * defaults.
	 */
	public void resetInput()
	{
		currentCharacterType = CharacterType.HUMAN;
		currentColor = Color.RED;
		nameTextField.setText("Name");
	}
	
	public void setCurrentColor(Color color)
	{
		currentColor = color;
	}
	
	public void setCharacterType(CharacterType type)
	{
		currentCharacterType = type;
	}
	
	/**
	 * This class represents the action listener for 
	 * the JButton titled "done."
	 * 
	 * Clicking on Done should save the user's choices and 
	 * move on to the next player if one exists.
	 * 
	 * @author trevor
	 *
	 */
	private class DoneListener implements ActionListener 
	{	
		/**
		 * The is listener takes the information from the user's 
		 * button clicking and entering their name and passes 
		 * it on to the GameSetup State which later on creates
		 * the important Session Object.
		 * 
		 * @param e The actionEvent triggered when user clicks the 'Done' button
		 */
		public void actionPerformed(ActionEvent e) 
		{
			StateSelector stateSelector = StateSelector.getInstance();
			GameSetupState state = (GameSetupState)stateSelector.getState();

			String name = nameTextField.getText();
			if (name.equals("Name") || name.isEmpty())
			{
				name = "David Smith";
			}
			
			Character character = new Character();
			character.setType(currentCharacterType);
			character.setColor(currentColor);
			character.setName(name);
			
			state.addCharacter(character);
			
			resetInput();

			playerAt++;
			if (playerAt > numPlayers)
			{
				state.createSession();
			}
		}
	}
	
	/**
	 * This class helps configuration for the name JTextField.
	 * When the user clicks on the field, then 'Name' that was 
	 * previously in the textbox disappears.
	 * 
	 * @author trevor
	 *
	 */
	private class NameTextFieldListener implements FocusListener
	{
		/**
		 * This is triggered when the user clicks in the box. The 
		 * helper string 'name' disappears if that is still in the box.
		 */
		public void focusGained(FocusEvent e) 
		{
			String name = nameTextField.getText();
			if (name.equals("Name"))
			{
				name = "";
			}
			nameTextField.setText(name);
		}
		
		/**
		 * When the user no longer is typing in box and types elsewhere, then
		 * if they did not input a name, the helper text reverts to 'Name.'
		 */
		public void focusLost(FocusEvent e) 
		{
			String name = nameTextField.getText();
			if (name.isEmpty())
			{
				name = "Name";
			}
			nameTextField.setText(name);
		}
	}
}
