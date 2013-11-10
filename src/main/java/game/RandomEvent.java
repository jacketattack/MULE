package game;

import java.util.Random;
import ui.Window;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: nickt
 * Date: 11/10/13
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class RandomEvent
{
    private static Random random = new Random();
    private static Session session;
    private static int[] m = {25,25,25,50,50,50,50,75,75,75,75,100};


    public static void generateEvent(Session sess, boolean needsGoodEvent)
    {
         session = sess;
         if (needsGoodEvent)
         {
             int randInt = random.nextInt(100);
             if (randInt <= 26)
             {
                 generateGoodEvent();
             }
         }
        else
         {
            int firstRand = random.nextInt(100);
            int secondRand = random.nextInt(7);
            if (firstRand <= 26)
            {
                if (secondRand <= 4)
                {
                    generateGoodEvent();
                }
                else
                {
                      generateBadEvent();
                }
            }
         }
    }

    private static void generateGoodEvent()
    {
        Window window = Window.getInstance();
        JOptionPane.showMessageDialog(window,"This is a good event");
    }

    private static void generateBadEvent()
    {
        Window window = Window.getInstance();
        JOptionPane.showMessageDialog(window,"This is a bad event");

    }

}
