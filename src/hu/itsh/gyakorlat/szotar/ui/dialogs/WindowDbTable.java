package hu.itsh.gyakorlat.szotar.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class WindowDbTable extends InternalWindow{

	private JTable tableData;
	private JTextField fieldSearch;
	private JList<String> listHits;
	private String[] arrayHits;
	private JButton buttonSearch;
	TableRowSorter sorter;
	
	public WindowDbTable() {
		super(SharedConstants.APP_NAME + " (-) Adatbázis", new BorderLayout());
		initComponents();
		
		contentPane.add(new JScrollPane(tableData), BorderLayout.CENTER);
		contentPane.add(fieldSearch, BorderLayout.SOUTH);
		contentPane.add(listHits, BorderLayout.EAST);
		contentPane.add(listHits, BorderLayout.EAST);
		pack();
		setVisible(true);
		tableData.repaint();

	}
	
	
	void initComponents() {
		
		
		
		fieldSearch = new JTextField();
		listHits = new JList<>();
		
		arrayHits = new String[5];
		fieldSearch.setToolTipText("Keresés");
		
		
		
		
		 RowFilter<DatabaseTableModel, Object> rf = null;
		    //If current expression doesn't parse, don't update.
		    try {
		        rf = RowFilter.regexFilter(fieldSearch.getText(), 0);
		    } catch (java.util.regex.PatternSyntaxException e) {
		        return;
		    }
		   
		   DatabaseTableModel model = new DatabaseTableModel(Database.dict);
		   sorter = new TableRowSorter<DatabaseTableModel>(model);
		    tableData = new JTable(model);
			tableData.setRowSorter(sorter);
		fieldSearch.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				newFilter();
				/*
				 	String compareString = fieldSearch.getText()+e.getKeyChar();
		            int counter = 0;
		            for (int i = 0 ; i <Database.dict.getRowCount() ; i++){
		                if (counter < 5){
		                	String hit = Database.dict.getRow(i).getWord();
		                	if (compareString.length() > hit.length()) {
		                		compareString = compareString.substring(0, hit.length());
		                		
		                	}
		                	
		                    if (hit.substring(0, compareString.length()).equals(compareString)){
		                        arrayHits[counter] = hit;
		                        counter++;
		                    }
		                    listHits.setListData(arrayHits);
		                }
		            }
		           */
				
				
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
		
		buttonSearch = new JButton("Keres");
		buttonSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UIUtil.showInformationDialog(Integer.toString(Database.dict.searchByWord(fieldSearch.getText())));
				
			}
		});
	}
	
	private void newFilter() {
	    RowFilter<String, String> rf = null;
	    //If current expression doesn't parse, don't update.
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
	    sorter.setRowFilter(rf);
	}
}
