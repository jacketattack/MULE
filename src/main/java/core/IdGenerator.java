	package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * NameGenerator combines a list of adjectives and nouns to create interesting IDs.
 */
public class IdGenerator 
{
	private static ArrayList<String> ids = new ArrayList<>();
	
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
		
		// if ids failed to load,
		// return a random number
		if (ids.size() == 0)
		{
			return "" + Math.random();
		}
		
		return ids.remove(0);
	}
	
	/**
	 * Lazy loads 100 ids so that they can be accessed later
	 */
	private static void loadIds()
	{	
	    ArrayList<String> adjectives = getText("/assets/adjectives.txt");
	    ArrayList<String> nouns = getText("/assets/nouns.txt");
		
	    // combine 100 random adjectives and nouns
	    for (int a = 0; a < 100 && a < nouns.size() && a < adjectives.size(); a++)
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
	 * Load the text of a file and return an array of line separated text
	 * @param filename The filename to read text from
	 * @return List of read words
	 */
	private static ArrayList<String> getText(String filename)
	{	
		ArrayList<String> words = new ArrayList<String>();
		InputStream input = IdGenerator.class.getResourceAsStream(filename);
		
		if (input != null)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
			try
			{
				String line = reader.readLine();
				
				while (line != null)
				{
					line = reader.readLine();
					if (Math.random() < 0.10)
					{
						words.add(line);
					}
				}
			}
			catch (IOException e)
			{
				
			}
		}
		
	    return words;
	}
}
