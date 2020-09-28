package com.firesoon.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class AfBorder
{

	public static void addPadding(JComponent c, int size)
	{
		addPadding(c, size, size, size, size);
	}
	

	public static void addPadding(JComponent c, int top, int left, int bottom, int right)
	{
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		Border oldBorder = c.getBorder();
		if(oldBorder != null)
		{
			border = BorderFactory.createCompoundBorder(oldBorder, border);
		}
		c.setBorder(border);
	}
	
	public static void addMargin(JComponent c, int size)
	{
		addMargin(c, size, size, size, size);
	}
	
	public static void addMargin(JComponent c, int top, int left, int bottom, int right)
	{
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		Border oldBorder = c.getBorder();
		if(oldBorder != null)
		{
			border = BorderFactory.createCompoundBorder(border, oldBorder);
		}
		c.setBorder(border);
	}
	

}
