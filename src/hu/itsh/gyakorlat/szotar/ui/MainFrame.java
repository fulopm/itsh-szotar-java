package hu.itsh.gyakorlat.szotar.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuOpenHelpAbout;
import hu.itsh.gyakorlat.szotar.ui.dialogs.DialogHelpAbout;

public class MainFrame extends JFrame {

	MainContentPane contentPane;

	private JMenuBar menuBarMain;

	JMenu menuFile;
	JMenuItem menuItemDbOpen;
	JMenuItem menuItemDbSave;
	JMenuItem menuItemExit;

	JMenu menuEdit;
	JMenuItem menuItemWordsAdd;
	JMenuItem menuItemWordsDelete;
	JMenuItem menuItemWordsShow;

	JMenu menuPractice;
	JMenuItem menuItemTestsWriteTest;
	JMenuItem menuItemTestsListeningTest;

	JMenu menuHelp;
	JMenuItem menuItemHelpAbout;
	
	public MainFrame() {
		initComponents();
		setContentPane(contentPane);
		setJMenuBar(menuBarMain);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640,480);
		setTitle(SharedConstants.APP_NAME + " - " + SharedConstants.APP_VERSION);
		setLocationRelativeTo(null);
	}

	void initComponents() {
		contentPane = new MainContentPane();

		menuBarMain = new JMenuBar();

		menuFile = new JMenu("Fájl");
		menuItemDbOpen = new JMenuItem("Adatbázis megnyitás");
		menuItemDbSave = new JMenuItem("Adatbázis mentése");
		menuItemExit = new JMenuItem("Kilépés a programból");

		menuFile.add(menuItemDbOpen);
		menuFile.add(menuItemDbSave);
		menuFile.add(menuItemExit);

		menuBarMain.add(menuFile);
		
		menuHelp = new JMenu("Segítség");
		menuItemHelpAbout = new JMenuItem("A programról");
		menuItemHelpAbout.setAction(new ActionMenuOpenHelpAbout(contentPane));
		menuHelp.add(menuItemHelpAbout);
		
		
		menuBarMain.add(menuHelp);

	}
	
	public static void setLaF() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (info.getName().contains("Nimbus")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	

}
