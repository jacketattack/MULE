package ui.panel;

import game.Session;
import game.state.GameState;
import game.state.MenuState;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import ui.Button;
import ui.Window;
import core.Callable;
import core.StateSelector;

public class PausePanel extends RenderPanel
{
	private static final long serialVersionUID = -3532616433483019497L;

	private JTextField idTextField;
	private JButton quitBtn;
	
	public PausePanel()
	{
		StateSelector stateSelector = StateSelector.getInstance();
		GameState state = (GameState) stateSelector.getState();
		Session session = state.getSession();
		
		idTextField = new JTextField(40);
		idTextField.setEditable(false);
		idTextField.setText(session.getID());
		add(idTextField);
		
		StringSelection stringSelection = new StringSelection(session.getID());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
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
	            window.setPanel(new GamePanel());
	            	
			}
		});
		buttons.add(backButton);
		
		quitBtn = new JButton("quit");
		quitBtn.addActionListener(new QuitListener());
		add(quitBtn);
	}
	
	private class QuitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			StateSelector stateSelector = StateSelector.getInstance();
			stateSelector.setState(new MenuState());

			Window window = Window.getInstance();
			window.setPanel(new MenuPanel());
		}
	}
}
