package ui.panel;

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

	public CharacterCreationPanel() 
	{   
		StateSelector stateSelector = StateSelector.getInstance();
		State state = stateSelector.getState();
		
		playerAt = 1;
		numPlayers = ((GameSetupState)state).getNumPlayers();
		currentCharacterType = CharacterType.HUMAN;
		currentColor = Color.RED;
		
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
	}
	
	public void setCurrentColor(Color color)
	{
		currentColor = color;
	}
	
	public void setCharacterType(CharacterType type)
	{
		currentCharacterType = type;
	}
	
	private class DoneListener implements ActionListener 
	{	
		public void actionPerformed(ActionEvent e) 
		{
			StateSelector stateSelector = StateSelector.getInstance();
			GameSetupState state = (GameSetupState)stateSelector.getState();
			
			state.addCharacterType(currentCharacterType);
			currentCharacterType = CharacterType.HUMAN;
			
			state.addCharacterColor(currentColor);
			currentColor = Color.RED;
			
			String name = nameTextField.getText();
			if (name.equals("Name") || name.isEmpty())
			{
				name = "David Smith";
			}
			state.addPlayerName(name);
			nameTextField.setText("Name");

			playerAt++;
			if (playerAt > numPlayers)
			{
				state.createSession();
			}
		}
	}
	
	private class NameTextFieldListener implements FocusListener
	{
		public void focusGained(FocusEvent e) 
		{
			String name = nameTextField.getText();
			if (name.equals("Name"))
			{
				name = "";
			}
			nameTextField.setText(name);
		}
		
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
