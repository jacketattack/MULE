/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GourmetSnacks;

import org.junit.Test;
import java.util.ArrayList;
import game.Character;
import game.Difficulty;
import game.CharacterType;
import game.TurnOrderCalculator;
import java.util.Collections;

/**
 *
 * @author nicholast
 */
public class DevelopmentRoundTest {
    
   public ArrayList<Character> cList =  new ArrayList<>();
   
   @Test
   public void turnOrderTest()
   {
       cList.add(new Character(CharacterType.BUZZITE, Difficulty.BEGINNER));
       cList.add(new Character(CharacterType.FLAPPER, Difficulty.BEGINNER));
       cList.add(new Character(CharacterType.HUMAN, Difficulty.TOURNAMENT));
       cList.add(new Character(CharacterType.HUMAN, Difficulty.BEGINNER));
       System.out.println("Sorted characters");
       Collections.sort(cList,new TurnOrderCalculator());
       for (Character c: cList)
       {
           System.out.println(c);
       }
   }
   
}
