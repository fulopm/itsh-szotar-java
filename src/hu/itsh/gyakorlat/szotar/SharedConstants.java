package hu.itsh.gyakorlat.szotar;

import java.text.SimpleDateFormat;

public class SharedConstants {

	private SharedConstants() {
		throw new IllegalStateException("Utility classes should be accessed by the static way");
	}

	public static final String APP_NAME = "NGP";
	public static final String APP_VERSION = "v0.5";
	public static final String[] APP_DEVS = { "Fulop Mark", "Beviz Mark", "Peter Benjamin", "Sovago David",
			"Pusztai Csaba", "Gaal Csaba" };

	public static final String SEPARATOR = "~";

	public static final SimpleDateFormat FORMAT_YYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	public static final String TABLE_HEADERS[] = { "ID", "Timestamp", "Prefix", "Word", "Suffix", "EnglishExplaination",
			"EnglishExample", "Hungarian0", "Hungarian1", "HungarianExplaination", "HungarianExample", "Level",
			"Language", "WordClass", "form0", "form1", "form2", "form3" };

}
