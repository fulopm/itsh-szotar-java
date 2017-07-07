package hu.itsh.gyakorlat.szotar;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class SharedConstants {
	public static final String APP_NAME = "NGP";
	public static final String APP_VERSION = "v0.5";
	public static String[] APP_DEVS = { "Fulop Mark", "Beviz Mark", "Peter Benjamin", "Sovago David", "Pusztai Csaba",
			"Gaal Csaba", "Major Csaba" };

	public static final String SEPARATOR_WAVE = "~";
	public static final String SEPARATOR_DOLLAR = "$";
	public static final String FORMAT_YYMMDD = "yyyy-MM-dd";

	public static final String[] wordClasses = { "névelö", "rövidítés", "mondat", "vt", "kifejezés", "fönév",
			"melléknév", "elöljáró", "határozó", "definició", "vi-sz", "vt-sz", "vt/vi", "kötöszó", "névmás",
			"vi/vt-rh", "vt-rh", "vi-rh", "vi", "vx", "vi/vt-sz", "számnév", "vx-rh"

	};

	public static final DateTimeFormatter DTF_YYMMDD = DateTimeFormatter.ofPattern("uuuu-MM-dd");

	
	public static Pattern REGEX_SPECIAL_CHARS = Pattern.compile("\\`|\\~|\\@|\\#|\\$|\\^|\\&|\\*|\\'|\\<|\\>|\\\"");
	
	
	public static final String FILENAME_NGP = "NGP.xlsx";
	public static final String FILENAME_TEMP_CSV = "table.csv";
	
	
}
