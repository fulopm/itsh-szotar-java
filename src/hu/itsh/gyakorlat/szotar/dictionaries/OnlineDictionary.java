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
	private String sourceWord;
	private String wordClass;
	
	public ArrayList<OnlineWord> words;
	
    
    public OnlineDictionary(String word, int destLanguage) throws IOException
    {
    	words = new ArrayList<>();
    	
    	System.out.println("TEST");
        Document doc = Jsoup.connect("http://en.bab.la/dictionary/" + (destLanguage == 1 ? "english-hungarian" : "hungarian-english") + "/" + word).get();
       // Elements content = doc.getElementsByClass("quick-results container");
        Element content = doc.select("div.quick-results.container").first(); // DO NOT TOUCH
        Elements quickResultOption = content.select("div.quick-result-option"); // DO NOT TOUCH 2
        Elements liS = content.select("div.quick-result-overview ul.sense-group-results");
        
        meanings = new ArrayList<>();
        int i = 0;
        for (Element classes : quickResultOption)
        {
        	try
        	{
	        	sourceWord = classes.text().split("\\s+")[0];
	        	wordClass = classes.text().split("\\s+")[1];
	        	for (Element wd : liS.get(i).select("li"))
	        	{
	        		if (!wd.text().equals(""))
	        		{
	        			meanings.add(wd.text());
	        		}
	        		
	        	}       	words.add(new OnlineWord(sourceWord, wordClass, meanings.toArray()));
	        	meanings.clear();
	        	i++;
        	}
        	catch (Exception e)
        	{
        		UIUtil.showErrorDialog("Nem talaltam semmit online!");;
        	}
        	
        }
        for (OnlineWord wd : words)
        {
			System.out.println("szo: "+wd.getWordClass()+"\nszofaj: "+wd.getSourceWord()+"\nwords: " + wd.meaningsToString() + "\n----------------------------------");
        }

    	
    }
	
	private void translate()
	{
	}
	
	private void translate2()
	{
		
	}
}



