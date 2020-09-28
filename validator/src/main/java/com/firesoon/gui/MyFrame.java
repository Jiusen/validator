package com.firesoon.gui;

import com.firesoon.calibrator.util.FsCheck;
import com.firesoon.validate.TValidate;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.log4j.PropertyConfigurator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFrame extends JFrame
{
	

	private JTextField textField=new JTextField(20);
	private JTextField textField2=new JTextField(20);
	private JTextField textField3 = new JTextField(20);
	private String groupFilePath; // 分组参数路径
	private String settlementFilePath;  // 结算单路径
	private String resultPath; // 结果存放路径
	private String city;  //城市
	
	public MyFrame(String title)
	{
		super(title);
		
		Box root=Box.createVerticalBox();
		this.setContentPane(root);

		JButton resButton = new JButton("开始校验");; //校验按钮
		//边框
		AfBorder.addMargin(root, 20);
		
		//row1
		JButton button=new JButton("选择");
		Box row1=Box.createHorizontalBox();
		AfBorder.addMargin(row1, 10);
		JLabel nameLabel=new JLabel("分组参数");
		textField.setText("请选择分组参数文件");
		textField.setForeground(Color.GRAY);
		row1.add(nameLabel);
		row1.add(Box.createHorizontalStrut(10));
		row1.add(textField);
		row1.add(Box.createHorizontalStrut(10));
		row1.add(button);
		row1.setMaximumSize(new Dimension(99999,50));
		
		//row2
		JButton button2=new JButton("选择");
		Box row2=Box.createHorizontalBox();
		AfBorder.addMargin(row2, 10);
		JLabel nameLabel2=new JLabel("结算单表");
		textField2.setText("请选择结算单文件");
		textField2.setForeground(Color.GRAY);
		row2.add(nameLabel2);
		row2.add(Box.createHorizontalStrut(10));
		row2.add(textField2);
		row2.add(Box.createHorizontalStrut(10));
		row2.add(button2);
		row2.setMaximumSize(new Dimension(99999,50));

		//row3
		JButton button3=new JButton("选择");
		Box row3=Box.createHorizontalBox();
		AfBorder.addMargin(row3, 10);
		JLabel nameLabel3=new JLabel("结果存放路径");
		textField3.setText("请选择路径");
		textField3.setForeground(Color.GRAY);
		row3.add(nameLabel3);
		row3.add(Box.createHorizontalStrut(10));
		row3.add(textField3);
		row3.add(Box.createHorizontalStrut(10));
		row3.add(button3);
		row3.setMaximumSize(new Dimension(99999,50));
		//row3
		Box row5=Box.createHorizontalBox();
		AfBorder.addMargin(row5, 10);
		JLabel sexLabel3=new JLabel("地区选择");
		String[] list = {"《中国》", "台州", "佛山"};
		final JComboBox sexField = new JComboBox(list);
		row5.add(sexLabel3);
		row5.add(Box.createHorizontalStrut(10));
		row5.add(sexField);
		row5.setMaximumSize(new Dimension(99999,50));

		//
		Box row4=Box.createHorizontalBox();
		row4.add(resButton);
		
		root.add(row1);
		root.add(Box.createVerticalStrut(20));
		root.add(row2);
		root.add(Box.createVerticalStrut(20));
		root.add(row3);
		root.add(Box.createVerticalStrut(20));
		root.add(row5);
		root.add(Box.createVerticalStrut(40));
		root.add(row4);

		//下拉框监听事件ֵ
		sexField.addItemListener(new ItemListener() {

	            @Override
	            public void itemStateChanged(ItemEvent ie) {
	                if(ie.getStateChange() == 1) {
	        			city = sexField.getSelectedItem().toString();
	                }
	            }

	        });

		//文件1监听事件
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				groupFilePath = test(textField);
			}

		});

		//文件2监听事件
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				settlementFilePath = test(textField2);
			}

		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resultPath = test(textField3);
			}
		});
		resButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				long l = 0;
				if (city.equals("台州")) {
					config();
					 l = TValidate.readFileAndCheck(groupFilePath, settlementFilePath, resultPath);
				}else if (city.equals("佛山")){
					l = FsCheck.begin(groupFilePath,settlementFilePath,resultPath);
				}
				hint(l);
			}
		});
	}

	private void config(){
		URL resource = this.getClass().getClassLoader().getResource("log4j.proerties");
		PropertyConfigurator.configure(resource);
	}


	private String test(JTextField field)
	{
		//文件选择器
		JFileChooser chooser=new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//单文件选择
		int ret=chooser.showOpenDialog(this);
		if(ret==JFileChooser.APPROVE_OPTION)
		{
			//选择到文件的路径
			File file=chooser.getSelectedFile();
			field.setText(file.getAbsolutePath());
			return file.getAbsolutePath();
		}
		
		return "";
	}
	
	//短消息提示框
	private void hint(long l)
	{
		JsToaster toaster=new JsToaster();
		toaster.setMessage("校验完毕！"+"共耗时:"+l+"ms");
		toaster.showPopup(this);

	}
}
