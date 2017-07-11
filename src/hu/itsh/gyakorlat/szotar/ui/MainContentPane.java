package hu.itsh.gyakorlat.szotar.ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import hu.itsh.gyakorlat.szotar.ui.windows.InternalWindow;

public class MainContentPane extends JDesktopPane implements InternalFrameListener {

	public MainContentPane() {
		initComponents();
	}

	public void initComponents() {

	}

	public void addInternalWindow(InternalWindow w) {
		add(w);
		
		moveToFront(w);
		
	}


	@Override
	public void internalFrameActivated(InternalFrameEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0)
	{
		System.out.println("hé");
		
	}


	

}
