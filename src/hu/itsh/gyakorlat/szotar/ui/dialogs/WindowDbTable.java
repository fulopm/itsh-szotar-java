package hu.itsh.gyakorlat.szotar.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.ui.ds.DatabaseTableModel;

public class WindowDbTable extends InternalWindow{

	private JTable tableData;
	private JTextField fieldSearch;
	private JList<String> listHits;
	private Vector<String> vectorHits;
	
	public WindowDbTable() {
		super(SharedConstants.APP_NAME + " (-) Adatbázis", new BorderLayout());
		initComponents();
		
		contentPane.add(new JScrollPane(tableData), BorderLayout.CENTER);
		contentPane.add(fieldSearch, BorderLayout.SOUTH);
		contentPane.add(listHits, BorderLayout.EAST);
		pack();
		setVisible(true);
		tableData.repaint();

	}
	
	
	void initComponents() {
		
		
		tableData = new JTable(new DatabaseTableModel(Database.dict));
		fieldSearch = new JTextField();
		listHits = new JList<>();
		
		vectorHits = new Vector<>();
		fieldSearch.setToolTipText("Keresés");
		fieldSearch.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				/*String toCompare = fieldSearch.getText()+e.getKeyChar();
				Iterator<Row> it = Database.dict.getRowIterator();
				while (it.hasNext()) {
					int found = Database.dict.searchByWord(toCompare);
					if (found != -1) {
						vectorHits.add(Database.dict.getRow(found).getWord());
					}
				}
				listHits.setListData(vectorHits);*/
				
				Database.dict.createCache();
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
