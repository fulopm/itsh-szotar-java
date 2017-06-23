package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.dialogs.DialogHelpAbout;

public class ActionMenuOpenHelpAbout extends AbstractAction{
	
	MainContentPane parent;
	
	
	public ActionMenuOpenHelpAbout(MainContentPane contentPane) {
		super("A programr�l...");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		parent.add(new DialogHelpAbout());
		
	}

}
