package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.Util;
import hu.itsh.gyakorlat.szotar.spell.SpellChecking;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionAddRow;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionAutoFillForms;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionSaveRowState;
import net.java.dev.designgridlayout.Tag;

public class WindowDbAddRow extends WindowDbRow {

	

	public WindowDbAddRow() {
		super();
		layoutHelper.row().grid(labelID).add(fieldID);
		layoutHelper.row().grid(labelTimestamp).add(fieldTimestamp);
		layoutHelper.row().grid(labelEngWord).add(fieldPrefix).add(fieldWord).add(fieldSuffix);
		layoutHelper.row().grid(labelEngExplain).add(fieldEngExplain);
		layoutHelper.row().grid(labelEngExample).add(fieldEngExample);
		layoutHelper.row().grid(labelHunWord).add(fieldHun0).add(fieldHun1);
		layoutHelper.row().grid(labelWordClass).add(fieldWordClass);
		layoutHelper.row().grid(labelLevel).add(fieldLevel);
		layoutHelper.row().grid(labelLang).add(fieldLang);
		layoutHelper.row().grid(labelForms).add(fieldForm0).add(fieldForm1).add(fieldForm2).add(fieldForm3)
				.add(buttonAutoFill);

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
		setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2, 420 ));
		setVisible(true);
	}

	public WindowDbAddRow(String word) {
		this();
		fieldWord.setText(word);

	}

	@Override
	protected void initComponents() {
		labelID = new JLabel("ID:");
		fieldID = new JTextField("később automatikusan kitöltve");
		fieldID.setEditable(false);
		fieldID.setEnabled(false);

		labelTimestamp = new JLabel("Timestamp:");
		fieldTimestamp = new JTextField("később automatikusan kitöltve");
		fieldTimestamp.setEditable(false);
		fieldTimestamp.setEnabled(false);

		labelEngWord = new JLabel("Idegen szó:");
		fieldPrefix = new JTextField();
		fieldWord = new JTextField();
		fieldSuffix = new JTextField();

		labelEngExplain = new JLabel("Idegen leírás:");
		fieldEngExplain = new JTextArea();

		labelEngExample = new JLabel("Idegen példa:");
		fieldEngExample = new JTextArea();

		labelHunWord = new JLabel("Magyar szó:");
		fieldHun0 = new JTextField();
		fieldHun1 = new JTextField();

		labelHunExplain = new JLabel("Magyar leírás:");
		fieldHunExplain = new JTextArea();

		labelHunExample = new JLabel("Magyar példa:");
		fieldHunExample = new JTextArea();

		labelWordClass = new JLabel("Szófaj:");
		fieldWordClass = new JComboBox<String>(SharedConstants.onlineWordClassShortenHun);

		labelLevel = new JLabel("Szint:");
		fieldLevel = new JTextField("1");

		labelLang = new JLabel("Nyelv:");
		fieldLang = new JTextField("a");

		labelForms = new JLabel("Szótári alakok:");
		fieldForm0 = new JTextField();
		fieldForm1 = new JTextField();
		fieldForm2 = new JTextField();
		fieldForm3 = new JTextField();

		buttonAutoFill = new JButton("Automatikus kitoltes");
		actionAutoFillForms = new ActionAutoFillForms(fieldWord, fieldWordClass, fieldForm0, fieldForm1, fieldForm2, fieldForm3);
		buttonAutoFill.setAction(actionAutoFillForms);
		try {
			buttonAutoFill.setIcon(new ImageIcon(ImageIO.read(new File("pencil.png"))));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		

		buttonSave = new JButton("Mentés");
		try {
			buttonSave.setIcon(new ImageIcon(ImageIO.read(new File("tick.png"))));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("MENTES ACTION!");
				if (fieldLevel.getText().length() < 1 || Util.parseInt(fieldLevel.getText()) == -1) {
					UIUtil.showErrorDialog("A szint mezo tartalma nem lehet ures, es csak szamot tartalmazhat!");
					return;
				} else if (fieldLang.getText().length() != 1) {
					UIUtil.showErrorDialog("A nyelv mezonek egy karaktert kell tartalmaznia!");
					return;
				} else {

					row.setPrefix(Util.escape(fieldPrefix.getText()));
					row.setWord(Util.escape(fieldWord.getText()));
					row.setSuffix(Util.escape(fieldSuffix.getText()));
					row.setEngExplain(Util.escape(fieldEngExplain.getText()));
					row.setEngExample(Util.escape(fieldEngExample.getText()));
					row.setLevel(Integer.parseInt(fieldLevel.getText()));
					row.setLang(fieldLang.getText().charAt(0));
					row.setHun0(Util.escape(fieldHun0.getText()));
					row.setHun1(Util.escape(fieldHun1.getText()));
					row.setHunExplain(Util.escape(fieldHunExplain.getText()));
					row.setHunExample(Util.escape(fieldHunExample.getText()));
					row.setWordClass((String)fieldWordClass.getSelectedItem());
					row.setForm0(Util.escape(fieldForm0.getText()));
					row.setForm1(Util.escape(fieldForm1.getText()));
					row.setForm2(Util.escape(fieldForm2.getText()));
					row.setForm3(Util.escape(fieldForm3.getText()));

					new ActionAddRow(row).actionPerformed(e);
					WindowDbAddRow.this.dispose();
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
		WindowDbAddRow other = (WindowDbAddRow) obj;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		return true;
	}

}
