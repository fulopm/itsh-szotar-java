package hu.itsh.gyakorlat.szotar.ui.actions;

import static hu.itsh.gyakorlat.szotar.Util.lastOne;
import static hu.itsh.gyakorlat.szotar.Util.lastTwo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ActionAutoFillForms extends AbstractAction {

	String word;
	String wordClass;

	JTextField wordField;
	JComboBox<String> wordClassComboBox;
	JTextField form0;
	JTextField form1;
	JTextField form2;
	JTextField form3;

	public ActionAutoFillForms(String word, String wordClass, JTextField form0, JTextField form1, JTextField form2,
			JTextField form3) {
		super("Automatikus kitoltes");
		this.word = word;
		this.wordClass = wordClass;

		this.form0 = form0;
		this.form1 = form1;
		this.form2 = form2;
		this.form3 = form3;
	}
	
	public ActionAutoFillForms(JTextField form0, JTextField form1, JTextField form2,
			JTextField form3) {
		super("Automatikus kitoltes");

		this.form0 = form0;
		this.form1 = form1;
		this.form2 = form2;
		this.form3 = form3;
	}
	
	public ActionAutoFillForms(JTextField word, JComboBox<String> wordClass, JTextField form0, JTextField form1, JTextField form2,
			JTextField form3) {
		super("Automatikus kitoltes");

		
		this.wordField = word;
		this.wordClassComboBox = wordClass;
		this.form0 = form0;
		this.form1 = form1;
		this.form2 = form2;
		this.form3 = form3;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.word = wordField.getText();
		this.wordClass =(String) wordClassComboBox.getSelectedItem();
		switch (wordClass) {
		case "főnév":
			boolean ES = lastOne(word) == 's' || lastTwo(word).equals("sh") || lastTwo(word).equals("ch")
					|| lastOne(word) == 'x' || lastOne(word) == 'z';
			if (ES) {
				form0.setText(word + "es");
				return;
			}


			boolean IES = lastOne(word) == 'y' && !lastTwo(word).equals("ay") && !lastTwo(word).equals("ey")
					&& !lastTwo(word).equals("oy");
			if (IES) {
				form0.setText(word.substring(0, word.length() - 1) + "ies");
				return;
			}

			boolean VES = lastOne(word) == 'f' || lastTwo(word).equals("fe");

			if (VES) {
				if (lastOne(word) == 'f')
					form0.setText(word.substring(0, word.length() - 1) + "ves");
				else
					form0.setText(word.substring(0, word.length() - 2) + "ves");

				return;
			}

			form0.setText(word + "s");

			break;
		case "ige":
			
			break;

		default:
			break;

		}

	}
	
	
	public void setWord(String string) {
		this.word = string;
	}
	
	
	public void setWordClass(String wordClass) {
		this.wordClass = wordClass;
	}
}
