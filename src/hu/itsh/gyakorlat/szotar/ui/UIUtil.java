package hu.itsh.gyakorlat.szotar.ui;

import javax.swing.JOptionPane;

public class UIUtil {

	
	
	public static void showErrorDialog(String text) {
		JOptionPane.showMessageDialog(null, text, "Hiba!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showInformationDialog(String text) {
		JOptionPane.showMessageDialog(null, text, "Információ!", JOptionPane.INFORMATION_MESSAGE);
	}
}
