package hu.itsh.gyakorlat.szotar.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.TableRowSorter;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.ds.DatabaseTableModel;

public class WindowDbTable extends InternalWindow {

	JTable tableData;
	DatabaseTableModel tableModel;
	TableRowSorter tableRowSorter;
	
	JTextField fieldSearch;

	public WindowDbTable() {
		super(SharedConstants.APP_NAME + " (-) Adatbázis", new BorderLayout());
		initComponents();

		contentPane.add(new JScrollPane(tableData), BorderLayout.CENTER);
		contentPane.add(fieldSearch, BorderLayout.SOUTH);
		pack();
		setVisible(true);

	}

	void initComponents() {

		fieldSearch = new JTextField();
		fieldSearch.setToolTipText("Kereseshez kedjen el geppelni!");

		tableModel = new DatabaseTableModel(Database.dict);
		tableRowSorter = new TableRowSorter<DatabaseTableModel>(tableModel);
		
		tableData = new JTable(tableModel);
		tableData.setRowSorter(tableRowSorter);
		fieldSearch.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				updateTableFilter();
			}
		});

	}
	
	private void updateTableFilter() {
		RowFilter<String, String> rf = null;
		try {
			rf = new RowFilter<String, String>() {

				@Override
				public boolean include(javax.swing.RowFilter.Entry<? extends String, ? extends String> entry) {
					return entry.getStringValue(3).startsWith(fieldSearch.getText().toLowerCase());

				}

			};
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		tableRowSorter.setRowFilter(rf);
	}
}
