package com.firesoon.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.JPanel;

public class JsLabel extends JPanel
{
	public int wrappingWidth = 0; 
	public String text ;
	public int lineGap = 4; 
	public int padding = 8; 
	
	public JsLabel()
	{
	}
	public JsLabel(String text)
	{
		this.text = text;
	}
	
	@Override
	public Dimension getPreferredSize()
	{				
		Font font = this.getFont();
		int maxWidth = (wrappingWidth > 0 ? wrappingWidth :9999);
		
		
		if(this.text == null || text.length() == 0)
		{
			return new Dimension(padding*2, font.getSize() + padding*2);
		}
		FontRenderContext frc = new MyFontRenderContext();
		AttributedString styledText = new AttributedString(text,font.getAttributes());
		AttributedCharacterIterator textIter = styledText.getIterator();
		LineBreakMeasurer measurer = new LineBreakMeasurer(textIter, frc);
				
		int width = 0;
		int height = 0;
		while (measurer.getPosition() < text.length())
		{
			TextLayout layout = measurer.nextLayout(maxWidth);

			Rectangle2D rect = layout.getBounds();
			int textWidth = (int) (rect.getX() + rect.getWidth());
			if(textWidth > width)  
				width = textWidth + 1;
			
			height += layout.getLeading() + layout.getAscent() + layout.getDescent();
			height += this.lineGap;
		}

		width += padding *2;
		height += padding *2;
		return new Dimension(width, height);
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.setPaint(Color.BLUE);

		if(this.text== null || text.length()==0)return;
		
		FontRenderContext frc = g2d.getFontRenderContext();
		AttributedString styledText = new AttributedString(text, g2d.getFont().getAttributes());
		AttributedCharacterIterator iter = styledText.getIterator();
		LineBreakMeasurer measurer = new LineBreakMeasurer(iter, frc);

		int maxWidth = (wrappingWidth > 0 ? wrappingWidth :9999) ;
		int x = padding;
		int y = padding;		
		while (measurer.getPosition() < text.length())
		{
			TextLayout layout = measurer.nextLayout(maxWidth);

			y += (layout.getAscent());
			layout.draw(g2d, x, y);

			y += layout.getDescent() + layout.getLeading();			
			y += lineGap; 
		}
	}

	////////// Getter / Setter /////////
	
	public int getWrappingWidth()
	{
		return wrappingWidth;
	}
	
	public void setWrappingWidth(int wrappingWidth)
	{
		this.wrappingWidth = wrappingWidth;
		this.repaint();
	}

	public String getText()
	{
		return text;		
	}
	
	public void setText(String text)
	{
		this.text = text;
		this.repaint();
	}

	public int getLineGap()
	{
		return lineGap;
	}
	
	public void setLineGap(int lineGap)
	{
		this.lineGap = lineGap;
		this.repaint();
	}
	public int getPadding()
	{
		return padding;
	}
	public void setPadding(int padding)
	{
		this.padding = padding;
	}
	
	
	// 鍥� FontRenderContext 鏄� protected鐨�
	// 涓轰簡娴嬮噺鏂囨湰鐨勯珮搴︼紝鎵�浠ヨ嚜宸卞缓浜嗕竴涓畠鐨勫瓙绫�
	private static class MyFontRenderContext extends FontRenderContext
	{
		public MyFontRenderContext()
		{			
		}
	}


}
