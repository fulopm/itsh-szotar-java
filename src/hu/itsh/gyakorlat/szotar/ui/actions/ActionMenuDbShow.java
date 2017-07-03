package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.dictionaries.Dictionary;
import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowDbTable;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowHelpAbout;

public class ActionMenuDbShow extends AbstractAction {

	MainContentPane parent;
	public static WindowDbTable window;

	public ActionMenuDbShow(MainContentPane contentPane) {
		super("Adatbázis megjelenítése");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Database.isNull()) {
			UIUtil.showErrorDialog("Az adatbazis nincs betoltve!\nKattintson a Fajl->Adatbazis megnyitasa gombra.");
			return;
		}
		if (window == null) {
			window = new WindowDbTable();
			parent.add(window);
			parent.cascade();
		} else {
			window.dispose();
			parent.remove(window);
			window = new WindowDbTable();
			parent.add(window);
			parent.cascade();
		}

	}

}
