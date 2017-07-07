package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import hu.itsh.gyakorlat.szotar.io.stats.StatisticsReader;
import hu.itsh.gyakorlat.szotar.statistics.Statistics;
import hu.itsh.gyakorlat.szotar.ui.ds.StatisticsTableModel;

public class WindowStatistics extends InternalWindow implements ActionListener {

	private Font tableFont = new Font("Ariel", Font.BOLD, 15);

	private JTable dataTable;

	private JButton buttonReset;
	StatisticsTableModel tableModel;
	Statistics statType, statSelection;
	public WindowStatistics(Statistics statType, Statistics statSelection, String title) {
		super(title, new BorderLayout());
		this.statType = statType;
		this.statSelection = statSelection;
		initComponents();

		contentPane.add(dataTable, BorderLayout.CENTER);
		JTableHeader header = dataTable.getTableHeader();
		header.setFont(tableFont);

		contentPane.add(header, BorderLayout.NORTH);

		contentPane.add(this.buttonReset, BorderLayout.SOUTH);
		this.buttonReset.addActionListener(this);

		this.setPreferredSize(new Dimension(500, 130));
		this.setMaximumSize(new Dimension(500, 130));
		this.setMinimumSize(new Dimension(500, 130));
		pack();
		setResizable(false);
		setVisible(true);
		repaint();
	}

	private void initComponents() {

		tableModel = new StatisticsTableModel(statType, statSelection);
		dataTable = new JTable(tableModel);

		this.buttonReset = new JButton("Statisztikai adatok nullazasa");
		this.buttonReset.setSize(400, 30);
	}

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(this.buttonReset)) {
			StatisticsReader.reset();
			tableModel.reset();
			tableModel.fireTableDataChanged();
			dataTable.repaint();
		}

	}

}
