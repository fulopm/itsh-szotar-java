package hu.itsh.gyakorlat.szotar.ui.listeners;

import java.awt.event.MouseAdapter;
import java.util.Arrays;

import javax.swing.JTable;

import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.dialogs.InternalWindow;
import hu.itsh.gyakorlat.szotar.ui.dialogs.WindowDbEditRow;

public class DbTableMouseAdapter extends MouseAdapter {

	JTable dbTable;

	public DbTableMouseAdapter(JTable dbTable) {
		this.dbTable = dbTable;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int row = dbTable.rowAtPoint(e.getPoint());
		int column = dbTable.columnAtPoint(e.getPoint());
		if (row >= 0 && column >= 0) {
			int id = Integer.parseInt(dbTable.getValueAt(row, 0).toString());
			WindowDbEditRow editWindow = new WindowDbEditRow(Database.dict.getRowByID(id));
			if (!Arrays.asList(InternalWindow.mainContentPane.getAllFrames()).contains(editWindow)) {
				InternalWindow.mainContentPane.add(editWindow);
				InternalWindow.mainContentPane.cascade();
			}

		}

	}

}
