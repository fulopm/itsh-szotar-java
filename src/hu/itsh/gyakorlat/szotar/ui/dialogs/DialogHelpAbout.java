package hu.itsh.gyakorlat.szotar.ui.dialogs;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import hu.itsh.gyakorlat.szotar.SharedConstants;

public class DialogHelpAbout extends InternalWindow {

	JLabel labelAppTitle;
	JTextArea fieldCredits;

	public DialogHelpAbout() {
		super(SharedConstants.APP_NAME + " (-) A programr�l", new GridLayout(2,1));
		initComponents();
		
		contentPane.add(labelAppTitle);
		contentPane.add(fieldCredits);
		pack();
		setVisible(true);

	}

	void initComponents() {
		labelAppTitle = new JLabel(SharedConstants.APP_NAME + " " + SharedConstants.APP_VERSION);
		labelAppTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		labelAppTitle.setHorizontalAlignment(JLabel.CENTER);
		
		
		fieldCredits = new JTextArea();
		fieldCredits.setBackground(UIManager.getColor("JPanel.Background"));
		String creditsText = "Az alkalmaz�s fejleszt�s�ben r�szt vettek:\n";
		for (String developer : SharedConstants.APP_DEVS)
			creditsText += developer +System.lineSeparator();
		
		creditsText += "\n\nAz alkalmaz�s az IT Services Hungary �ltal szervezett �sszef�gg� szakmai gyakorlaton k�sz�lt.";
			
	
		fieldCredits.setText(creditsText);
		fieldCredits.setEditable(false);
			
	}
	
	

}
