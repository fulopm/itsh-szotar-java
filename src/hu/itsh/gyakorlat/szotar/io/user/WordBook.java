package hu.itsh.gyakorlat.szotar.io.user;

import java.util.ArrayList;

public class WordBook
{
	private String[] allWords;
	private ArrayList<String> sortedWords = new ArrayList<>();
	
	public WordBook(String text)
	{
		this.allWords = text.split("\\s+");
	}
	
	public ArrayList<String> getSortedWords()
	{
		for (String word : allWords)
		{
			if (sortedWords.isEmpty() || !sortedWords.contains(word))
				sortedWords.add(word.replaceAll("[-+.^:,?!]", ""));
		}
		sortedWords.sort(String.CASE_INSENSITIVE_ORDER);
		
		for (String word : sortedWords)
		{
			System.out.println("szo: " + word);
		}
		
		return sortedWords;
	}
	
	public String[] getAllWords()
	{
		return this.allWords;
	}
}
