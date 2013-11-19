package ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class SimpleFocusListener implements FocusListener
{
	private String defaultText;
	
	public SimpleFocusListener(String defaultText)
	{
		this.defaultText = defaultText;
	}
	
	public void focusGained(FocusEvent e) 
	{
		JTextField textField = (JTextField)e.getSource();
		
		String text = textField.getText();
		if (text.equals(defaultText))
		{
			textField.setText("");
		}
	}
	
	public void focusLost(FocusEvent e) 
	{
		JTextField textField = (JTextField)e.getSource();
		
		String text = textField.getText();
		if (text.equals(""))
		{
			textField.setText(defaultText);
		}
	}
}