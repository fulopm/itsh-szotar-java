package hu.itsh.gyakorlat.szotar.dictionaries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;

public class Dictionary {

	private List<Row> rows;
	
	public Dictionary(List<Row> rows) {
		this.rows = rows;
	}

	public Dictionary() {
		this.rows = new ArrayList<>();
	}
	
	public void clear(){
		this.rows.clear();
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

	public boolean isEmpty() {
		return rows == null || rows.size() == 0;

	}

	public Row searchByID(int id) {
		for (Row row : rows)
			if (row.getId() == id)
				return row;

		return null;

	}

	public Row searchByWord(String word) {

		Comparator<Row> c = new Comparator<Row>() {

			@Override
			public int compare(Row o1, Row o2) {
				return o1.getWord().compareToIgnoreCase(o2.getWord());
			}

		};
		Row r = new Row();
		r.setWord(word);
		int hit = Collections.binarySearch(rows, r, c);

		return hit > -1 ? getRow(hit) : null;

	}

	public void setRow(int index, Row newRow) {
		rows.set(index, newRow);
	}

	public int getRowIndex(Row e) {
		if (!rows.contains(e))
			return -1;

		return rows.indexOf(e);
	}

	public void sort() {
		Collections.sort(rows);
	}

	Iterator<Row> getRowIterator() {
		return rows.iterator();
	}

	public void recalculateIDs() {
		int currentID = 1;
		for (Row r : rows) {
			r.setId(currentID);
			currentID++;
		}
	}

}
