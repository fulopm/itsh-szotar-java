package hu.itsh.gyakorlat.szotar.ui.dialogs;

import java.awt.LayoutManager;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public abstract class InternalWindow extends JInternalFrame {

	JPanel contentPane;
	
	
	public InternalWindow(String title) {
		super(title, true, true, true, true);
		contentPane = (JPanel) this.getContentPane();
		setContentPane(contentPane);
		
	}
	
	public InternalWindow(String title, LayoutManager layout) {
		super(title, true, true, true, true);
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(layout);
		setContentPane(contentPane);
	}
	
	public InternalWindow(String title, JPanel contentPane) {
		super(title, true, true, true, true);
		this.contentPane = contentPane;
		setContentPane(contentPane);

	}
}
