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
	    ArrayList<String> adjectives = getText("assets/adjectives.txt");
	    ArrayList<String> nouns = getText("assets/nouns.txt");
		
	    for (int a = 0; a < 100; a++)
	    {
	    	String noun = nouns.get((int)(Math.random() * nouns.size()));
	    	String adjective = adjectives.get((int)(Math.random() * adjectives.size()));
	    	names.add(adjective + "-" + noun);
	    }
	    
	    long seed = System.nanoTime();
	    Collections.shuffle(names, new Random(seed));
	    Collections.shuffle(names, new Random(seed));
	}
	
	private static ArrayList<String> getText(String fileName)
	{
	    File file = new File(fileName);
	    ArrayList<String> words = new ArrayList<String>();

	    try
	    {
	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) 
	        {
	            String word = sc.nextLine();
	            
	            if (Math.random() < 0.10)
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
	    
	    return words;
	}
}
