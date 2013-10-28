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
		JTextField textfield = (JTextField)e.getSource();
		
		String text = textfield.getText();
		if (text.equals(defaultText))
		{
			textfield.setText("");
		}
	}
	
	public void focusLost(FocusEvent e) 
	{
		JTextField textfield = (JTextField)e.getSource();
		
		String text = textfield.getText();
		if (text.equals(""))
		{
			textfield.setText(defaultText);
		}
	}
}