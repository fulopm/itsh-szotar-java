package hu.itsh.gyakorlat.szotar.games;

import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.windows.InternalWindow;

public class Hanger 
{
	String word;
	String modifiedWord;
	private final String WORD_SEPARATOR = "     ";
	
	public Hanger(String word)
	{
		this.word = word;
		StringBuilder modifiedStringBuilder = new StringBuilder();
		for (char c : toCharArray())
		{
			if (c == ' ')
				modifiedStringBuilder.append(this.WORD_SEPARATOR);
			else
				modifiedStringBuilder.append(" ").append(c);
				
		}
		modifiedWord = modifiedStringBuilder.toString();
	}
	
	public char[] toCharArray()
	{
		return word.toCharArray();		
	}
	
	public int getWordLength()
	{
		return word.length();
	}
	
	public String toHidden()
	{
		StringBuilder hiddenStringBuilder = new StringBuilder();
		for (int i = 0; i < getWordLength(); i++)
		{
			if (word.charAt(i) == ' ')
				hiddenStringBuilder.append(WORD_SEPARATOR);
			else
				hiddenStringBuilder.append(" _");
		}
		return  hiddenStringBuilder.toString();
	}
	
	public String checkAndUpdateWord(String status, char inp)
	{
			char[] wd = status.toCharArray();
			System.out.println("DEBUG [" + modifiedWord + "]");
			System.out.println("DEBUG [" + String.valueOf(wd) + "]");
			
			for (int i = 0; i < status.length(); i++)
			{
				if (inp == modifiedWord.charAt(i))
					wd[i] = inp;
					
			}
			  if (status.toCharArray() == wd)
			  {
				UIUtil.showInformationDialog("Kitalaltad, gratulalok!");
			  }
		return String.valueOf(wd);
		
	}
	
	public boolean checkWord(String status)
	{
		boolean isGuessed = true;
		for (char c : status.toCharArray())
		{
			if (c == '_')
				isGuessed = false;
		}	
		return isGuessed;
	}
}
