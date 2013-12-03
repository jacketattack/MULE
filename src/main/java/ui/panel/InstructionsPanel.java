package ui.panel;

import game.state.MenuState;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ui.Window;
import ui.render.Render;
import core.Callable;
import core.StateSelector;

/**
 * Created with IntelliJ IDEA.
 * User: nickt
 * Date: 11/30/13
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class InstructionsPanel extends RenderPanel
{
	private static final long serialVersionUID = -1189480354577346542L;

	private JLabel title;
    private JTextArea text;
    private JScrollPane scroller;
    private Scanner instructionsStream;
    private Render backgroundRender;

    public InstructionsPanel()
    {

        backgroundRender = new Render();
        backgroundRender.addImage("assets/images/background.png");
        renders.add(backgroundRender);
        loadText();
        this.setLayout(new BorderLayout(0, 5));
        title = new JLabel("Instructions");
        text = new JTextArea();
        text.setEditable(false);
        text.setText("");





        while (instructionsStream.hasNextLine())
        {
            text.append(instructionsStream.nextLine()+"\n");
        }

        scroller = new JScrollPane(text);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        text.setCaretPosition(0);
        text.setHighlighter(null);

        //add everything to the layout below here
        this.setBorder(BorderFactory.createEmptyBorder(5,9,70,9));
        add(title, BorderLayout.NORTH);
        add(scroller,BorderLayout.CENTER);

        ui.Button backButton = new ui.Button("assets/images/buttons/backDefault.png", "assets/images/buttons/backHover.png", "assets/images/buttons/backClick.png");
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

                StateSelector stateSelector = StateSelector.getInstance();
                stateSelector.setState(new MenuState());

            }
        });
        buttons.add(backButton);


   }

    private void loadText()
    {
        try {
            instructionsStream = new Scanner(new FileInputStream("assets/instructions.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found in instructions");
        }
    }

    public void preRender()
    {
        renders.add(backgroundRender);
    }



}
