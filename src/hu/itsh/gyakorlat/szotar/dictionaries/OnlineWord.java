package hu.itsh.gyakorlat.szotar.dictionaries;

import java.util.Arrays;

import hu.itsh.gyakorlat.szotar.SharedConstants;

public class OnlineWord 
{
	private String sourceWord;
	private String wordClass;
	private String[] meanings;
	
	private final String UNKNOWN_WORDCLASS = "ISMERETLEN";
	
	public OnlineWord(String source, String wordClass, Object[] meanings)
	{
		this.sourceWord = source;
		this.wordClass = wordClass;
		this.meanings =  Arrays.copyOf(meanings, meanings.length, String[].class);
		
	}
	
	public String getSourceWord()
	{
		return sourceWord;
	}
	
	public String getWordClass()
	{
		String hunWordClass = this.UNKNOWN_WORDCLASS;
		
		for (int i = 0; i < SharedConstants.onlineWordClassShortenEng.length; i++)
		{
			if (wordClass.equals(SharedConstants.onlineWordClassShortenEng[i]))
				hunWordClass = SharedConstants.onlineWordClassShortenHun[i];
		}
		
		return hunWordClass;
	}
	
	public String[] getMeanings()
	{
		return meanings;
	}
	
	public String meaningsToString()
	{
		StringBuilder sb = new StringBuilder();
		for (String meaning : meanings)
		{
			if (!meaning.equals(meanings[0]))
				sb.append(" / " + meaning);
			else
				sb.append(meaning);
		}
		
		return sb.toString();
	}
	
	public void setMeanings(String[] meanings)
	{
		this.meanings = meanings;
	}
}
