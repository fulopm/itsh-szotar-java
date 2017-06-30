package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.dialogs.WindowDbEditRow;
import hu.itsh.gyakorlat.szotar.ui.dialogs.WindowDbTable;

public class ActionSaveRowState extends AbstractAction {

	Row row;
	
	public ActionSaveRowState(Row newState) {
		super("Mentés");
		if (newState == null)
			return;
		this.row = newState;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!Database.dict.containsRow(row))
			throw new IllegalStateException("Row doesn't exists!, ROW ID: " + row.getId());
		else if (UIUtil.showYesNoDialog("Biztosan menti a jelenleg megadott adatokkal a sort?")) {
			int index = Database.dict.getRowIndex(row);
			System.out.println(index);
			Database.dict.setRow(index, row);
			Database.dict.sort();
			ActionMenuDbShow.window.getTableModel().updateBackend();
		}
	}

}
