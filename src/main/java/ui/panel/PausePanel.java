package ui.panel;

import game.Session;
import game.state.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Game;
import core.StateSelector;
import core.db.MongoDB;

@SuppressWarnings("serial")
public class PausePanel extends JPanel
{
	private JTextField idTextField;
	private JButton saveBtn;
	
	public PausePanel()
	{
		idTextField = new JTextField(40);
		idTextField.setEditable(false);
		add(idTextField);
		
		saveBtn = new JButton("save");
		saveBtn.addActionListener(new SaveListener());
		add(saveBtn);
	}
	
	private class SaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			StateSelector stateSelector = StateSelector.getInstance();
			GameState state = (GameState) stateSelector.getState();
			Session session = state.getSession();
			
			Game game = new Game(new MongoDB());
			String id = game.save(session);
			
			idTextField.setText(id);
		}
	}
}
