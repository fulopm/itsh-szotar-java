package hu.itsh.gyakorlat.szotar.games;

public class Hanger 
{
	String word;
	String modified_word;
	private final String WORD_SEPARATOR = "     ";
	
	public Hanger(String word)
	{
		this.word = word;
		this.modified_word = "";
		for (char c : word.toCharArray())
		{
			if (c == ' ')
				this.modified_word += this.WORD_SEPARATOR;
			else
				this.modified_word += " " + c;
				
		}
	}
	
	public char[] toCharArray()
	{
		return word.toCharArray();		
	}
	
	public int getWordLength()
	{
		return toCharArray().length;
	}
	
	public String toHidden()
	{
		String hidden = "";
		
		for (int i = 0; i < getWordLength(); i++)
		{
			if (word.charAt(i) == ' ')
				hidden += WORD_SEPARATOR;
			else
				hidden += " _";
		}
		System.out.println("DEBUG WITH WORD SIZE " + getWordLength() + ": [" + hidden + "]");
		System.out.println("DEBUG WITH WORD SIZE " + getWordLength() + ": [" + modified_word + "]");
		return hidden;
	}
	
	public String checkAndUpdateWord(String status, char inp)
	{
		char[] wd = status.toCharArray();

			for (int i = 0; i < status.length(); i++)
			{
				if (inp == modified_word.charAt(i))
					wd[i] = inp;
					
			}
		System.out.println("DEBUG [" + String.copyValueOf(wd) + "]");
		return String.copyValueOf(wd);
		
	}
}
