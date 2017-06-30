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

public class WindowHelpAbout extends InternalWindow {

	JLabel labelAppTitle;
	JTextArea fieldCredits;

	public WindowHelpAbout() {
		super(SharedConstants.APP_NAME + " (-) A programról", new GridLayout(2,1));
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
		String creditsText = "Az alkalmazas fejleszteseben reszt vettek:\n";
		for (String developer : SharedConstants.APP_DEVS)
			creditsText += developer +System.lineSeparator();
		
		creditsText += "\n\nAz alkalmazas az IT Services Hungary altal szervezett osszefuggo szakmai gyakorlaton keszult.";
			
	
		fieldCredits.setText(creditsText);
		fieldCredits.setEditable(false);
			
	}
	
	

}
