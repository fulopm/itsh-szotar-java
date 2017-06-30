package hu.itsh.gyakorlat.szotar.tests;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.ui.dialogs.InternalWindow;

public class SelectionTest extends InternalWindow implements ActionListener {

	private JButton buttonStart, buttonCheck;
	private JLabel labelEnglishWord, labelMeaning, labelCheck;
	private JRadioButton radioAnswer1, radioAnswer2, radioAnswer3, radioAnswer4, radioAnswer5;
	private ButtonGroup group;

	private String wordClass = "";
	private String dbWord = "";
	private String rightAns = "";
	private int random = 0;
	private int gameNo = 0;
	private int score = 0;

	private ArrayList<String> answers = new ArrayList<String>();

	public SelectionTest() {

		super(SharedConstants.APP_NAME + " (-) Valasztos teszt");
		initComponents();

		contentPane.setLayout(null);

		contentPane.add(this.buttonStart);
		contentPane.add(this.labelEnglishWord);
		contentPane.add(this.labelCheck);
		contentPane.add(this.labelMeaning);

		this.group.add(this.radioAnswer1);
		this.group.add(this.radioAnswer2);
		this.group.add(this.radioAnswer3);
		this.group.add(this.radioAnswer4);
		this.group.add(this.radioAnswer5);

		contentPane.add(this.radioAnswer1);
		contentPane.add(this.radioAnswer2);
		contentPane.add(this.radioAnswer3);
		contentPane.add(this.radioAnswer4);
		contentPane.add(this.radioAnswer5);

		contentPane.add(this.buttonCheck);

		///////////// ACTION LISTENERS /////////////

		this.buttonStart.addActionListener(this);
		this.buttonCheck.addActionListener(this);
		this.radioAnswer1.addActionListener(this);
		this.radioAnswer2.addActionListener(this);
		this.radioAnswer3.addActionListener(this);
		this.radioAnswer4.addActionListener(this);
		this.radioAnswer5.addActionListener(this);

		setPreferredSize(new Dimension(500, 280));
		setMinimumSize(new Dimension(500, 280));
		setMaximizable(true);

		pack();
		this.setVisible(true);

	}

	void initComponents() {
		this.buttonStart = new JButton("Start!");
		this.buttonStart.setSize(100, 30);
		this.buttonStart.setLocation(20, 20);

		this.labelEnglishWord = new JLabel();
		this.labelEnglishWord.setSize(300, 30);
		this.labelEnglishWord.setLocation(150, 20);
		this.labelEnglishWord.setText("Angol szo: ");

		this.labelMeaning = new JLabel("Valaszd ki a jelenteset:");
		this.labelMeaning.setSize(200, 30);
		this.labelMeaning.setLocation(150, 50);

		this.labelCheck = new JLabel(this.gameNo + "/10");
		this.labelCheck.setSize(100, 80);
		this.labelCheck.setLocation(20, 100);

		this.group = new ButtonGroup();

		this.radioAnswer1 = new JRadioButton("");
		this.radioAnswer1.setLocation(150, 80);
		this.radioAnswer1.setSize(300, 20);

		this.radioAnswer2 = new JRadioButton("");
		this.radioAnswer2.setLocation(150, 110);
		this.radioAnswer2.setSize(300, 20);

		this.radioAnswer3 = new JRadioButton("");
		this.radioAnswer3.setLocation(150, 140);
		this.radioAnswer3.setSize(300, 20);

		this.radioAnswer4 = new JRadioButton("");
		this.radioAnswer4.setLocation(150, 170);
		this.radioAnswer4.setSize(300, 20);

		this.radioAnswer5 = new JRadioButton("");
		this.radioAnswer5.setLocation(150, 200);
		this.radioAnswer5.setSize(300, 20);

		this.buttonCheck = new JButton("Ellenorzes");
		this.buttonCheck.setSize(100, 30);
		this.buttonCheck.setLocation(20, 60);
		this.buttonCheck.setEnabled(false);

	}

	public void setRandom() {
		this.random = (int) (Math.random() * (Database.dict.getRowCount() + 1));
	}

	public String newWord() {
		setRandom();
		if (!Database.dict.getRow(this.random).getWordClass().contains("definició") && !Database.dict.getRow(this.random).getWordClass().contains("mondat") && !Database.dict.getRow(this.random).getWordClass().contains("rövidítés")) {
			this.dbWord = Database.dict.getRow(this.random).getWord();
			this.wordClass = Database.dict.getRow(this.random).getWordClass();
			this.rightAns = Database.dict.getRow(this.random).getHun0();
		} else {
			newWord();
		}
		return this.dbWord;

	}

	public void getAnswers() {
		this.answers.clear();
		this.answers.add(this.rightAns);
		for (int i = 0; i < 4; i++) {
			setRandom();
			while (!Database.dict.getRow(this.random).getWordClass().equals(this.wordClass)) {
				setRandom();
			}
			this.answers.add(Database.dict.getRow(this.random).getHun0());
		}

		Collections.shuffle(this.answers);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(this.buttonStart)) {
			this.gameNo++;

			if (this.gameNo > 10) {
				JOptionPane.showMessageDialog(null, "Vege a jateknak!\nEredmenyed: " + this.score + "/10", "Eredmeny",
						JOptionPane.INFORMATION_MESSAGE);
				this.gameNo = 0;
				this.score = 0;
				this.buttonStart.setText("Start!");
				this.buttonCheck.setEnabled(false);
				this.group.clearSelection();
				this.labelCheck.setText("0/10");
				this.labelEnglishWord.setText("Angol szo: ");
				
				this.radioAnswer1.setText("");
				this.radioAnswer2.setText("");
				this.radioAnswer3.setText("");
				this.radioAnswer4.setText("");
				this.radioAnswer5.setText("");

			} else {
				this.buttonStart.setText("Kovetkezo");
				this.buttonStart.setEnabled(false);
				this.buttonCheck.setEnabled(true);
				this.labelCheck.setText(this.gameNo + "/10");
				this.labelEnglishWord.setText("Angol szo: " + newWord());
				this.group.clearSelection();

				//////// VALASZOK BEALLITASA /////////
				getAnswers();

				this.radioAnswer1.setText(this.answers.get(0));
				this.radioAnswer1.setActionCommand(this.radioAnswer1.getText());

				this.radioAnswer2.setText(this.answers.get(1));
				this.radioAnswer2.setActionCommand(this.radioAnswer2.getText());

				this.radioAnswer3.setText(this.answers.get(2));
				this.radioAnswer3.setActionCommand(this.radioAnswer3.getText());

				this.radioAnswer4.setText(this.answers.get(3));
				this.radioAnswer4.setActionCommand(this.radioAnswer4.getText());

				this.radioAnswer5.setText(this.answers.get(4));
				this.radioAnswer5.setActionCommand(this.radioAnswer5.getText());

			}
		} else if (ae.getSource().equals(this.buttonCheck)) {
			try {
				if (this.rightAns.equals(this.group.getSelection().getActionCommand())) {
					this.labelCheck.setText(this.gameNo + "/10 - Helyes!");
					this.score++;
					this.buttonStart.setEnabled(true);
				} else {
					this.labelCheck.setText("<html>" + this.gameNo + "/10 - Hiba!<br>" + "Helyes válasz: <br>"
							+ this.rightAns + "</html>");
					this.buttonStart.setEnabled(true);
				}
			} catch (Exception e) {
				this.labelCheck.setText("<html>" + this.gameNo + "/10 - Hiba!<br>" + "Válassz valamit!<br></html>");
			}
		}

	}
}
