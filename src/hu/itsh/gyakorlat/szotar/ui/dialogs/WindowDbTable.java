package hu.itsh.gyakorlat.szotar.ui.dialogs;

import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.ui.ds.DatabaseTableModel;

public class WindowDbTable extends InternalWindow{

	private JTable table;

	public WindowDbTable() {
		super(SharedConstants.APP_NAME + " (-) Adatbázis", new GridLayout(2,1));
		initComponents();
		
		contentPane.add(new JScrollPane(table));
		pack();
		setVisible(true);
		table.repaint();

	}
	
	
	void initComponents() {
		
		
		table = new JTable(new DatabaseTableModel(Database.dict));
	}
}
