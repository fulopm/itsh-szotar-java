package hu.itsh.gyakorlat.szotar.ui.dialogs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.Tag;

public class WindowDbEditRow extends InternalWindow {

	int rowID;
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

	public WindowDbEditRow(Row row) {
		super(SharedConstants.APP_NAME + " (-) Sor szerkesztése");
		
		layoutHelper = new DesignGridLayout(this.getContentPane());
		this.getLayeredPane().setLayer(this, JLayeredPane.POPUP_LAYER.intValue());
		
		
		if (!Database.dict.containsRow(row)) {
			this.dispose();
			return;
		}
		
		this.row = row;

		initComponents();

		layoutHelper.row().grid(labelID).add(fieldID);
		layoutHelper.row().grid(labelTimestamp).add(fieldTimestamp);
		layoutHelper.row().grid(labelEngWord).add(fieldPrefix).add(fieldWord).add(fieldSuffix);
		layoutHelper.row().grid(labelEngExplain).add(fieldEngExplain);
		layoutHelper.row().grid(labelEngExample).add(fieldEngExample);
		layoutHelper.row().grid(labelHunWord).add(fieldHun0).add(fieldHun1);
		layoutHelper.row().grid(labelLevel).add(fieldLevel);
		layoutHelper.row().grid(labelLang).add(fieldLang);
		layoutHelper.row().grid(labelForms).add(fieldForm0).add(fieldForm1).add(fieldForm2).add(fieldForm3);

		layoutHelper.row().bar().add(buttonSave, Tag.OK);
		setMinimumSize(new Dimension(810, 380));
		setSize(810, 380);
		setVisible(true);
		
		

	}

	void initComponents() {
		labelID = new JLabel("ID:");
		fieldID = new JTextField(Integer.toString(row.getId()));
		fieldID.setEditable(false);

		labelTimestamp = new JLabel("Timestamp:");
		fieldTimestamp = new JTextField(row.getTimestamp());
		fieldTimestamp.setEditable(false);

		labelEngWord = new JLabel("Idegen szó:");
		fieldPrefix = new JTextField(row.getPrefix());
		fieldWord = new JTextField(row.getWord());
		fieldSuffix = new JTextField(row.getSuffix());

		labelEngExplain = new JLabel("Idegen leírás:");
		fieldEngExplain = new JTextArea(row.getEngExplain());

		labelEngExample = new JLabel("Idegen példa:");
		fieldEngExample = new JTextArea(row.getEngExample());

		labelHunWord = new JLabel("Magyar szó:");
		fieldHun0 = new JTextField(row.getHun0());
		fieldHun1 = new JTextField(row.getHun1());

		labelHunExplain = new JLabel("Magyar leírás:");
		fieldHunExplain = new JTextArea(row.getHunExplain());

		labelHunExample = new JLabel("Magyar példa:");
		fieldHunExample = new JTextArea(row.getHunExample());

		labelLevel = new JLabel("Szint:");
		fieldLevel = new JTextField(Integer.toString(row.getLevel()));

		labelLang = new JLabel("Nyelv:");
		fieldLang = new JTextField(""+row.getLang());

		labelForms = new JLabel("Szótári alakok:");
		fieldForm0 = new JTextField(row.getForm0());
		fieldForm1 = new JTextField(row.getForm1());
		fieldForm2 = new JTextField(row.getForm2());
		fieldForm3 = new JTextField(row.getForm3());
		
		buttonSave = new JButton("Mentés");

	}

}
