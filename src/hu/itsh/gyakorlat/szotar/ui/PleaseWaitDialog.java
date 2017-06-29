package hu.itsh.gyakorlat.szotar.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import net.java.dev.designgridlayout.DesignGridLayout;

public class PleaseWaitDialog extends JDialog {

	JLabel labelPleaseWait;
	JProgressBar progressBar;

	JPanel panel;

	DesignGridLayout layoutHelper;

	public PleaseWaitDialog() {
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setUndecorated(true);

		initComponents();

		layoutHelper = new DesignGridLayout(panel);

		layoutHelper.row().center().add(labelPleaseWait);
		layoutHelper.row().center().add(progressBar);

		add(panel);
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);

	}

	void initComponents() {
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);

		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		
		labelPleaseWait = new JLabel("Kérem, várjon...");
		labelPleaseWait.setFont(UIManager.getFont("Label.font").deriveFont(18f).deriveFont(Font.BOLD));

	}

}