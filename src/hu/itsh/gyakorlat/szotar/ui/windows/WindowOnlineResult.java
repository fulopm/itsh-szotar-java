package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import hu.itsh.gyakorlat.szotar.dictionaries.OnlineWord;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionAddRow;
import net.java.dev.designgridlayout.DesignGridLayout;

public class WindowOnlineResult extends InternalWindow implements ActionListener
{
	private ArrayList<JRadioButton> resultSel; 
	private ArrayList<JLabel> resultPan;
	private ArrayList<OnlineWord> words;
	private ButtonGroup bGroup;
	private JButton addDict;
	private JButton close;
	private int selected;

	public WindowOnlineResult(ArrayList<OnlineWord> words) 
	{
		super("Online szótár talalatok");
		
		this.words = words;
		
		DesignGridLayout dg = new DesignGridLayout(this.contentPane);
		initializeComponents();
		setVisible(true);
		setResizable(false);
		//this.getLayeredPane().setLayer(this, JLayeredPane.POPUP_LAYER.intValue());
		for (JRadioButton rb : resultSel)
		{
			rb.addActionListener(this);
			bGroup.add(rb);
			dg.row().grid().add(rb);
		}
		dg.row().grid().add(addDict).add(close);
		pack();
		
	}
	
	private void initializeComponents()
	{
		bGroup = new ButtonGroup();
		resultSel = new ArrayList<>();
		resultPan = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		int perCounter = 0;
		
		for (int i = 0; i < words.size(); i++)
		{
			
			//sb.append(words.get(i).meaningsToString());
			for (char c : words.get(i).meaningsToString().toCharArray())
			{
				if (c == '/')
				{
					//sb.append(c);
					perCounter++;
				}
				if (perCounter  >= 8)
					break;
				else
					sb.append(c);
			}
			resultSel.add(new JRadioButton("<html><b>[" + words.get(i).getWordClass() + "] " + words.get(i).getSourceWord() + ":</b> " + sb + "</html>"));
			sb.delete(0, sb.length());
			perCounter = 0;
		}
		close = new JButton("Bezaras");	
		close.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				
			}  });
		addDict = new JButton("Hozzaadas a szotarhoz");
		addDict.addActionListener(new ActionListener()
		{
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				WindowDbAddRow addR = new WindowDbAddRow();			
				mainContentPane.addInternalWindow(addR);
				addR.fieldWord.setText(words.get(selected).getSourceWord());
				addR.fieldHun0.setText(words.get(selected).meaningsToString());
				addR.fieldWordClass.setSelectedItem(words.get(selected).getWordClass());
				
				
			}});
		addDict.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		for (int i = 0; i < resultSel.size(); i++)
		{
			if (arg0.getSource().equals(resultSel.get(i)))
			{
				selected = i;
			}
		}	
		
		addDict.setEnabled(true);	
	}
}
