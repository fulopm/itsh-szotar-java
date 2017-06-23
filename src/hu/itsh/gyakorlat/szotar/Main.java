package hu.itsh.gyakorlat.szotar;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URISyntaxException;

import hu.itsh.gyakorlat.szotar.szotarak.OnlineSzotar;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {
		

		// 	SAJÁT FELHASZNÁLÓ + JELSZÓ INTERNET HOZZÁFÉRÉSHEZ
		setSystemProxy("qhub03.hu.t-internal.com", 3128, "user", "password");
		
		String s = OnlineSzotar().translate("do", OnlineSzotar.HUNGARIAN)
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
