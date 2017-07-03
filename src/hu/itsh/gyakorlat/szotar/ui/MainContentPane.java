package hu.itsh.gyakorlat.szotar.ui;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;

import hu.itsh.gyakorlat.szotar.ui.windows.InternalWindow;

public class MainContentPane extends JDesktopPane {

	public MainContentPane() {
		initComponents();
	}

	public void initComponents() {

	}

	public void addInterenalWindow(InternalWindow w) {
		add(w);
	}

	public void cascade() {
		JInternalFrame ifs[] = getAllFrames();
		for (int i = 0; i < ifs.length; i++) {
			ifs[i].setBounds(i * 20, i * 20, ifs[i].getWidth(), ifs[i].getHeight());
		}
	}
	
	

	

}
