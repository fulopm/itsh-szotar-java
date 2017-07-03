package hu.itsh.gyakorlat.szotar.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.tts.TTS;
import hu.itsh.gyakorlat.szotar.tts.TestFrame;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuDbOpen;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuDbShow;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuExit;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuOpenHelpAbout;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuTestSelect;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuTestType;
import hu.itsh.gyakorlat.szotar.ui.dialogs.InternalWindow;
import hu.itsh.gyakorlat.szotar.ui.dialogs.WindowHelpAbout;

public class MainFrame extends JFrame{

	MainContentPane contentPane;

	private JMenuBar menuBarMain;

	JMenu menuFile;
	JMenuItem menuItemDbOpen;
	JMenuItem menuItemDbSave;
	JMenuItem menuItemDbShow;
	JMenuItem menuItemExit;

	JMenu menuEdit;
	JMenuItem menuItemWordsAdd;
	JMenuItem menuItemWordsDelete;
	JMenuItem menuItemWordsShow;

	JMenu menuPractice;
	JMenuItem menuItemTestsSelectTest;
	JMenuItem menuItemTestsListeningTest;

	JMenu menuHelp;
	JMenuItem menuItemHelpAbout;
	
	public MainFrame() {
		initComponents();
		setContentPane(contentPane);
		setJMenuBar(menuBarMain);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setTitle(SharedConstants.APP_NAME + " - " + SharedConstants.APP_VERSION);
		new ActionMenuDbOpen(contentPane).actionPerformed(null);
	}


	void initComponents() {
		contentPane = new MainContentPane();
		menuBarMain = new JMenuBar();
		InternalWindow.mainContentPane = contentPane;
		
		
		menuFile = new JMenu("Fajl");
		menuItemDbOpen = new JMenuItem("Adatbazis megnyitas");
		menuItemDbOpen.setAction(new ActionMenuDbOpen(contentPane));
		menuItemDbSave = new JMenuItem("Adatbazis mentese");
		
		menuItemDbShow = new JMenuItem("Adatbázis megjelenitese");
		menuItemDbShow.setAction(new ActionMenuDbShow(contentPane));
		
		menuItemExit = new JMenuItem("Kilepes a programbol");
		menuItemExit.setAction(new ActionMenuExit());

		menuFile.add(menuItemDbOpen);
		menuFile.add(menuItemDbSave);
		menuFile.add(menuItemDbShow);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		
		
		
		menuPractice = new JMenu("Gyakorlas");
		menuItemTestsListeningTest = new JMenuItem("Felovasos teszt");
		menuItemTestsListeningTest.setAction(new ActionMenuTestType(contentPane));
		
		menuItemTestsSelectTest = new JMenuItem("Valasztos teszt");
		menuItemTestsSelectTest.setAction(new ActionMenuTestSelect(contentPane));
		
		menuPractice.add(menuItemTestsListeningTest);
		menuPractice.add(menuItemTestsSelectTest);
		

		menuBarMain.add(menuFile);
		
		menuHelp = new JMenu("Segitseg");
		menuItemHelpAbout = new JMenuItem("A programrol");
		menuItemHelpAbout.setAction(new ActionMenuOpenHelpAbout(contentPane));
		menuHelp.add(menuItemHelpAbout);
		
		menuBarMain.add(menuPractice);
		
		menuBarMain.add(menuHelp);

	}
	
	public static void setLaF() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (info.getName().contains("Nimbus")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	


	
	
	
	

}
