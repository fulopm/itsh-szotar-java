package hu.itsh.gyakorlat.szotar.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hu.itsh.gyakorlat.szotar.ui.MainContentPane;
import hu.itsh.gyakorlat.szotar.ui.windows.WindowHanger;

public class ActionMenuHanger extends AbstractAction
{
	MainContentPane parent;
	
	public ActionMenuHanger(MainContentPane contentPane)
	{
		super("Akasztofa");
		parent = contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		parent.add(new WindowHanger());
		
	}
	
}
