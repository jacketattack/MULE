package ui.panel;

import game.CharacterType;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Window;

@SuppressWarnings("serial")
public class CharacterTypePanel extends JPanel 
{	
	private JButton humanButton;
	private JButton flapperButton;
	private JButton bonzoidButton;
	private JButton ugaiteButton;
	private JButton buzziteButton;

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
	
	private class CharacterListener implements ActionListener 
	{	
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
