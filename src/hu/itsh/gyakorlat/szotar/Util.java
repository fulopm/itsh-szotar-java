package hu.itsh.gyakorlat.szotar;

import java.util.regex.Pattern;

public class Util {

	
	
	private Util() {

	}

	public static String escape(String expression) {
		if (expression.length() == 0) return "";
		expression = SharedConstants.REGEX_SPECIAL_CHARS.matcher(expression).replaceAll("_");
		return expression;
	}
	
	public static int parseInt(String expression) {
		
		try {
			return Integer.parseInt(expression);
		} catch (NumberFormatException x) {
			return -1;
		}
		
	}
	
}
 