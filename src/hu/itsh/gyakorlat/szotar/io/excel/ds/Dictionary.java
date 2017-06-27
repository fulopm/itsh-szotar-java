package hu.itsh.gyakorlat.szotar.io.excel.ds;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	private List<Row> rows;
	
	public Dictionary(List<Row> rows) {
		this.rows = rows;
	}
	
	public Dictionary() {
		this.rows = new ArrayList<>();
	}
	
	public boolean addRow(Row e) {
		return this.rows.add(e);
	}
	
	public Row removeRow(int index) {
		return this.rows.remove(index);
	}
	
	public Row getRow(int index) {
		return this.rows.get(index);
	}
	
	
	public boolean containsRow(Row e) {
		return this.rows.contains(e);
	}
	
	public int getRowCount() {
		return rows.size();
	}
	
	
}
