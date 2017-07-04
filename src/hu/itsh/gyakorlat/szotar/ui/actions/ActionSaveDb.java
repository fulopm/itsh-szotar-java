package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.ui.windows.InternalWindow;

public class ActionSaveDb extends AbstractAction {

	
	public ActionSaveDb(Row newState) {
		super("Adatbázis mentése");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
			Database.dict.recalculateIDs();
			ActionMenuDbShow.window.getTableModel().fireTableDataChanged();
			ActionMenuDbShow.window.getTable().repaint();
			

			/*
			 * TODO mentes excelbe
			 */
	}

}
