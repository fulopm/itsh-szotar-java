package hu.itsh.gyakorlat.szotar.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;	
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Painter;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuDbOpen;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuDbShow;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuExit;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuOpenHelpAbout;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuStatistics;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuTestSelect;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuTestType;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuTranslate;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionSaveDb;
import hu.itsh.gyakorlat.szotar.ui.actions.hanger.ActionMenuHanger;
import hu.itsh.gyakorlat.szotar.ui.windows.InternalWindow;

public class MainFrame extends JFrame {

	static MainContentPane contentPane;

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


	JMenuItem menuItemStatistics;

	JMenu menuTranslate;
	JMenuItem menuWordBook;
	JMenuItem menuItemTestsHanger;
	

	JMenu menuHelp;
	JMenuItem menuItemHelpAbout;

	public MainFrame() {
		initComponents();
		setContentPane(contentPane);
		setJMenuBar(menuBarMain);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setTitle(SharedConstants.APP_NAME + " - " + SharedConstants.APP_VERSION);
		setMinimumSize(new Dimension(800,600));
		new ActionMenuDbOpen(contentPane).actionPerformed(null);

		setLaF();
	}

	void initComponents() {
		contentPane = new MainContentPane();
		menuBarMain = new JMenuBar();
		InternalWindow.mainContentPane = contentPane;

		menuFile = new JMenu("Fajl");
		menuItemDbOpen = new JMenuItem("Adatbazis megnyitas");
		menuItemDbOpen.setAction(new ActionMenuDbOpen(contentPane));
		menuItemDbSave = new JMenuItem("Adatbazis mentese");
		menuItemDbSave.setAction(new ActionSaveDb());

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
		menuItemTestsHanger = new JMenuItem("Akasztofa");
		menuItemTestsHanger.setAction(new ActionMenuHanger(contentPane));
		
		menuItemStatistics = new JMenuItem("Statisztika");
		menuItemStatistics.setAction(new ActionMenuStatistics(contentPane));

		menuPractice.add(menuItemTestsListeningTest);
		menuPractice.add(menuItemTestsSelectTest);
		menuPractice.addSeparator();
		menuPractice.add(menuItemStatistics);
		menuPractice.addSeparator();
		menuPractice.add(menuItemTestsHanger);

		menuTranslate = new JMenu("Forditas");
		menuWordBook = new JMenuItem("Szoszedet, mondatforditas");
		menuWordBook.setAction(new ActionMenuTranslate(contentPane));
		menuTranslate.add(menuWordBook);

		menuBarMain.add(menuFile);

		menuHelp = new JMenu("Segitseg");
		menuItemHelpAbout = new JMenuItem("A programrol");
		menuItemHelpAbout.setAction(new ActionMenuOpenHelpAbout(contentPane));
		menuHelp.add(menuItemHelpAbout);

		menuBarMain.add(menuPractice);

		menuBarMain.add(menuTranslate);

		menuBarMain.add(menuHelp);

	}

	public static void setLaF() {
		UIManager.put("DesktopPane[Enabled].backgroundPainter", new DesktopPainter());
		
		UIManager.put("nimbusBase", new Color(139, 41, 142));
		UIManager.put("nimbusBlueGrey", new Color(169,176,190));
		UIManager.put("nimbusSelectionBackground", new Color(139,41,142));
		
		

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


	static class DesktopPainter implements Painter<JComponent> {
java.awt.Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - contentPane.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - contentPane.getHeight()) / 2);
        
		private Image image;
		{
			try {
				image = ImageIO.read(new File("ITSH1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paint(Graphics2D g, JComponent object, int width, int height) {
			g.drawImage(image, 0, 0, 1600, 970, null);

		}

	}
}
