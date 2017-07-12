package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.*;

import com.sun.glass.ui.MenuItem;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.io.stats.StatisticsReader;
import hu.itsh.gyakorlat.szotar.statistics.Statistics;
import hu.itsh.gyakorlat.szotar.tts.TTS;

public class WindowTestRead extends InternalWindow implements ActionListener {

	private JLabel labelInput, labelCheck, labelAnswser;
	private JTextField textEnglishWord;
	private JButton buttonStart, buttonAgain, buttonCheck;

	private int random = 0;
	private int gameNo = 0;
	private int score = 0;
	private String inputWord = "";
	private String dbWord;
	
	private TTS tts;	

	public WindowTestRead() {
		super(SharedConstants.APP_NAME + " (-) Felolvasos teszt");
		initComponents();

		contentPane.setLayout(null);

		contentPane.add(this.buttonStart);
		contentPane.add(this.buttonAgain);
		contentPane.add(this.labelInput);
		contentPane.add(this.textEnglishWord);
		contentPane.add(this.labelCheck);
		contentPane.add(this.buttonCheck);
		contentPane.add(this.labelAnswser);

		this.buttonStart.addActionListener(this);
		this.buttonAgain.addActionListener(this);
		this.buttonCheck.addActionListener(this);

		setPreferredSize(new Dimension(400, 220));
		setMinimumSize(new Dimension(400, 220));
		setMaximizable(true);

		pack();
		this.setVisible(true);

	}

	void initComponents() {
		this.buttonStart = new JButton("Start!");
		this.buttonStart.setSize(100, 30);
		this.buttonStart.setLocation(20, 20);

		this.buttonAgain = new JButton("Nem ertettem, ujra!");
		this.buttonAgain.setSize(150, 30);
		this.buttonAgain.setLocation(150, 20);
		this.buttonAgain.setEnabled(false);

		this.labelInput = new JLabel();
		this.labelInput.setSize(200, 30);
		this.labelInput.setLocation(20, 75);
		this.labelInput.setText("Ird ide, amit halottal:");

		this.textEnglishWord = new JTextField();
		this.textEnglishWord.setSize(100, 30);
		this.textEnglishWord.setLocation(150, 80);

		this.buttonCheck = new JButton("Ellenorzes");
		this.buttonCheck.setSize(100, 30);
		this.buttonCheck.setLocation(150, 120);
		buttonCheck.setMnemonic(KeyEvent.VK_ENTER);

		this.labelCheck = new JLabel(this.gameNo + "/10");
		this.labelCheck.setSize(100, 20);
		this.labelCheck.setLocation(260, 80);

		this.labelAnswser = new JLabel();
		this.labelAnswser.setSize(200, 30);
		this.labelAnswser.setLocation(150, 150);
		
		tts = new TTS();

	}

	public String newWord() {
		this.random = (int) (Math.random() * (Database.dict.getRowCount() + 1));
		if (!Database.dict.getRow(this.random).getWordClass().contains("definició")
				&& !Database.dict.getRow(this.random).getWordClass().contains("mondat")
				&& !Database.dict.getRow(this.random).getWordClass().contains("rövidítés")
				&& !Database.dict.getRow(this.random).getWordClass().contains("kifejezés")) {
			this.dbWord = Database.dict.getRow(this.random).getWord();
			System.out.println(this.dbWord);
		} else {
			newWord();
		}
		return this.dbWord;

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(this.buttonStart)) {
			if (!this.tts.isValid()) {
				this.tts = new TTS();
			}
			this.gameNo++;

			if (this.gameNo > 10) {
				this.tts.deallocate();
				JOptionPane.showMessageDialog(null, "Vege a jateknak!\nEredmenyed: " + this.score + "/10", "Eredmeny",
						JOptionPane.INFORMATION_MESSAGE);

				int[] result = StatisticsReader.readTypeStatistics();
				int right = result[0];
				int wrong = result[1];
				right += this.score;
				wrong += 10 - this.score;

				StatisticsReader.writeTypeStatistics(right, wrong);

				this.gameNo = 0;
				this.score = 0;
				this.buttonStart.setText("Start!");
				this.buttonAgain.setEnabled(false);

			} else {

				this.buttonStart.setText("Kovetkezo");
				this.labelAnswser.setText("");
				this.buttonAgain.setEnabled(true);
				this.textEnglishWord.setText("");
				this.textEnglishWord.setEnabled(true);
				this.buttonCheck.setEnabled(true);
				this.textEnglishWord.requestFocus();
				this.labelCheck.setText(this.gameNo + "/10");

				this.tts.speak(newWord());
			}
		} else if (ae.getSource().equals(this.buttonAgain)) {
			this.tts.speak(this.dbWord);
		} else if (ae.getSource().equals(this.buttonCheck)) {
			this.inputWord = this.textEnglishWord.getText();

			if (this.inputWord.equals(this.dbWord)) {
				this.labelCheck.setText(this.gameNo + "/10 - Helyes!");
				this.score++;
				this.textEnglishWord.setEnabled(false);
				this.buttonCheck.setEnabled(false);
				this.buttonStart.requestFocus();
			} else {
				this.labelCheck.setText(this.gameNo + "/10 - Hiba!");
				this.labelAnswser.setText("Helyes valasz: " + this.dbWord);
				this.textEnglishWord.setEnabled(false);
				this.buttonCheck.setEnabled(false);
				this.buttonStart.requestFocus();
			}
		}

	}

}
