package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.tests.SelectionTest;
import hu.itsh.gyakorlat.szotar.ui.MainContentPane;

public class ActionMenuTestSelect extends AbstractAction{
	
	MainContentPane parent;

	public ActionMenuTestSelect(MainContentPane contentPane){
		super("Valasztos tesz");
		parent = contentPane;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SelectionTest st = new SelectionTest();
		parent.add(st);
	}

}
