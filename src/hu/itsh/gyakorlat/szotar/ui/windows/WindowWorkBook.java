package hu.itsh.gyakorlat.szotar.ui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.dictionaries.OnlineDictionary;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.io.user.WordBook;
import net.java.dev.designgridlayout.DesignGridLayout;

public class WindowWorkBook extends InternalWindow
{
	private JTextArea textInput;
	private JButton organize;
	private JButton clear;
	private JTable words;
	private DefaultTableModel tableModel;
	private final String[] columnNames = {"Eredeti", "Forditott"};
	private final String[] buttonNames = {"Online kereses", "Szotarhoz adas", "Megsem"};
	
	public WindowWorkBook()
	{
		super("Szövegforditás");
		super.setResizable(false);
		DesignGridLayout layout = new DesignGridLayout(this.contentPane);
		initializeComponents();
		layout.row().grid().add(new JScrollPane(words)).add(new JScrollPane(textInput));
		layout.row().grid().add(clear).add(organize);
		pack();
		setVisible(true);
	}
	
	public void initializeComponents()
	{
		textInput = new JTextArea(27, 50);
		textInput.setLineWrap(true);
		textInput.setWrapStyleWord(true);
		
		tableModel = new DefaultTableModel(columnNames, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) 
		    {
		       return false;
		    }
		};
		
		words = new JTable(tableModel);
		words.addMouseListener(new MouseListener() 
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				int row = words.rowAtPoint(e.getPoint());
				int col = words.columnAtPoint(e.getPoint());
				
				if (col == 0 && tableModel.getValueAt(row, col+1).toString().equals("UNKNOWN"))
				{
					int change = JOptionPane.showOptionDialog(new WindowWorkBook(), "Nem talaltam semmit!", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonNames, buttonNames[0]);
					switch (change)
					{
						case (JOptionPane.YES_OPTION):
							
						try
						{
							Elements onlineWords = OnlineDictionary.translate(tableModel.getValueAt(row, col).toString(), OnlineDictionary.HUNGARIAN);
							String meanings = "<html>";
							String[] line;
							boolean isSource = true;
							String craft = "<b>";
							for (Element wordSet : onlineWords)
							{
								if (wordSet.text().contains("HU") || wordSet.text().contains("EN"))
								{
									line = wordSet.text().split("\\s+");
									for (String wd : line)
									{
										if (!wd.contains("{"))
										{
											if (wd.equals("HU") || wd.equals("EN"))
											{
												isSource = false;
												meanings += craft + "</b>";
												craft = "<b>";
											}
											else if (isSource)
												craft += wd + " ";
											else if (!isSource && !wd.equals("HU") && !wd.equals("EN"))
												meanings += wd + " ";
										 }
									}
								}
								isSource = true;
								meanings +=  "<br>";
							}
							meanings += "</html>";
							System.out.println(meanings);
							JOptionPane.showMessageDialog(new WindowWorkBook(), meanings, "Lehetséges jelentések", JOptionPane.OK_CANCEL_OPTION);
							
						} catch (IOException e1)
						{
							e1.printStackTrace();
						}
						break;
						case (JOptionPane.NO_OPTION):
							
						break;
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{}

			@Override
			public void mouseExited(MouseEvent e)
			{}

			@Override
			public void mousePressed(MouseEvent e)
			{}

			@Override
			public void mouseReleased(MouseEvent e)
			{}	
			});
		
		organize = new JButton("Fordit");
		organize.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!textInput.getText().isEmpty())
				{
					Object[] row = new Object[2];
					WordBook wb = new WordBook(textInput.getText().replaceAll("[-+.^:,?!]", ""));
					ArrayList<String> sourceWords = wb.getSortedWords();
					if (tableModel.getRowCount() != 0)
						clearTable();
					
					for (int i = 0; i < sourceWords.size(); i++)
					{			
						// 55
						row[0] = sourceWords.get(i);
						Row rowHit = Database.dict.searchByWord(sourceWords.get(i));
						row[1] = rowHit != null ? rowHit.getHun0() : "UNKNOWN";
						tableModel.addRow(row);	
					}
				}
			}
			
			private void clearTable()
			{
				while (tableModel.getRowCount() != 0)
				{
					for (int i = 0; i < tableModel.getRowCount(); i++)
					{
						tableModel.removeRow(i);
					}
				}
			}
		});
		clear = new JButton("Torol");
		clear.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				while (tableModel.getRowCount() != 0)
				{
					for (int i = 0; i < tableModel.getRowCount(); i++)
					{
						tableModel.removeRow(i);
					}
				}		
			}
		});
	}
}
