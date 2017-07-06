package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.sl.usermodel.StrokeStyle;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.games.Hanger;
import hu.itsh.gyakorlat.szotar.ui.UIUtil;

public class WindowHanger extends InternalWindow implements ActionListener {
	private Hanger game;
	private int failCounter;

	private JButton[] abcButtons;
	private JButton newGame;
	private BorderLayout la;
	private JLabel labelWord;

	private JPanel abcContainer;
	private JPanel panelHang;

	private String currentWordState;

	private List<Object> hangObjects;

	private final Object[] body = {

			new Line(650, 60, 650, 450), // akasztofa
			// fuggoleges
			new Line(570, 60, 650, 140), // tamaszto (x-60,
											// y+60)
			new Line(350, 60, 650, 60), // akasztofa vizszintes
			new Line(350, 60, 350, 160), // kotel
			Head.head, new Line(350, 160 + Head.head.wh, 350, (160 + Head.head.wh) + (2 * Head.head.wh)), // test
			new Line(350, (160 + Head.head.wh) + 10, 350 - 35, ((160 + Head.head.wh) + 10) + 30), /// bal
																									/// ///
																									/// kez
			new Line(350, (160 + Head.head.wh) + 10, 350 + 35, ((160 + Head.head.wh) + 10) + 30), // jobb
																									// kez
			new Line(350, (160 + Head.head.wh) + (2 * Head.head.wh), 350 - 35,
					(160 + Head.head.wh) + (2 * Head.head.wh) + 50), /// bal
																		/// lab
			new Line(350, (160 + Head.head.wh) + (2 * Head.head.wh), 350 + 35,
					(160 + Head.head.wh) + (2 * Head.head.wh) + 50) };

	public WindowHanger() {
		super("Akasztofa jatek");

		la = new BorderLayout(10, 10);
		this.setLayout(la);
		setResizable(false);
		setVisible(true);
		// 43
		this.setSize(800, 800);
		this.setMaximumSize(new Dimension(800, 800));
		this.setMinimumSize(new Dimension(800, 800));
		this.setPreferredSize(new Dimension(800, 800));
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

		for (JButton btn : abcButtons) {
			btn.addActionListener(this);
			btn.setFont(new Font(new JPanel().getFont().getName(), Font.PLAIN, 20));
			btn.setRequestFocusEnabled(false);
			btn.setEnabled(false);
			btn.setMaximumSize(new Dimension(30, 30));
			abcContainer.add(btn);

		}
		// --------------------------
		// NEW GAME
		newGame = new JButton("Uj jatek");
		newGame.addActionListener(this);
		abcContainer.add(newGame);
		// --------------------------

		// Set JLabel to desired properties

		labelWord = new JLabel();
		Font font = labelWord.getFont();
		labelWord.setFont(new Font(font.getName(), Font.PLAIN, 30));
		labelWord.setMaximumSize(new Dimension(30, 50));
		add(labelWord, BorderLayout.NORTH);
		// --------------------------

		hangObjects = new ArrayList<>();

		panelHang = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(2f));
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				for (Object o : hangObjects) {
					if (o instanceof Line) {
						Line l = (Line) o;
						l.drawn = true;
						g2.setColor(l.color);
						g2.drawLine(l.x1 - 100, l.y1, l.x2 - 100, l.y2);
					} else if (o instanceof Head) {
						Head h = (Head) o;
						g2.setColor(h.color);
						g2.drawOval(h.x - 100, h.y, h.wh, h.wh);
					}
				}

			}
		};
		add(panelHang, BorderLayout.CENTER);
		pack();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source = (JButton) arg0.getSource();
		if (newGame == source)
			startGame();
		else {
			currentWordState = labelWord.getText();
			labelWord.setText(
					game.checkAndUpdateWord(labelWord.getText(), source.getActionCommand().toLowerCase().charAt(0)));
			if (currentWordState.equals(labelWord.getText())) {
				source.setForeground(Color.RED);
				source.setEnabled(false);
				if (failCounter < 9) {
					hangObjects.add(body[failCounter]);

					panelHang.repaint();
				} else {
					hangObjects.add(body[9]);
					panelHang.repaint();
					UIUtil.showErrorDialog("Vesztettél!\nEz volt a szó: " + labelWord.getText());
					for (JButton btn : abcButtons)
						btn.setEnabled(false);

				}
				failCounter++;
			} else {
				source.setForeground(Color.GREEN.darker());
				source.setEnabled(false);
				source.setRequestFocusEnabled(false);
			}

			System.out.println(failCounter);

			if (game.checkWord(labelWord.getText())) {
				for (JButton btn : abcButtons)
					btn.setEnabled(false);
				JOptionPane.showInternalMessageDialog(InternalWindow.mainContentPane, "Gratulalok, kitalaltad!");
			}
		}
	}

	private void startGame() {
		hangObjects.clear();
		panelHang.repaint();
		String gen_word = "";
		do {
			gen_word = Database.dict.getRow((int) new Random().nextInt(Database.dict.getRowCount()) + 1).getWord();
			System.out.println("GENERATED WORD: " + gen_word);
		} while (gen_word.length() >= 20);

		game = new Hanger(gen_word.replaceAll("[-+.^:,?!()/]", ""));
		labelWord.setText(game.toHidden());
		labelWord.setHorizontalAlignment(JLabel.CENTER);
		labelWord.setVerticalAlignment(JLabel.CENTER);
		newGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pack();

		for (JButton btn : abcButtons) {
			btn.setEnabled(true);
			btn.setForeground(Color.BLACK);
		}
		failCounter = 0;
	}

	private static class Line {
		final int x1;
		final int y1;
		final int x2;
		final int y2;
		final Color color = Color.BLACK;
		boolean drawn = false;

		public Line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	private static class Head {

		final int x;
		final int y;
		final int wh;
		final Color color = Color.BLACK;
		static Head head = new Head(325, 160, 50); // fej

		public Head(int x, int y, int wh) {
			this.x = x;
			this.y = y;
			this.wh = wh;
		}

	}
}
