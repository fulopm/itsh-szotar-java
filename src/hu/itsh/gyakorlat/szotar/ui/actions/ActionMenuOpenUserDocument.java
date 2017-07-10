package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowHelpAbout;

public class ActionMenuOpenUserDocument extends AbstractAction{
	
MainContentPane parent;
	
	
	public ActionMenuOpenUserDocument(MainContentPane contentPane) {
		super("Felhasznaloi dokumentacio");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("userdoc.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (Exception ex) {
                UIUtil.showErrorDialog("A Felhasználói Dokumentáció nem található vagy\nnincs beállítva alapértelmezett PDF olvasó!");
            }
        }
		
	}
}
