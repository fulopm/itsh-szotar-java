package hu.itsh.gyakorlat.szotar.ui.ds;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import hu.itsh.gyakorlat.szotar.statistics.Statistics;

public class StatisticsTableModel extends AbstractTableModel {

	private String columns[] = { "Teszt", "Helyes", "Hibás", "Összes", "Százalék" };

	private Statistics beirasos, valasztasos;

	public StatisticsTableModel(Statistics typeStat, Statistics selectionStat) {
		this.beirasos = typeStat;
		this.valasztasos = selectionStat;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			if (rowIndex == 0) {
				return "Beírásos";
			} else {
				return "Választós";
			}
		case 1:
			if (rowIndex == 0) {
				return beirasos.getHelyes();
			} else {
				return valasztasos.getHelyes();
			}
		case 2:
			if (rowIndex == 0) {
				return beirasos.getHelytelen();
			} else {
				return valasztasos.getHelytelen();
			}
		case 3:
			if (rowIndex == 0) {
				return beirasos.getOsszes();
			} else {
				return valasztasos.getOsszes();
			}

		case 4:
			if (rowIndex == 0) {
				return beirasos.getSzazalek() + "%";
			} else {
				return valasztasos.getSzazalek() + "%";
			}
		default:
			return "HIBA";
		}

	}
	
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}
	
	public void reset() {
		beirasos.reset();
		valasztasos.reset();
	}

}
