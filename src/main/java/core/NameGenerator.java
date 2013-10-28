package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class NameGenerator 
{
	private static ArrayList<String> names = new ArrayList<String>();
	private static ArrayList<String> words = new ArrayList<String>();
	
	public static String getName()
	{
		if (names.size() == 0)
		{
			loadNames();
		}
		
		return names.remove(0);
	}
	
	private static void loadNames()
	{
	    File file = new File("assets/words.txt");

	    try
	    {
	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) 
	        {
	            String word = sc.nextLine();
	            
	            if (Math.random() < 0.02)
	            {
	            	words.add(word);
	            }
	        }
	        
	        sc.close();
	    } 
	    catch (FileNotFoundException e) 
	    {
	        e.printStackTrace();
	    } 
	    
	    for (int a = 0, b = words.size() - 1; a < b; a++, b--)
	    {
	    	String start = words.get(a);
	    	String end = words.get(b);
	    	
	    	names.add(start + "-" + end);
	    }
	    
	    long seed = System.nanoTime();
	    Collections.shuffle(names, new Random(seed));
	    Collections.shuffle(names, new Random(seed));
	}
}
