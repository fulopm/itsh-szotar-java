package hu.itsh.gyakorlat.szotar.spell;

import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.PopupListener;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;

public class SpellChecking {

	private JPopupMenu popup;
	
	public SpellChecking() {
		
		//file location of dictionary
		String userDictionaryPath = "/hu/itsh/gyakorlat/szotar/spell/";
				
		//SET DICTIONARY PROVIDER FROM DICTIONARY PATH
		SpellChecker.setUserDictionaryProvider(new FileUserDictionary(userDictionaryPath));
				
		//REGISTER DICTIONARY
		SpellChecker.registerDictionaries(getClass().getResource(userDictionaryPath), "en");
		
		SpellCheckerOptions sco = new SpellCheckerOptions();
		sco.setCaseSensitive(false);
		sco.setSuggestionsLimitMenu(10);
		sco.setLanguageDisableVisible(false);
		sco.setIgnoreAllCapsWords(true);
		this.popup = SpellChecker.createCheckerPopup(sco);

		
	}

	public void check(JTextComponent text) {
		SpellChecker.register(text);
		text.addMouseListener(new PopupListener(this.popup));
	}
}
