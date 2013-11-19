package ui.panel;

import game.LocalSession;
import game.Session;
import game.state.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.Button;
import ui.SimpleFocusListener;
import ui.Window;
import ui.render.Render;
import core.Callable;
import core.StateSelector;
import core.db.DB;
import core.db.DatabaseObject;

public class LoadPanel extends RenderPanel
{	
	private static final long serialVersionUID = 6614707792830070289L;

    private JTextField idTextField;

    private Render backgroundRender;

	public LoadPanel() 
	{   
		backgroundRender = new Render();
		backgroundRender.addImage("assets/images/background.png");
		renders.add(backgroundRender);

        JLabel prompt = new JLabel();
		add(prompt);
		
		idTextField = new JTextField(40);
		idTextField.setText("game id");
		idTextField.addFocusListener(new SimpleFocusListener("game id"));
		add(idTextField);

        JButton doneBtn = new JButton("load");
		doneBtn.addActionListener(new DoneListener());
        add(doneBtn);
        
		Button backButton = new Button("assets/images/buttons/backDefault.png", "assets/images/buttons/backHover.png", "assets/images/buttons/backClick.png");
		backButton.setWidth(71);
		backButton.setHeight(33);
		backButton.setX(539);
		backButton.setY(367);
		onHover(backButton, backButton.HOVER_COMMAND, backButton.UNHOVER_COMMAND);
		onPress(backButton, backButton.PRESS_COMMAND);
		onRelease(backButton, new Callable()
		{
			public void call()
			{
	            Window window = Window.getInstance();
	            window.setPanel(new MenuPanel());
	            	
			}
		});
		buttons.add(backButton);
	}
	
	public void preRender()
	{
		renders.add(backgroundRender);
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
