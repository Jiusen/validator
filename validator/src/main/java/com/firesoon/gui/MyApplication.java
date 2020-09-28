package com.firesoon.gui;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MyApplication
{

	private static void createGUI()
	{
		JFrame frame = new MyFrame("校验器");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(600, 400);
		
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	    Image icon = Toolkit.getDefaultToolkit().getImage("src/check.jpg");
	    frame.setIconImage(icon);
	}
	
	public static void main(String[] args)
	{

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}


		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createGUI();
			}
		});

	}

}
