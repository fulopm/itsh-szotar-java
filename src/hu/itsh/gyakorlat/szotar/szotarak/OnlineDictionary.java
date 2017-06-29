package hu.itsh.gyakorlat.szotar.szotarak;

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
	
	public static ArrayList<String> translate(String word, int destLanguage) throws IOException
	{
        
		
		
        Document doc = Jsoup.connect("http://en.bab.la/dictionary/" + (destLanguage == 1 ? "english-hungarian" : "hungarian-english") + "/" + word).get();
        Elements res = doc.getElementsByClass("quick-result-entry");
        
        ArrayList<String> div = new ArrayList<>();
        
        for (Element classes : res)
        {
        	if (classes.text().contains("EN") || classes.text().contains("HU"))
        		div.add(classes.text());
        }
        
        // Lista szintaxisa: ANGOL KIFEJEZ�S + NYELV (HU/EN) + JELENT�SEK
        ArrayList<String> words = new ArrayList<>();
       
        String[] line;
        for (int i = 0; i < div.size(); i++)
        {
        	line = div.get(i).split("\\s+");
        	for (int j = 0; j < line.length; j++)
        	{
        		if (!line[j].contains("{"))
        		{
        			words.add(line[j]);
        		}
        	}
        }
        return words;
        
        
	}
	
}
