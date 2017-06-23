package hu.itsh.gyakorlat.szotar.szotarak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map.Entry;

public class OnlineSzotar
{
	private URI hunToEng;
	private URI engToHun;
	
	private String hunWord;
	private String engWord;
	
	public OnlineSzotar() throws URISyntaxException
	{
		hunToEng = new URI("http://en.bab.la/dictionary/hungarian-english/");
		engToHun = new URI("http://en.bab.la/dictionary/english-hungarian/");
		
		
	}
	
	public String translateToEnglish(String hunWord) throws IOException
	{
		URL url = new URL("http://en.bab.la/dictionary/hungarian-english/valami");
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String l;
        while ((l=in.readLine())!=null) {
            System.out.println(l);
        }

        return null;
        
        
	}
	
	public String translateToHun(String engWord)
	{
		return "";
	}
	
	
}
