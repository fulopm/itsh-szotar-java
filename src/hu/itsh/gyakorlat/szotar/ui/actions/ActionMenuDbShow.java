package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.dialogs.WindowDbTable;
import hu.itsh.gyakorlat.szotar.ui.dialogs.WindowHelpAbout;

public class ActionMenuDbShow extends AbstractAction{
	
	MainContentPane parent;
	
	
	public ActionMenuDbShow(MainContentPane contentPane) {
		super("Adatbázis megjelenítése");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		parent.add(new WindowDbTable());
		
	}

}
