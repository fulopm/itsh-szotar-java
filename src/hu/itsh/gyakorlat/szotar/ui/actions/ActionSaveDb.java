package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ExcelBase;
import hu.itsh.gyakorlat.szotar.io.excel.SaveActions;
import hu.itsh.gyakorlat.szotar.ui.PleaseWaitDialog;

public class ActionSaveDb extends AbstractAction {

	
	public ActionSaveDb() {
		super("Adatbázis mentése");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
			System.out.println("a");
//			Database.dict.recalculateIDs();
//			ActionMenuDbShow.window.getTableModel().fireTableDataChanged();
//			ActionMenuDbShow.window.getTable().repaint();
//			
			PleaseWaitDialog dialog  = new PleaseWaitDialog();
			SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){

				@Override
				protected Void doInBackground() throws Exception {
					System.out.println("Mentés elkezdése...");
					SaveActions sa = new SaveActions();
					sa.fullSave();
					return null;
				}
				
			};
			
			mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if (evt.getPropertyName().equals("state")) {
						if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
							System.out.println("Mentés vége...");
							dialog.dispose();
						}
					}
				}
			});
			
			mySwingWorker.execute();
			dialog.setVisible(true);
			

			
		
			

			/*
			 * TODO mentes excelbe
			 */
			
			
	}

}
