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
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.Window;
import core.StateSelector;

@SuppressWarnings("serial")
public class PausePanel extends JPanel
{
	private JTextField idTextField;
	
	private JButton resumeBtn;
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
		
		resumeBtn = new JButton("resume");
		resumeBtn.addActionListener(new ResumeListener());
		add(resumeBtn);
		
		quitBtn = new JButton("quit");
		quitBtn.addActionListener(new QuitListener());
		add(quitBtn);
	}
	
	private class ResumeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Window window = Window.getInstance();
			window.setPanel(new GamePanel());
			window.setPanel(new GamePanel());
		}
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
