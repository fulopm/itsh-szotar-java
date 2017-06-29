package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;

public class ActionMenuExit extends AbstractAction{
	
	
	
	
	public ActionMenuExit() {
		super("Kilépés");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (UIUtil.showYesNoDialog("Biztosan kilep az NGP programbol?\nElmentette a módosításokat?")) {
			System.exit(0);
		}
		
	}

}
