package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.games.Hanger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WindowHanger extends InternalWindow implements ActionListener {
	private Hanger game;
	private int fail_counter = 0;

	private JButton[] abcButtons;
	private JButton newGame;
	private BorderLayout la;
	private JLabel word;

	private JPanel abcContainer;
	private JPanel wordContainer;

	private String temp;
	private String inGame = "varok..";

	public WindowHanger() {
		super("Akasztofa jatek");
		
		la = new BorderLayout(10, 10);
		this.setLayout(la);
		setResizable(false);
		setVisible(true);
		// 43
		this.setSize(600, 400);
		this.setMaximumSize(new Dimension(600, 400));
		this.setMinimumSize(new Dimension(600, 400));
		this.setPreferredSize(new Dimension(600, 400));
		initializeComponents();


	}

	public void initializeComponents() {
		// Add button a-z
		abcContainer = new JPanel();
		abcContainer.setLayout(new GridLayout(5, 6));

		abcButtons = new JButton[26];
		add(abcContainer, BorderLayout.SOUTH);
		// 65-90 ASCII capital letters in abc order
		int abc_ASCII = 65;
		for (int i = 0; i < abcButtons.length; i++) {
			abcButtons[i] = new JButton("" + (char) abc_ASCII);
			abc_ASCII++;
		}

		for (JButton btn : abcButtons) 
		{
			btn.addActionListener(this);
			btn.setFont(new Font(new JPanel().getFont().getName(), Font.PLAIN, 20));
			btn.setRequestFocusEnabled(false);
			btn.setEnabled(false);
			btn.setMaximumSize(new Dimension(30, 30));
			abcContainer.add(btn);

		}
		//--------------------------
		// NEW GAME
		newGame = new JButton("Uj jatek");
		newGame.addActionListener(this);
		abcContainer.add(newGame);
		// --------------------------

		// Set JLabel to desired properties

		word = new JLabel();
		Font font = word.getFont();
		word.setFont(new Font(font.getName(), Font.PLAIN, 30));
		word.setMaximumSize(new Dimension(30, 50));
		add(word, BorderLayout.NORTH);
		// --------------------------


		pack();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (newGame.equals(arg0.getSource()))
			startGame();
		else {
			temp = word.getText();
			word.setText(game.checkAndUpdateWord(word.getText(), arg0.getActionCommand().toLowerCase().charAt(0)));
			for (JButton btn : abcButtons) {
				if (btn.equals(arg0.getSource())) {
					if (temp.equals(word.getText())) {
						btn.setForeground(Color.RED);
						btn.setEnabled(false);
						fail_counter++;
					}
					else
					{
						btn.setForeground(Color.GREEN.darker());
						btn.setEnabled(false);
						btn.setRequestFocusEnabled(false);
					}
				}
			}
			System.out.println(fail_counter);

			if (game.checkWord(word.getText())) {
				for (JButton btn : abcButtons)
					btn.setEnabled(false);
				JOptionPane.showInternalMessageDialog(InternalWindow.mainContentPane, "Gratulalok, kitalaltad!");
			}
		}
	}

	private void startGame() 
	{
		String gen_word = "";
		do
		{
			gen_word = Database.dict.getRow((int) new Random().nextInt(Database.dict.getRowCount()) + 1).getWord();
			System.out.println("GENERATED WORD: " + gen_word);
		} while (gen_word.length() >= 20);
		
		game = new Hanger(gen_word.replaceAll("[-+.^:,?!()/]", ""));
		word.setText(game.toHidden());
		word.setHorizontalAlignment(JLabel.CENTER);
		word.setVerticalAlignment(JLabel.CENTER);
		newGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pack();
		
		for (JButton btn : abcButtons)
		{
			btn.setEnabled(true);
			btn.setForeground(Color.BLACK);
		}

		

		fail_counter = 0;
	}
}
