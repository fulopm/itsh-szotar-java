package hu.itsh.gyakorlat.szotar.ui;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import hu.itsh.gyakorlat.szotar.SharedConstants;

public class PleaseWaitDialog extends JDialog {

	private JLabel labelPleaseWait;
	JProgressBar progressBar;

	JPanel panel;

	public PleaseWaitDialog(MainContentPane parent, String title) {
		super();
		setUndecorated(true);

		initComponents();

		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);

		panel.add(labelPleaseWait);
		panel.add(progressBar);


		add(panel);
		pack();
		setAlwaysOnTop(true);
		setLocationRelativeTo(parent);

	}

	void initComponents() {
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);

		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		labelPleaseWait = new JLabel("Kérem, várjon...");
		labelPleaseWait.setFont(labelPleaseWait.getFont().deriveFont(22f));

	}

}