package hu.itsh.gyakorlat.szotar;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;

public class SharedConstants {
	public static final String APP_NAME = "NGP";
	public static final String APP_VERSION = "v0.5";
	public static String[] APP_DEVS = { "Fulop Mark", "Beviz Mark", "Peter Benjamin", "Sovago David", "Pusztai Csaba",
			"Gaal Csaba" };

	public static final String SEPARATOR = "~";
	public static final SimpleDateFormat FORMAT_YYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	public static final String[] wordClasses = { "névelö", "rövidítés", "mondat", "vt", "kifejezés", "fönév",
			"melléknév", "elöljáró", "határozó", "definició", "vi-sz", "vt-sz", "vt/vi", "kötöszó", "névmás",
			"vi/vt-rh", "vt-rh", "vi-rh", "vi", "vx", "vi/vt-sz", "számnév", "vx-rh"

	};

	public static final DateTimeFormatter DTF_YYMMDD = DateTimeFormatter.ofPattern("uuuu-MM-dd");

}
