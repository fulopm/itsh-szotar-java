package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowTestSelection;

public class ActionMenuTestSelect extends AbstractAction{
	
	MainContentPane parent;

	public ActionMenuTestSelect(MainContentPane contentPane){
		super("Valasztos tesz");
		parent = contentPane;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		WindowTestSelection st = new WindowTestSelection();
		parent.addInternalWindow(st);
	}

}
