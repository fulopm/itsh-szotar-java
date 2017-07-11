package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowHelpAbout;

public class ActionMenuOpenHelpAbout extends AbstractAction{
	
	MainContentPane parent;
	
	
	public ActionMenuOpenHelpAbout(MainContentPane contentPane) {
		super("A programrol...");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		parent.addInternalWindow(new WindowHelpAbout());
		
	}

}
