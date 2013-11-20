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

/**
 * When players choose to load a game, this is the JPanel
 * that they will see. It contains a simple text box for putting
 * in the id of the save file and a button to go and get the data.
 * 
 * The storage is with MongoDB
 * 
 * @author trevor
 *
 */
public class LoadPanel extends RenderPanel
{	
	private static final long serialVersionUID = 6614707792830070289L;

    private JTextField idTextField;

    private Render backgroundRender;

    /**
     * Constructor contains all the parts that are shown on
     * the screen such as the text box and the done button.
     */
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
	
	/**
	 * This helps with avoiding the delay that could occur when
	 * first bringing up the load screen.
	 */
	public void preRender()
	{
		renders.add(backgroundRender);
	}
	
	/**
	 * This actionlistener for the done button collects the id
	 * and passes it off to our DB object to get all the
	 * necessary data for that id.
	 * 
	 * @author trevor
	 *
	 */
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
