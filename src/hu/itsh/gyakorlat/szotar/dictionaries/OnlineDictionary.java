package hu.itsh.gyakorlat.szotar.dictionaries;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class OnlineDictionary
{
	public static final int ENGLISH = 1;
	public static final int HUNGARIAN = 2;
	
	public ArrayList<String> meanings;
	
	public static Elements translate(String word, int destLanguage) throws IOException
	{
        Document doc = Jsoup.connect("http://en.bab.la/dictionary/" + (destLanguage == 1 ? "english-hungarian" : "hungarian-english") + "/" + word).get();
        Elements quickResultOption = doc.getElementsByClass("quick-result-entry");
        Elements quickResultEntry = doc.getElementsByClass("quick-result-option");
        
        ArrayList<String> eng_words = new ArrayList<>();
        
        for (Element classes : quickResultOption)
        {
        	if (!classes.text().contains("HU") && !classes.text().contains("EN") && !classes.text().contains("{"))
        		eng_words.add(classes.text());
        }
        return quickResultOption;
	}	
}



