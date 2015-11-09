package ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * This is used when players input text 
 * during the game setup so that the default text
 * shown in the box will disappear when clicked on.
 * 
 *
 */
public class SimpleFocusListener implements FocusListener
{
    private String defaultText;
    
    /**
     * The only info that the constructor needs is the 
     * default text to display when the user is not "focused"
     * on the box.
     * 
     * @param defaultText String of text to display in box by default
     */
    public SimpleFocusListener(String defaultText)
    {
        this.defaultText = defaultText;
    }
    
    /**
     * This is triggered when the user clicks on the text 
     * box to enter their own string of info. The default text
     * disappears and is replaced with an empty string.
     */
    public void focusGained(FocusEvent e) 
    {
        JTextField textField = (JTextField)e.getSource();
        
        String text = textField.getText();
        if (text.equals(defaultText))
        {
            textField.setText("");
        }
    }
    
    /**
     * If the user clicks outside of the text box and still
     * has not put any of their own info in, then the default
     * text reappears. 
     */
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
