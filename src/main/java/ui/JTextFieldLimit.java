package ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument 
{
	private static final long serialVersionUID = -90890825246303078L;

	private int limit;

	public JTextFieldLimit(int limit)
	{
		super();
		this.limit = limit;
	}
	
	public void insertString(int offset, String string, AttributeSet attribute) throws BadLocationException 
	{
		if (string == null)
			return;
		
		if (getLength() + string.length() <= limit)
		{
			super.insertString(offset, string, attribute);
		}
	}
}