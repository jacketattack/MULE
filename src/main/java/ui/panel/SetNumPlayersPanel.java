package ui.panel;

import game.state.GameSetupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.BackListener;
import ui.Window;
import core.StateSelector;

/**
 * This simple JPanel has JButtons letting the 
 * user pick how many players will be in this game.
 * It is apart of the step in GameSetup.
 * 
 * @author grant
 * @author trevor
 */
@SuppressWarnings("serial")
public class SetNumPlayersPanel extends JPanel
{
	private JLabel title;

	/**
	 * This has a simple JLabel and the 4 JButtons for 
	 * 1-4 players. All buttons use the same listener.
	 */
	public SetNumPlayersPanel() 
	{   
		title = new JLabel("select number of players");
		add(title);
		
		JButton button1 = new JButton("1");
		button1.addActionListener(new PlayerNumListener());
		add(button1);
		
		JButton button2 = new JButton("2");
		add(button2);
		button2.addActionListener(new PlayerNumListener());
		
		JButton button3 = new JButton("3");
		button3.addActionListener(new PlayerNumListener());
		add(button3);
		
		JButton button4 = new JButton("4");
		button4.addActionListener(new PlayerNumListener());
		add(button4);	
		
		JButton backBtn = new JButton("back");
		backBtn.addActionListener(new BackListener(new SetMapTypePanel()));
		add(backBtn);
	}
	
	/**
	 * This handles what occurs when the user clicks one
	 * of the JButtons. It helps parse the information for
	 * number of players and pass it on to GameSetupState for
	 * creating a Session later on.
	 * 
	 * @author trevor
	 *
	 */
	private class PlayerNumListener implements ActionListener 
	{	
		/**
		 * This first deciphers how many players are desired by 
		 * getting the title of the JButton. Then that number needs to
		 * be passed on so that it can be used to create Session object.
		 */
		public void actionPerformed(ActionEvent e) 
		{
			JButton button = (JButton)e.getSource();
			String buttonText = button.getText();
			
			switch (buttonText) 
			{
				case "1":
					setNumPlayers(1);
					break;
				case "2":
					setNumPlayers(2);
					break;
				case "3":
					setNumPlayers(3);
					break;
				case "4":
					setNumPlayers(4);
					break;
			}
			
			Window window = Window.getInstance();
			window.setPanel(new PlayerCreationPanel());
		}
		
		/**
		 * Once we know how many players are desired, we go ahead
		 * and pass that value onto the GameSetupState which will gather
		 * all of this game config data and put it into one, Session object.
		 * 
		 * @param num the number of players to set for this game
		 */
		private void setNumPlayers(int num)
		{
			StateSelector stateSelector = StateSelector.getInstance();
			GameSetupState state = (GameSetupState)stateSelector.getState();
			
			state.setNumPlayers(num);
		}
	}
}
