package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.io.stats.StatisticsReader;
import hu.itsh.gyakorlat.szotar.statistics.Statistics;
import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowStatistics;

public class ActionMenuStatistics extends AbstractAction{
	MainContentPane panel;
	
	public ActionMenuStatistics(MainContentPane parent) {
		super("Statisztika");
		panel = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Statistics statType, statSelection;
		statType = new Statistics(StatisticsReader.readTypeStatistics());
		statSelection = new Statistics(StatisticsReader.readSelectionStatistics());
		
		
		WindowStatistics ws = new WindowStatistics(statType, statSelection, "Statisztika");
		panel.addInternalWindow(ws);
	}

}
