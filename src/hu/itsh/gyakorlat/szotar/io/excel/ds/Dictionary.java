package hu.itsh.gyakorlat.szotar.io.excel.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

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

	public boolean isEmpty() {
		return rows == null || rows.size() == 0;

	}

	/*
	 * Sorts the List based on the toCompare() method defined in Row class
	 * FM
	 */
	public void sort() {
		Collections.sort(rows);
	}

	public Iterator<Row> getRowIterator() {
		return rows.iterator();
	}

	/*
	 * Looks for a Row which has exactly the same "word" field as the parameter
	 * If found, returns the index of the row
	 * If not found, returns < 0
	 * FM
	 */
	public int searchByWord(String word) {

		Comparator<Row> c = new Comparator<Row>() {

			@Override
			public int compare(Row o1, Row o2) {
				return o1.getWord().compareToIgnoreCase(o2.getWord());
			}

		};
		Row r = new Row();
		r.setWord(word);
		return Collections.binarySearch(rows, r, c);

	}

}
