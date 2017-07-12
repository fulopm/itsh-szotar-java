package hu.itsh.gyakorlat.szotar.dictionaries;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hu.itsh.gyakorlat.szotar.ui.UIUtil;


public class OnlineDictionary
{
	public static final int ENGLISH = 1;
	public static final int HUNGARIAN = 2;
	
	public ArrayList<String> meanings;
	public boolean isEmpty;
	private String sourceWord;
	private String wordClass;
	
	public ArrayList<OnlineWord> words;
	
    
    public OnlineDictionary(String word, int destLanguage) throws IOException
    {
    	words = new ArrayList<>();
    	
        Document doc = Jsoup.connect("http://en.bab.la/dictionary/" + (destLanguage == 1 ? "english-hungarian" : "hungarian-english") + "/" + word).get();
       // Elements content = doc.getElementsByClass("quick-results container");
        Element content = doc.select("div.quick-results.container").first(); // DO NOT TOUCH
        Elements quickResultOption = content.select("div.quick-result-option"); // DO NOT TOUCH 2
        Elements liS = content.select("div.quick-result-overview ul.sense-group-results");
        
        meanings = new ArrayList<>();
        int j = 0;
        for (Element classes : quickResultOption)
        {
        	try
        	{
        		sourceWord = "";
        		
        		for (int i = 0; i < classes.text().split("\\s+").length; i++)
        		{
        			if (i == classes.text().split("\\s+").length-1)
        			{
        	        	wordClass = classes.text().split("\\s+")[i];
        			}
        			else
        				sourceWord += " " +classes.text().split("\\s+")[i];
        		}
        		
	        	for (Element wd : liS.get(j).select("li"))
	        	{
	        		if (!wd.text().equals(""))
	        		{
	        			meanings.add(wd.text());
	        		}
	        		
	        	}       	
	        	words.add(new OnlineWord(sourceWord, wordClass, meanings.toArray()));
	        	meanings.clear();
	        	j++;
        	}
        	catch (Exception e)
        	{
        		isEmpty = true;
        	}
        	
        }

    	
    }
	
	private void translate()
	{
	}
	
	private void translate2()
	{
		
	}
}



