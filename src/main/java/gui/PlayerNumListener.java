package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PlayerNumListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		JButton but = (JButton)e.getSource();
		String name = but.getName();
		
		switch (name) {
		
		case "1":
			
		}
	}
}
