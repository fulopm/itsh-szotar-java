package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.inet.jortho.Utils;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.PleaseWaitDialog;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowHelpAbout;
import javafx.stage.FileChooser;

public class ActionMenuDbOpen extends AbstractAction {

	MainContentPane parent;
	private JFileChooser dbChooser;
	public static String ngpFilePath;
	public static File f = new File("NGP.xlsx");
	
	
	public ActionMenuDbOpen(MainContentPane contentPane) {
		super("Adatbazis megnyitasa");
		parent = contentPane;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if(f.exists()){
			PleaseWaitDialog dialog = new PleaseWaitDialog();
			SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {

					try {
					XSSFWorkbook wb = Database.loadWorkbook(f.getPath());
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
		
		Database.dict.sort();
		Database.dict.recalculateIDs();
			
		}else{
			UIUtil.showErrorDialog("NGP.xlsx nem található. Kérem válassza ki a megfelelő fájlt.");

			dbChooser = new JFileChooser();
			
			FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Excel xlsx", "xlsx");
			
			dbChooser.setFileFilter(fileFilter);
			dbChooser.showOpenDialog(parent);
			
			parent.add(dbChooser);
			f = dbChooser.getSelectedFile();
			System.out.println(dbChooser.getSelectedFile().getName());
			
			parent.remove(dbChooser);
			new ActionMenuDbOpen(parent).actionPerformed(null);
		}
	

	

	}

}
