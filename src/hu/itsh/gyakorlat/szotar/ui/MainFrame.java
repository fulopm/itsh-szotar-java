package hu.itsh.gyakorlat.szotar.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Painter;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuDbOpen;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuDbShow;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuExit;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuOpenHelpAbout;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuOpenUserDocument;
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
	JMenuItem menuItemUserDocument;

	public MainFrame() {
		initComponents();
		setContentPane(contentPane);
		setJMenuBar(menuBarMain);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setTitle(SharedConstants.APP_NAME + " - " + SharedConstants.APP_VERSION);
		setMinimumSize(new Dimension(800, 600));
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
		menuItemUserDocument = new JMenuItem("Felhasznaloi dokumentacio");
		menuItemHelpAbout.setAction(new ActionMenuOpenHelpAbout(contentPane));
		menuHelp.add(menuItemHelpAbout);
		menuItemUserDocument.setAction(new ActionMenuOpenUserDocument(contentPane));
		menuHelp.add(menuItemUserDocument);
		
		menuBarMain.add(menuPractice);

		menuBarMain.add(menuTranslate);

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
		
		UIManager.getLookAndFeelDefaults().put("DesktopPane[Enabled].backgroundPainter", new DesktopPainter());
		UIManager.put("nimbusSelection", new Color(139, 41, 142));
		UIManager.getLookAndFeelDefaults().put("nimbusBlueGrey", new Color(169, 176, 190));
		UIManager.put("nimbusSelectionBackground", new Color(139, 41, 142));
		UIManager.getLookAndFeelDefaults().put("MenuBar:Menu[Selected].backgroundPainter", new FillPainter(new Color(139, 41, 142)));
	}

	static class DesktopPainter implements Painter<JComponent> {

		private Image image;
		{
			try {
				image = ImageIO.read(new File("ITSH1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paint(Graphics2D g, JComponent object, int width, int height) {
			  int imgWidth = image.getWidth(null);
		        int imgHeight = image.getHeight(null);

		        double imgAspect = (double) imgHeight / imgWidth;

		        int canvasWidth = object.getWidth();
		        int canvasHeight = object.getHeight();

		        double canvasAspect = (double) canvasHeight / canvasWidth;

		        int x1 = 0; // top left X position
		        int y1 = 0; // top left Y position
		        int x2 = 0; // bottom right X position
		        int y2 = 0; // bottom right Y position

		        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
		            // the image is smaller than the canvas
		            x1 = (canvasWidth - imgWidth)  / 2;
		            y1 = (canvasHeight - imgHeight) / 2;
		            x2 = imgWidth + x1;
		            y2 = imgHeight + y1;

		        } else {
		            if (canvasAspect > imgAspect) {
		                y1 = canvasHeight;
		                // keep image aspect ratio
		                canvasHeight = (int) (canvasWidth * imgAspect);
		                y1 = (y1 - canvasHeight) / 2;
		            } else {
		                x1 = canvasWidth;
		                // keep image aspect ratio
		                canvasWidth = (int) (canvasHeight / imgAspect);
		                x1 = (x1 - canvasWidth) / 2;
		            }
		            x2 = canvasWidth + x1;
		            y2 = canvasHeight + y1;
		        }
		        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
		}

	}

	static class FillPainter implements Painter<JComponent> {

		private final Color color;

		FillPainter(Color c) {
			color = c;
		}

		@Override
		public void paint(Graphics2D g, JComponent object, int width, int height) {
			g.setColor(color);
			g.fillRect(0, 0, width, height);
		}
	}
}
