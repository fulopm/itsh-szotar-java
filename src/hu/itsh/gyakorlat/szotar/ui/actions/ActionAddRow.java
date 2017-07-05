package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;

public class ActionAddRow extends AbstractAction {

	Row row;

	public ActionAddRow(Row row) {
		super("H.ad");
		if (row == null)
			return;
		this.row = row;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (UIUtil.showYesNoDialog("Biztosan hozzáadja a megadott adatokkal a sort?")) {
			//int nextID = Database.dict.getRowCount() + 1;
			//row.setId(nextID);
			row.setTimestamp(SharedConstants.DTF_YYMMDD.format(LocalDate.now()));
			
			
			
			Database.dict.addRow(row);
			Database.dict.sort();
			Database.dict.recalculateIDs();
			
			
			
			ActionMenuDbShow.window.getTableModel().fireTableDataChanged();
			ActionMenuDbShow.window.getTable().repaint();

		}
	}

}
