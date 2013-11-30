package ui.panel;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: nickt
 * Date: 11/30/13
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class InstructionsPanel extends JPanel
{
    private JLabel title;
    private JTextArea text;
    private JScrollPane scroller;
    private Scanner instructionsStream;

    public InstructionsPanel()
    {
        loadText();
        this.setLayout(new BorderLayout(0, 5));
        title = new JLabel("Instructions");
        text = new JTextArea(20,50);
        text.setEditable(false);
        text.setText("");





        while (instructionsStream.hasNextLine())
        {
            text.append(instructionsStream.nextLine());
        }

        scroller = new JScrollPane(text);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);



        //add everything to the layout below here
        add(title, BorderLayout.NORTH);
        add(scroller,BorderLayout.CENTER);

   }

    private void loadText()
    {
        try {
            instructionsStream = new Scanner(new FileInputStream("assets/instructions.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found in instructions");
        }
    }


}
