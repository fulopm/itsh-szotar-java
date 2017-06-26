package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.PleaseWaitDialog;
import hu.itsh.gyakorlat.szotar.ui.dialogs.DialogHelpAbout;

public class ActionMenuDbOpen extends AbstractAction {

	MainContentPane parent;

	public ActionMenuDbOpen(MainContentPane contentPane) {
		super("Adatbazis megnyitasa");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		PleaseWaitDialog dialog = new PleaseWaitDialog(parent, "");
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {

				XSSFWorkbook wb = Database.loadWorkbook("NGP.xlsx");
				Database.loadDictionary(wb);
				return null;

			}
		};

		mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("state")) {
					if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
						dialog.dispose();
					}
				}
			}
		});

		mySwingWorker.execute();
		dialog.setVisible(true);

	}

}
