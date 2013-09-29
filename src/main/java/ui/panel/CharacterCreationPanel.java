package ui.panel;

import game.CharacterType;
import game.state.GameSetupState;
import game.state.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StateSelector;

public class CharacterCreationPanel extends JPanel {
	
	private int playerAt;
	private int numPlayers;
	private JTextField nameTextField;
	private JButton doneButton;
	private JButton humanButton;
	private JButton flapperButton;
	private CharacterType currentCharacterType;

	public CharacterCreationPanel() 
	{   
		playerAt = 1;
		
		currentCharacterType = CharacterType.HUMAN;
		StateSelector stateSelector = StateSelector.getInstance();
		State currentState = stateSelector.getState();
		numPlayers = ((GameSetupState)currentState).getNumPlayers();
		
		nameTextField = new JTextField("Name", 30);
		
		doneButton = new JButton("Done");
		humanButton = new JButton("Human");
		flapperButton = new JButton("Flapper");
		
		doneButton.addActionListener(new DoneListener());
		humanButton.addActionListener(new HumanListener());
		flapperButton.addActionListener(new FlapperListener());
		
		add(nameTextField);
		add(humanButton);
		add(flapperButton);
		add(doneButton);
	}
	
	private class HumanListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Set to Human");
			currentCharacterType = CharacterType.HUMAN;
		}
	}
	
	private class FlapperListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Set to Flapper");
			currentCharacterType = CharacterType.FLAPPER;
		}
	}
	
	private class DoneListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) 
		{
			StateSelector stateSelector = StateSelector.getInstance();
			State currentState = stateSelector.getState();
			
			((GameSetupState)currentState).addCharacterType(currentCharacterType);
			
			String name = nameTextField.getText();
			((GameSetupState)currentState).addPlayerName(name);
			
			System.out.println(playerAt + " " + numPlayers);
			
			playerAt++;
			if (playerAt > numPlayers)
			{
				((GameSetupState)currentState).createSession();
			}
		}
	}

}
