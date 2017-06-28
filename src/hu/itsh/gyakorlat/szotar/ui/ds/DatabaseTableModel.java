package hu.itsh.gyakorlat.szotar.ui.ds;

import javax.swing.table.AbstractTableModel;

import hu.itsh.gyakorlat.szotar.io.excel.ds.Dictionary;
import static hu.itsh.gyakorlat.szotar.SharedConstants.TABLE_HEADERS;

public class DatabaseTableModel extends AbstractTableModel {

	private Dictionary dict;

	public DatabaseTableModel(Dictionary dict) {
		this.dict = dict;
	}

	@Override
	public int getColumnCount() {
		return TABLE_HEADERS.length;
	}

	@Override
	public int getRowCount() {
		return dict.getRowCount();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return dict.getRow(row).getId();
		case 1:
			return dict.getRow(row).getTimestamp();
		case 2:
			return dict.getRow(row).getPrefix();
		case 3:
			return dict.getRow(row).getWord();
		case 4:
			return dict.getRow(row).getSuffix();
		case 5:
			return dict.getRow(row).getEngExplain();
		case 6:
			return dict.getRow(row).getEngExample();
		case 7:
			return dict.getRow(row).getHun0();
		case 8:
			return dict.getRow(row).getHun1();
		case 9:
			return dict.getRow(row).getHunExplain();
		case 10:
			return dict.getRow(row).getHunExample();
		case 11:
			return dict.getRow(row).getLevel();
		case 12:
			return dict.getRow(row).getLang();
		case 13:
			return dict.getRow(row).getWordClass();
		case 14:
			return dict.getRow(row).getForm0();
		case 15:
			return dict.getRow(row).getForm1();
		case 16:
			return dict.getRow(row).getForm2();
		case 17:
			return dict.getRow(row).getForm3();
		default:
			throw new IllegalStateException("Bad column number");
		}

	}

	@Override
	public String getColumnName(int column) {

		return TABLE_HEADERS[column];

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
