package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
	
	private static ArrayList<String> getText(String filename)
	{	
		ArrayList<String> words = new ArrayList<String>();
		InputStream input = NameGenerator.class.getResourceAsStream(filename);
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
		    
	    return words;
	}
}
