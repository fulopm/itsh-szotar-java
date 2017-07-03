package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;

import javax.swing.JPopupMenu;
import com.inet.jortho.PopupListener;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.spell.SpellChecking;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionSaveRowState;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.Tag;

public class WindowDbEditRow extends WindowDbRow {

	public WindowDbEditRow(Row row) {
		super(row);

		layoutHelper.row().grid(labelID).add(fieldID);
		layoutHelper.row().grid(labelTimestamp).add(fieldTimestamp);
		layoutHelper.row().grid(labelEngWord).add(fieldPrefix).add(fieldWord).add(fieldSuffix);
		layoutHelper.row().grid(labelEngExplain).add(fieldEngExplain);
		layoutHelper.row().grid(labelEngExample).add(fieldEngExample);
		layoutHelper.row().grid(labelHunWord).add(fieldHun0).add(fieldHun1);
		layoutHelper.row().grid(labelLevel).add(fieldLevel);
		layoutHelper.row().grid(labelLang).add(fieldLang);
		layoutHelper.row().grid(labelForms).add(fieldForm0).add(fieldForm1).add(fieldForm2).add(fieldForm3);
		
		SpellChecking sp = new SpellChecking();
		sp.check(fieldPrefix);
		sp.check(fieldWord);
		sp.check(fieldSuffix);
		sp.check(fieldEngExplain);
		sp.check(fieldEngExample);
		sp.check(fieldForm0);
		sp.check(fieldForm1);
		sp.check(fieldForm2);
		sp.check(fieldForm3);
		
		
		layoutHelper.row().bar().add(buttonSave, Tag.OK);
		setMinimumSize(new Dimension(810, 380));
		setSize(810, 380);
		setVisible(true);

	}

	@Override
	protected void initComponents() {
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
		fieldLang = new JTextField("" + row.getLang());

		labelForms = new JLabel("Szótári alakok:");
		fieldForm0 = new JTextField(row.getForm0());
		fieldForm1 = new JTextField(row.getForm1());
		fieldForm2 = new JTextField(row.getForm2());
		fieldForm3 = new JTextField(row.getForm3());

		buttonSave = new JButton("Mentés");
		// buttonSave.setAction(new ActionSaveRowState(row));
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fieldLevel.getText().length() < 1 || fieldLang.getText().length() < 1) {
					UIUtil.showErrorDialog("Sem a szint, sem pedig a nyelv nem maradhat uresen!");
					return;
				} else {
					row.setPrefix(fieldPrefix.getText());
					row.setWord(fieldWord.getText());
					row.setSuffix(fieldSuffix.getText());
					row.setEngExplain(fieldEngExplain.getText());
					row.setEngExample(fieldEngExample.getText());
					row.setLevel(Integer.parseInt(fieldLevel.getText()));
					row.setLang(fieldLang.getText().charAt(0));
					row.setHun0(fieldHun0.getText());
					row.setHun1(fieldHun1.getText());
					row.setHunExplain(fieldHunExplain.getText());
					row.setHunExample(fieldHunExample.getText());
					row.setForm0(fieldForm0.getText());
					row.setForm1(fieldForm1.getText());
					row.setForm2(fieldForm2.getText());
					row.setForm3(fieldForm3.getText());
					new ActionSaveRowState(row).actionPerformed(e);
					WindowDbEditRow.this.dispose();
				}
			}
		});

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WindowDbEditRow other = (WindowDbEditRow) obj;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		return true;
	}

}
