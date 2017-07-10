package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import net.java.dev.designgridlayout.DesignGridLayout;

public abstract class WindowDbRow extends InternalWindow {
	
	Row row;

	JLabel labelID;
	JTextField fieldID;

	JLabel labelTimestamp;
	JTextField fieldTimestamp;

	JLabel labelEngWord;
	JTextField fieldPrefix;
	JTextField fieldWord;
	JTextField fieldSuffix;

	JLabel labelEngExplain;
	JTextArea fieldEngExplain;	

	JLabel labelEngExample;
	JTextArea fieldEngExample;

	JLabel labelHunWord;
	JTextField fieldHun0;
	JTextField fieldHun1;

	JLabel labelHunExplain;
	JTextArea fieldHunExplain;

	JLabel labelHunExample;
	JTextArea fieldHunExample;

	JLabel labelWordClass;
	JComboBox<String> fieldWordClass;
		
	JLabel labelLevel;
	JTextField fieldLevel;

	JLabel labelLang;
	JTextField fieldLang;

	JLabel labelForms;
	JTextField fieldForm0;
	JTextField fieldForm1;
	JTextField fieldForm2;
	JTextField fieldForm3;

	JButton buttonSave;

	DesignGridLayout layoutHelper;

	public WindowDbRow() {
		super(SharedConstants.APP_NAME + " (-) Sor hozzáadása");
		this.row = new Row();
		layoutHelper = new DesignGridLayout(this.getContentPane());
		this.getLayeredPane().setLayer(this, JLayeredPane.POPUP_LAYER.intValue());

		initComponents();

		setMinimumSize(new Dimension(810, 380));
		setSize(810, 380);
		setVisible(true);
	}

	public WindowDbRow(Row row) {
		super(SharedConstants.APP_NAME + " (-) Sor szerkesztése");
		this.row = row;
		layoutHelper = new DesignGridLayout(this.getContentPane());
		this.getLayeredPane().setLayer(this, JLayeredPane.POPUP_LAYER.intValue());
		if (!Database.dict.containsRow(row)) {
			this.dispose();
			return;
		}

		initComponents();

		setMinimumSize(new Dimension(810, 380));
		setSize(810, 380);
		setVisible(true);

	}

	protected abstract void initComponents();
}
