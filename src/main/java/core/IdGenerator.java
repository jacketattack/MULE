package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * NameGenerator combines a list of adjectives and nouns to create interesting IDs.
 */
public class IdGenerator 
{
	private static ArrayList<String> ids = new ArrayList<String>();
	
	/**
	 * Grab a random id from the generator
	 * @return a random id
	 */
	public static String getId()
	{
		if (ids.size() == 0)
		{
			loadIds();
		}
		
		return ids.remove(0);
	}
	
	/**
	 * Lazy loads 100 ids so that they can be accessed later
	 */
	private static void loadIds()
	{	
	    ArrayList<String> adjectives = getText("assets/adjectives.txt");
	    ArrayList<String> nouns = getText("assets/nouns.txt");
		
	    // combine 100 random adjectives and nouns
	    for (int a = 0; a < 100; a++)
	    {
	    	String noun = nouns.get((int)(Math.random() * nouns.size()));
	    	String adjective = adjectives.get((int)(Math.random() * adjectives.size()));
	    	ids.add(adjective + "-" + noun);
	    }
	    
	    // random sort
	    long seed = System.nanoTime();
	    Collections.shuffle(ids, new Random(seed));
	    Collections.shuffle(ids, new Random(seed));
	}
	
	/**
	 * Read text from a file
	 * @param filename File containing words
	 * @return A list of random words from the file
	 */
	private static ArrayList<String> getText(String filename)
	{
	    File file = new File(filename);
	    ArrayList<String> words = new ArrayList<String>();

	    try
	    {
	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) 
	        {
	            String word = sc.nextLine();
	            
	            // only need a few, so low probability works well
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
