package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.itsh.gyakorlat.szotar.games.Hanger;
import net.java.dev.designgridlayout.DesignGridLayout;

public class WindowHanger extends InternalWindow implements ActionListener
{
	private Hanger game;
	
	private JButton[] abcButtons;
	private BorderLayout la;
	private JLabel word;
	
	private JPanel abcContainer;
	private JPanel wordContainer;
	
	public WindowHanger() 
	{
		super("Akasztofa tajtek");
		la = new BorderLayout(10, 10);
		game = new Hanger("test heme");
		String h = game.toHidden();
		this.setLayout(la);
		setResizable(false);
		setVisible(true);
		initializeComponents();
		pack();
	}
	
	public void initializeComponents()
	{
		game = new Hanger("test elek");
		// ADDING ABC BUTTONS 
		abcContainer = new JPanel();
		abcContainer.setLayout(new GridLayout(5, 6));
		
		abcButtons = new JButton[26];
		add(abcContainer, BorderLayout.SOUTH);
		// 65-90 ASCII capital letters in abc order
		int abc_ASCII = 65;
		for (int i = 0; i < abcButtons.length; i++)
		{
			abcButtons[i] = new JButton("" + (char)abc_ASCII);
			abcButtons[i].setFont(new Font(new JPanel().getFont().getName(), Font.PLAIN, 25));
			abc_ASCII++;
		}
		
		for (JButton btn : abcButtons)
		{
			btn.addActionListener(this);
			abcContainer.add(btn);
			
		}
		
		// ----------------------
		
		// WORDS
		//wordContainer = new JPanel();
		//wordContainer.add();
		word = new JLabel(game.toHidden());
		word.setText(game.checkAndUpdateWord(word.getText(), 'e'));
		game.checkAndUpdateWord(word.getText(), 't');
		Font font = word.getFont();
		word.setFont(new Font(font.getName(), Font.PLAIN, 30));
		
		//add(ne, BorderLayout.SOUTH);
		add(word, BorderLayout.WEST);
		
		//pack();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
	}
	
}
