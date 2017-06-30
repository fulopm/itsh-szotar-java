package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.dialogs.WindowWorkBook;

public class ActionMenuTranslate extends AbstractAction
{
	MainContentPane parent;

	public ActionMenuTranslate(MainContentPane contentPane)
	{
		super("Szoszedet, mondatforditas");
		parent = contentPane;
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		WindowWorkBook wb = new WindowWorkBook();
		parent.add(wb);
		
	}

}
