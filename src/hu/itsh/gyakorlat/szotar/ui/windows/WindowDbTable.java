package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableRowSorter;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.ui.ds.DatabaseTableModel;
import hu.itsh.gyakorlat.szotar.ui.listeners.DbTableMouseAdapter;
import net.java.balloontip.BalloonTip;

public class WindowDbTable extends InternalWindow implements ChangeListener {

	JTable table;
	DatabaseTableModel tableModel;
	TableRowSorter tableSorter;
	JTextField fieldSearch;
	BalloonTip fieldSearchBalloon;

	JPopupMenu fieldSearchMenu;
	JLabel fieldSearchMenuLanguage;
	JRadioButtonMenuItem fieldSearchMenuLangEng;
	JRadioButtonMenuItem fieldSearchMenuLangHun;
	ButtonGroup languageButtonGroup;
	JLabel fieldSearchMenuTitle;
	JCheckBoxMenuItem fieldSearchMenuExplainAndExample;
	JCheckBoxMenuItem fieldSearchMenuForms;

	JButton buttonAddWord;

	JPanel panelUserActions;

	public WindowDbTable() {
		super(SharedConstants.APP_NAME + " (-) Adatbázis", new BorderLayout());
		initComponents();

		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		contentPane.add(panelUserActions, BorderLayout.SOUTH);
		setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2));
		setVisible(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				WindowDbTable.this.dispose();
				InternalWindow.mainContentPane.remove(WindowDbTable.this);
			}
		});

	}

	void initComponents() {

		fieldSearch = new JTextField();

		fieldSearch.setToolTipText("Keresés");
		fieldSearchBalloon = new BalloonTip(fieldSearch,
				"Kattintson a mezore jobb klikkel, ha szeretne modositani hogy mely mezokben keressunk!");
		fieldSearchMenu = new JPopupMenu();

		fieldSearchMenuLanguage = new JLabel("Keresési nyelv:");

		fieldSearchMenuLangEng = new JRadioButtonMenuItem("Angol");
		fieldSearchMenuLangEng.addChangeListener(this);
		fieldSearchMenuLangHun = new JRadioButtonMenuItem("Magyar");
		fieldSearchMenuLangHun.addChangeListener(this);
		languageButtonGroup = new ButtonGroup();
		languageButtonGroup.add(fieldSearchMenuLangEng);
		languageButtonGroup.add(fieldSearchMenuLangHun);

		fieldSearchMenuTitle = new JLabel("Keresés a következő mezőkben:");
		fieldSearchMenuExplainAndExample = new JCheckBoxMenuItem("Magyarázat és példa");
		fieldSearchMenuExplainAndExample.addChangeListener(this);
		fieldSearchMenuForms = new JCheckBoxMenuItem("Szótári alakokban");
		fieldSearchMenuForms.addChangeListener(this);

		fieldSearchMenu.add(fieldSearchMenuLanguage);
		fieldSearchMenu.add(fieldSearchMenuLangEng);
		fieldSearchMenu.add(fieldSearchMenuLangHun);

		fieldSearchMenu.add(fieldSearchMenuTitle);
		fieldSearchMenu.add(fieldSearchMenuExplainAndExample);
		fieldSearchMenu.add(fieldSearchMenuForms);

		fieldSearch.setComponentPopupMenu(fieldSearchMenu);

		tableModel = new DatabaseTableModel(Database.dict);
		tableSorter = new TableRowSorter<DatabaseTableModel>(tableModel);
		table = new JTable(tableModel);
		table.setRowSorter(tableSorter);
		table.addMouseListener(new DbTableMouseAdapter(table));
		fieldSearch.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				newFilter(fieldSearch.getText().toLowerCase(), fieldSearchMenuLangHun.isSelected(), fieldSearchMenuExplainAndExample.isSelected(),
						fieldSearchMenuForms.isSelected());

			}

		});

		buttonAddWord = new JButton("H.ad");
		buttonAddWord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WindowDbAddRow addRowWindow = new WindowDbAddRow(fieldSearch.getText());
				mainContentPane.add(addRowWindow);
				mainContentPane.cascade();
			}
		});

		panelUserActions = new JPanel(new GridLayout(1, 2));
		panelUserActions.add(fieldSearch);
		panelUserActions.add(buttonAddWord);

	}

	private void newFilter(String searchString, boolean isHungarian, boolean inExampleAndExplain, boolean inForms) {
		RowFilter<String, String> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			rf = new RowFilter<String, String>() {

				@Override
				public boolean include(javax.swing.RowFilter.Entry<? extends String, ? extends String> entry) {
					boolean finalResult = false;
					if (isHungarian) {
						if (inExampleAndExplain) {
							finalResult = entry.getStringValue(7).startsWith(searchString)
									|| entry.getStringValue(8).startsWith(searchString)
									|| entry.getStringValue(9).contains(searchString)
									|| entry.getStringValue(10).contains(searchString);
						} else {
							finalResult = entry.getStringValue(7).startsWith(searchString)
									|| entry.getStringValue(8).startsWith(searchString);
						}
					} else {
						if (inExampleAndExplain && inForms) {
							finalResult = entry.getStringValue(3).startsWith(searchString)
									|| entry.getStringValue(5).contains(searchString)
									|| entry.getStringValue(6).contains(searchString)
									|| entry.getStringValue(14).startsWith(searchString)
									|| entry.getStringValue(15).startsWith(searchString)
									|| entry.getStringValue(16).startsWith(searchString)
									|| entry.getStringValue(17).startsWith(searchString);
						} else if (inExampleAndExplain) {
							finalResult = entry.getStringValue(3).startsWith(searchString)
									|| entry.getStringValue(5).contains(searchString)
									|| entry.getStringValue(6).contains(searchString);
						} else if (inForms) {
							finalResult = entry.getStringValue(3).startsWith(searchString)
									|| entry.getStringValue(14).startsWith(searchString)
									|| entry.getStringValue(15).startsWith(searchString)
									|| entry.getStringValue(16).startsWith(searchString)
									|| entry.getStringValue(17).startsWith(searchString);
						} else {
							finalResult = entry.getStringValue(3).startsWith(searchString);
						}
					}
					return finalResult;

				}

			};
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		tableSorter.setRowFilter(rf);
	}

	public DatabaseTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DatabaseTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (fieldSearchMenuLangHun.isSelected()) {
			fieldSearchMenuForms.setSelected(false);
			fieldSearchMenuForms.setEnabled(false);
		}

		newFilter(fieldSearch.getText().toLowerCase(), fieldSearchMenuLangHun.isSelected(), fieldSearchMenuExplainAndExample.isSelected(),
				fieldSearchMenuForms.isSelected());

	}

}
