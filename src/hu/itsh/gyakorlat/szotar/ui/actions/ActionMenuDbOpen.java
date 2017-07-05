package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.PleaseWaitDialog;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowHelpAbout;

public class ActionMenuDbOpen extends AbstractAction {

	MainContentPane parent;

	public ActionMenuDbOpen(MainContentPane contentPane) {
		super("Adatbazis megnyitasa");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		File f = new File("NGP.xlsx");
		if(f.exists()){
			PleaseWaitDialog dialog = new PleaseWaitDialog();
			SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {

					try {
					XSSFWorkbook wb = Database.loadWorkbook("NGP.xlsx");
					Database.loadDictionary(wb);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					return null;

				}
			};

			mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if (evt.getPropertyName().equals("state")) {
						if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
							dialog.dispose();
							UIUtil.showInformationDialog("Az adatbazis betoltese lezajlott!");
							parent.requestFocus();
							
						}
					}
				}
			});

			mySwingWorker.execute();
			dialog.setVisible(true);
			
		}else{
			UIUtil.showErrorDialog("NGP.xlsx nem található. Kérem helyezze be a gyökér könyvtárba.");
			System.exit(0);
		}
	
	

	}

}
