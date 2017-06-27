package hu.itsh.gyakorlat.szotar.io.excel.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Dictionary {
	
	private List<Row> rows;
	
	int[] indexes = new int[26];
	char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
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
	
	public void sortByWord() {
		Collections.sort(rows);
	}
	
	public Iterator<Row> getRowIterator() {
		return rows.iterator();
	}
	
	
	public void createCache() {
		indexes[0] = 0;
		for (int i = 1; i < indexes.length; i++) {
			
			int j = 0;

			for (char c : alphabet) {
				j++;
				if (rows.get(i).word.charAt(0) == c) {
					indexes[i] = j;
					break;
				}
			}
			
		}
		System.out.println(Arrays.toString(indexes));
	}
	
	public int searchByWord(String word) {
		/*Iterator<Row> it = getRowIterator();
		while (it.hasNext()) {
			Row row = it.next();
			if (row.word.startsWith(word)) {
				return row;
			}
			
		}
		*/
		Row search = new Row();
		search.setWord(word);
		
		
		Comparator<Row> c = new Comparator<Row>() {

			@Override
			public int compare(Row o1, Row o2) {
				return o1.getWord().compareTo(o2.getWord());
			}
			
		};
		return Collections.binarySearch(rows, search, c);
		
		
	}
	

	
	
}
