package ui.panel;

import game.CharacterType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ui.Window;
import core.ImageLoader;

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
	private JButton humanButton;
	private ImageIcon humanIconSelected;
	private ImageIcon humanIconUnselected;
	
	private JButton flapperButton;
	private ImageIcon flapperIconSelected;
	private ImageIcon flapperIconUnselected;
	
	private JButton bonzoidButton;
	private ImageIcon bonzoidIconSelected;
	private ImageIcon bonzoidIconUnselected;
	
	private JButton ugaiteButton;
	private ImageIcon ugaiteIconSelected;
	private ImageIcon ugaiteIconUnselected;

	private JButton buzziteButton;
	private ImageIcon buzziteIconSelected;
	private ImageIcon buzziteIconUnselected;
	
	private JButton previousButton;
	
	/**
	 * This constructor contains all the display of the JButtons
	 * and adds their respective actionListeners as well.
	 */
	public CharacterTypePanel() 
	{
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		humanIconSelected = new ImageIcon(imageLoader.load("assets/images/character/robotPortrait.png"));
		humanIconUnselected = new ImageIcon(imageLoader.load("assets/images/character/robotPortraitTransparent.png"));
		
		flapperIconSelected = new ImageIcon(imageLoader.load("assets/images/character/robotPortrait.png"));
		flapperIconUnselected = new ImageIcon(imageLoader.load("assets/images/character/robotPortraitTransparent.png"));

		bonzoidIconSelected = new ImageIcon(imageLoader.load("assets/images/character/robotPortrait.png"));
		bonzoidIconUnselected = new ImageIcon(imageLoader.load("assets/images/character/robotPortraitTransparent.png"));

		ugaiteIconSelected = new ImageIcon(imageLoader.load("assets/images/character/robotPortrait.png"));
		ugaiteIconUnselected = new ImageIcon(imageLoader.load("assets/images/character/robotPortraitTransparent.png"));

		buzziteIconSelected = new ImageIcon(imageLoader.load("assets/images/character/robotPortrait.png"));
		buzziteIconUnselected = new ImageIcon(imageLoader.load("assets/images/character/robotPortraitTransparent.png"));
		
		humanButton = createButton(humanButton, humanIconUnselected, "Human");
		flapperButton = createButton(flapperButton, flapperIconUnselected, "Flapper");
		bonzoidButton = createButton(bonzoidButton, bonzoidIconUnselected, "Bonzoid");
		ugaiteButton = createButton(ugaiteButton, ugaiteIconUnselected, "Ugaite");
		buzziteButton = createButton(buzziteButton, buzziteIconUnselected, "Buzzite");
		
		previousButton = humanButton;
		setIcon(previousButton, previousButton.getText(), true);
	}
	
	private JButton createButton(JButton button, ImageIcon icon, String text)
	{
		button = new JButton(text);
		button.setIcon(icon);
		button.setFocusable(false);
		button.setVerticalTextPosition(SwingConstants.TOP);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.addActionListener(new CharacterListener());
		add(button);
		
		return button;
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
			JButton button = (JButton)e.getSource();
			String name = button.getText();
			
			if (previousButton == button)
			{
				return;
			}
			
			CharacterType type = getType(name);
			
			setIcon(previousButton, previousButton.getText(), false);
			setIcon(button, name, true);
			
			
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
	
	private void setIcon(JButton button, String name, boolean selected)
	{
		switch (name)
		{
			case "Human":
				if (selected)
				{
					button.setIcon(humanIconSelected);
				}
				else
				{
					button.setIcon(humanIconUnselected);
				}
				break;
			case "Flapper":
				if (selected)
				{
					button.setIcon(flapperIconSelected);
				}
				else
				{
					button.setIcon(flapperIconUnselected);
				}
				break;
			case "Bonzoid":
				if (selected)
				{
					button.setIcon(bonzoidIconSelected);
				}
				else
				{
					button.setIcon(bonzoidIconUnselected);
				}
				break;
			case "Ugaite":
				if (selected)
				{
					button.setIcon(ugaiteIconSelected);
				}
				else
				{
					button.setIcon(ugaiteIconUnselected);
				}
				break;
			case "Buzzite":
				if (selected)
				{
					button.setIcon(buzziteIconSelected);
				}
				else
				{
					button.setIcon(buzziteIconUnselected);
				}
				break;
		}
	}
}
