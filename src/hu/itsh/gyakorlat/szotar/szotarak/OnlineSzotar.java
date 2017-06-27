package hu.itsh.gyakorlat.szotar.szotarak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class OnlineSzotar
{
	public static final int ENGLISH = 1;
	public static final int HUNGARIAN = 2;
	
	private static String raw_html;
	public static ArrayList<String> meanings;
	
	public static String translate(String word, int destLanguage) throws IOException
	{
		raw_html = "";
		URL url = new URL("http://en.bab.la/dictionary/" + (destLanguage == 1 ? "english-hungarian" : "hungarian-english") + "/" + word);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String l;
        while ((l=in.readLine())!=null) {
            raw_html += l;
        }
        
        // NULLPOINTER EXCEPTION 
        Document hFile = Jsoup.parse(raw_html, "UTF-8");
        Elements res = hFile.select("div.quick-result-entry");
        //return meanings.add(res.get(0).text());
        //meanings.add(res.get(1).text());
        //meanings.add(res.get(2).text());
        //return meanings;
        return null;
        
        
	}
	
	public static ArrayList<String> splitMeanings()
	{
		
		
		return null;
		
	}
	
}
