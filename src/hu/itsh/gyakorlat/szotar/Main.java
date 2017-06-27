package hu.itsh.gyakorlat.szotar;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URISyntaxException;
import hu.itsh.gyakorlat.szotar.szotarak.OnlineSzotar;

import hu.itsh.gyakorlat.szotar.szotarak.OnlineSzotar;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {
		

		// 	SAJ�T FELHASZN�L� + JELSZ� INTERNET HOZZ�F�R�SHEZ
		setSystemProxy(args[0], Integer.parseInt(args[1]), args[2], args[3]);
		
		String s = OnlineSzotar.translate("do", OnlineSzotar.HUNGARIAN);
		System.out.println(s);
		
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
