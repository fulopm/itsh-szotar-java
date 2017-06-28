package hu.itsh.gyakorlat.szotar.ui;

import javax.swing.JOptionPane;

public class UIUtil {

	private UIUtil() {
		throw new IllegalStateException("Utility classes should be accessed by the static way"); 
	}
	
	public static void showErrorDialog(String text) {
		JOptionPane.showMessageDialog(null, text, "Hiba!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showInformationDialog(String text) {
		JOptionPane.showMessageDialog(null, text, "Információ!", JOptionPane.INFORMATION_MESSAGE);
	}
}
