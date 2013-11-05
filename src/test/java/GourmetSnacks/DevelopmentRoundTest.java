/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GourmetSnacks;

import org.junit.Test;
import java.util.ArrayList;
import game.Player;
import game.Difficulty;
import game.PlayerType;
import game.TurnOrderCalculator;
import java.util.Collections;

/**
 *
 * @author nicholast
 */
public class DevelopmentRoundTest {
    
   public ArrayList<Player> cList =  new ArrayList<>();
   
   @Test
   public void turnOrderTest()
   {
       cList.add(new Player(PlayerType.BUZZITE, Difficulty.BEGINNER));
       cList.add(new Player(PlayerType.FLAPPER, Difficulty.BEGINNER));
       cList.add(new Player(PlayerType.HUMAN, Difficulty.TOURNAMENT));
       cList.add(new Player(PlayerType.HUMAN, Difficulty.BEGINNER));
       System.out.println("Sorted characters");
       Collections.sort(cList,new TurnOrderCalculator());
       for (Player c: cList)
       {
           System.out.println(c);
       }
   }
   
}
