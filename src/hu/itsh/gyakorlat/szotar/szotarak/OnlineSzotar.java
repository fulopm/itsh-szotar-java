package hu.itsh.gyakorlat.szotar.szotarak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;


public class OnlineSzotar
{
	public static final int ENGLISH = 1;
	public static final int HUNGARIAN = 2;
	
	private String raw_html = "";
	
	public OnlineSzotar() throws URISyntaxException
	{
		
		
	}
	
	public String translate(String word, int destLanguage) throws IOException
	{
		
		URL url = new URL("http://en.bab.la/dictionary/" + (destLanguage == 1 ? "english-hungarian" : "hungarian-english") + "/" + word);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String l;
        while ((l=in.readLine())!=null) {
            raw_html += l;
        }
        
        
        
        return null;
        
        
	}
	
}
