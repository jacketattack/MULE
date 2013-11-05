package ui.panel;

import game.Session;
import game.state.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.SimpleFocusListener;
import ui.Window;
import core.GameSave;
import core.StateSelector;
import core.db.MongoDB;

@SuppressWarnings("serial")
public class LoadPanel extends JPanel
{	
	private JLabel prompt;
	private JTextField idTextField;
	
	private JButton doneBtn;
	private JButton backBtn;

	public LoadPanel() 
	{   
		prompt = new JLabel();
		add(prompt);
		
		idTextField = new JTextField(40);
		idTextField.setText("game id");
		idTextField.addFocusListener(new SimpleFocusListener("game id"));
		add(idTextField);
		
		doneBtn = new JButton("load");
		doneBtn.addActionListener(new DoneListener());
        add(doneBtn);
        
        backBtn = new JButton("back");
        backBtn.addActionListener(new BackListener());
        add(backBtn);
	}

	private class DoneListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			String id = idTextField.getText();
			id = id.trim();
			
			GameSave gameSave = new GameSave(new MongoDB());
			Session session = gameSave.load(id);
			
			if (session == null)
			{
				idTextField.setText("invalid");
			}
			else
			{
				GameState state = new GameState(session);
				StateSelector stateSelector = StateSelector.getInstance();
				stateSelector.setState(state);
				
				GamePanel panel = new GamePanel();
				Window window = Window.getInstance();
				window.setPanel(panel);
			}
		}
	}

	private class BackListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			Window window = Window.getInstance();
			window.setPanel(new MenuPanel());
		}
	}
}
