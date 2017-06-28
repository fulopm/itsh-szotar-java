package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.tts.TestFrame;
import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.PleaseWaitDialog;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.dialogs.WindowHelpAbout;

public class ActionMenuTestType extends AbstractAction {

	MainContentPane parent;

	public ActionMenuTestType(MainContentPane contentPane) {
		super("Beiros teszt");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TestFrame tf = new TestFrame();
		parent.add(tf);
	
		

	}

}
