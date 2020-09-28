package com.firesoon.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;


/*
 * js.swing类
 * 用来设计消息提示得效果
 */
public class JsToaster extends JPanel
{
	//使用JsLabel进行测算
	private JsLabel content=new JsLabel();
	private Timer timer; //Swing timer ，定时器技术
	private int delay=1500; //N 毫秒之后自动关闭
	
	//消息窗口，使用(JWindow实现)
	JWindow popup;
	
	public JsToaster()
	{
		//设置JsLabel;
		content.setWrappingWidth(240);
		content.setOpaque(true);
		content.setBackground(Color.LIGHT_GRAY);
		content.setFont(new Font("微软雅黑",Font.PLAIN,14));
		content.setForeground(Color.red);
		
		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);	
	}
	
	//设置消息
	public void setMessage(String message)
	{
		content.setText(message);
	}

	//计算窗口所需大小
	@Override
	public Dimension getPreferredSize() 
	{
		Dimension size=content.getPreferredSize();
		return size;
	}
	
	//显示消息提示
	public void showPopup(Window ownerWindow)
	{
		/*根据主窗口位置计算，将消息显示在主窗口得中心位置*/
		Rectangle rect=ownerWindow.getBounds();
		Dimension size=this.getPreferredSize();
		int x=(int)rect.getCenterX()-size.width/2;
		int y=(int)rect.getCenterY()-size.height/2;
		
		/*创建并显示消息窗口（用JWindow实现）*/
		
		//创建JWindow,参数owner为窗口
		popup=new JWindow(ownerWindow); //主仆关系：ownerWindow关闭时，popup也关闭...
		//设置ContentPane
		popup.getRootPane().setContentPane(this);
		//创建窗口大小
		popup.setSize(this.getPreferredSize());
		//设置窗口显示位置
		popup.setLocation(x, y);
		//显示窗口
		popup.setVisible(true);
		
		//启动定时，自动关闭
		timer=new Timer(5000,new TimerHandler());
		timer.start();
	}
	
	//关闭消息提示
	public void hidePopup()
	{
		if(popup!=null)
		{
			popup.setVisible(false); //隐藏窗口
			popup.dispose();  //销毁窗口
			popup=null;
		}
	}
	
	//
	private class  TimerHandler implements ActionListener
	{
		//开始得时间
		long startTime=System.currentTimeMillis();
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			long pass=System.currentTimeMillis()-startTime;
			int remain=delay-(int)pass;
			
			//已经结束
			if(remain<=0)
			{
				timer.stop();
				hidePopup();
				return;
			}
			
			//倒计时300秒
			if(remain<=300)
			{
				float percent=remain/300.0f;  //0-1
				popup.setOpacity(percent); //透明度
			}
			
		}
		
	}
	
}