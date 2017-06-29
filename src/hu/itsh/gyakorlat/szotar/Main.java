package hu.itsh.gyakorlat.szotar;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URISyntaxException;
import java.util.ArrayList;

import hu.itsh.gyakorlat.szotar.szotarak.OnlineDictionary;
import hu.itsh.gyakorlat.szotar.ui.MainFrame;
import hu.itsh.gyakorlat.szotar.szotarak.OnlineDictionary;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {
		
   MainFrame frame = new MainFrame();
	 frame.setVisible(true);
		// 	ITSH PROXY HOST + USER + PW TO CONNECT TO PROGRAM ARGUMENTS NEEDED 
		//setSystemProxy(args[0], Integer.parseInt(args[1]), args[2], args[3]);
		
	}
	
	public static void setSystemProxy(String proxyAddress, int proxyPort,
            String user, String password) 
	{
		final String authUser = user;
		final String authPassword = password;
		
		System.setProperty("proxySet", "true");
		System.setProperty("ProxyHost", proxyAddress);
		System.setProperty("ProxyPort", String.valueOf(proxyPort));
		
		System.setProperty("http.proxyHost", proxyAddress);
		System.setProperty("http.proxyPort", String.valueOf(proxyPort));
		
		System.setProperty("http.proxyHost", proxyAddress);
		System.setProperty("http.proxyPort", String.valueOf(proxyPort));
		
		System.setProperty("http.proxyUser", authUser);
		System.setProperty("http.proxyPassword", authPassword);
		
		Authenticator.setDefault(
		new Authenticator() {
		 @Override
		 public PasswordAuthentication getPasswordAuthentication() {
		     return new PasswordAuthentication(authUser, authPassword.toCharArray());
		 }
		});
	}

}
