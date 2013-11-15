package ui.panel;

import game.LocalSession;
import game.Session;
import game.state.GameState;
import game.state.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.BackListener;
import ui.SimpleFocusListener;
import ui.Window;
import core.StateSelector;
import core.db.DB;
import core.db.DatabaseObject;

public class LoadPanel extends JPanel
{	
	private static final long serialVersionUID = 6614707792830070289L;
	
	private JLabel prompt;
	private JTextField idTextField;
	
	private JButton doneBtn;

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
        
        JButton backBtn = new JButton("back");
        backBtn.addActionListener(new BackListener<MenuPanel, State>(MenuPanel.class));
        add(backBtn);
	}

	private class DoneListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			String id = idTextField.getText();
			id = id.trim();
			
			DB db = DB.getInstance();
			DatabaseObject data = db.get("saves", id);
			
			if (data == null)
			{
				idTextField.setText("invalid");
			}
			else
			{
				Session session = new LocalSession(data);
				GameState state = new GameState(session);
				StateSelector stateSelector = StateSelector.getInstance();
				stateSelector.setState(state);
				
				GamePanel panel = new GamePanel();
				Window window = Window.getInstance();
				window.setPanel(panel);
			}
		}
	}
}
