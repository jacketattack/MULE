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


//put in checks to make sure incrementX functions can't make things go below zero
//make this so it doesn't use joptionpane, but instead prints to bottom of screen!
public class RandomEvent
{
    private static Random random = new Random();
    private static Session session;
    private static int[] m = {25,25,25,50,50,50,50,75,75,75,75,100};
    private static final String[] goodEvents = {
            "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.",
            "A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.",
            "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $%d.", //8*m
            "YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $%d" //2*m
    };
    private static final String[] badEvents = {
            "FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $%d.",//4*m
            "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.",
            "YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $%d TO CLEAN IT UP."//6*m
    };





    public static void generateEvent(Session sess, boolean needsBadEvent)
    {
         session = sess;
         if (!needsBadEvent)
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
        int randInt = random.nextInt(4);
        String event = goodEvents[randInt];
        String currentPlayerID = session.getCurrentPlayerId();
        switch (randInt)
        {
            case 0:
                session.incrementFood(currentPlayerID,3);
                session.incrementEnergy(currentPlayerID,2);
                break;
            case 1:
                session.incrementOre(currentPlayerID,2);
                break;
            case 2:
                event = String.format(event,8*m[session.getCurrentRound()]) ;
                break;
            case 3:
                event = String.format(event,2*m[session.getCurrentRound()]);
                break;
            default:
                System.out.println("Error in random event generation");
                System.exit(0);
        }


        JOptionPane.showMessageDialog(window,event);
    }

    private static void generateBadEvent()
    {
        Window window = Window.getInstance();
        int randInt = random.nextInt(3);
        String event = badEvents[randInt];
        String currentPlayerID = session.getCurrentPlayerId();
        switch (randInt)
        {
            case 0:
                event = String.format(event,4*m[session.getCurrentRound()]);
                session.setPlayerMoney(currentPlayerID, session.getPlayerMoney(currentPlayerID)-4*m[session.getCurrentRound()]);
                break;
            case 1:
                int food = session.getPlayerFood(currentPlayerID);
                int halfFood = food/2;
                session.incrementFood(currentPlayerID,session.getPlayerFood(currentPlayerID)-halfFood);
                break;
            case 2:
                event = String.format(event,6*m[session.getCurrentRound()]);
                session.setPlayerMoney(currentPlayerID, session.getPlayerMoney(currentPlayerID)-6*m[session.getCurrentRound()]);
                break;
            default:
                System.out.println("Error in random event generation");
                System.exit(0);
        }



        JOptionPane.showMessageDialog(window,event);

    }

}
