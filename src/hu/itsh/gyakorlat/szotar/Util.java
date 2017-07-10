package hu.itsh.gyakorlat.szotar;

import java.io.File;
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
	
	public static String getExtension(File f){
		
		String ext = null;
		String fileName = f.getName();
		int dotInd = fileName.lastIndexOf('.');
		
		if(dotInd > 0 && dotInd < fileName.length() -1){
			ext = fileName.substring(dotInd+1).toLowerCase();
		}
		
		return ext;
		
	}
	
	public static char lastOne(String string) {
		if (string.length() == 0) return '\0';
		return string.charAt(string.length()-1);
	}
	
	
	public static String lastTwo(String string) {
		if (string.length() == 0) return "\0";
		return string.substring(string.length()-2, string.length());
	}
	
	public static String trimStr(String word){
		char currentChar;
		int firstInd = -1;
		int lastInd = -1;
		for (int i = 0; i < word.length()-1; i++) {
			currentChar = word.charAt(i);
			if(currentChar == ' ' && firstInd == -1){
				continue;
			}else if(currentChar != ' ' && firstInd == -1){
				firstInd = i;
			}
			if(currentChar == ' ' && word.charAt(i+1) == ' '){
				lastInd = i-1;
			}
			if(i == word.length()-2 && lastInd == -1 && currentChar != ' '){
				lastInd = word.length();
				}
		}
		return word.substring(firstInd, lastInd);
	}
	
}
 