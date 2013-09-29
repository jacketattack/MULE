package ui.panel;

import game.CharacterType;
import game.state.GameSetupState;
import game.state.State;

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
public class CharacterCreationPanel extends JPanel 
{	
	private int playerAt;
	private int numPlayers;
	private CharacterType currentCharacterType;
	
	private JTextField nameTextField;
	private JButton doneButton;
	private JButton humanButton;
	private JButton flapperButton;

	public CharacterCreationPanel() 
	{   
		StateSelector stateSelector = StateSelector.getInstance();
		State state = stateSelector.getState();
		
		playerAt = 1;
		numPlayers = ((GameSetupState)state).getNumPlayers();
		currentCharacterType = CharacterType.HUMAN;
		
		nameTextField = new JTextField("Name", 30);
		nameTextField.addFocusListener(new NameTextFieldListener());
		add(nameTextField);
		
		humanButton = new JButton("Human");
		humanButton.addActionListener(new HumanListener());
		add(humanButton);
		
		flapperButton = new JButton("Flapper");
		flapperButton.addActionListener(new FlapperListener());
		add(flapperButton);
		
		doneButton = new JButton("Done");
		doneButton.addActionListener(new DoneListener());
		add(doneButton);
	}
	
	private class HumanListener implements ActionListener 
	{	
		public void actionPerformed(ActionEvent e) 
		{
			currentCharacterType = CharacterType.HUMAN;
		}
	}
	
	private class FlapperListener implements ActionListener 
	{	
		public void actionPerformed(ActionEvent e) 
		{
			currentCharacterType = CharacterType.FLAPPER;
		}
	}
	
	private class DoneListener implements ActionListener 
	{	
		public void actionPerformed(ActionEvent e) 
		{
			StateSelector stateSelector = StateSelector.getInstance();
			GameSetupState state = (GameSetupState)stateSelector.getState();
			
			state.addCharacterType(currentCharacterType);
			currentCharacterType = CharacterType.HUMAN;
			
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
