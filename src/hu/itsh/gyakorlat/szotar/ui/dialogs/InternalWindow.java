package hu.itsh.gyakorlat.szotar.ui.dialogs;

import java.awt.LayoutManager;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public abstract class InternalWindow extends JInternalFrame {

	JPanel contentPane;
	
	
	public InternalWindow(String title) {
		contentPane = (JPanel) this.getContentPane();
		setTitle(title);
		setContentPane(contentPane);
		setClosable(true);
		
	}
	
	public InternalWindow(String title, LayoutManager layout) {
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(layout);
		setTitle(title);
		setContentPane(contentPane);
		setClosable(true);
	}
	
	public InternalWindow(String title, JPanel contentPane) {
		this.contentPane = contentPane;
		setTitle(title);
		setContentPane(contentPane);
		setClosable(true);
	}
}
