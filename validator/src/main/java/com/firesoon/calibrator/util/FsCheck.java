package com.firesoon.calibrator.util;

import com.firesoon.calibrator.excel.CSVRead;
import com.firesoon.calibrator.excel.DiagReadExcel;
import com.firesoon.calibrator.excel.ReadExcel;
import com.firesoon.calibrator.excel.ReadExcel2;
import com.firesoon.calibrator.excel.ReadMarkExcel;
import com.firesoon.calibrator.excel.SurgReadExcel;

/**
 * 
 * @author Jiusen.Guo
 * 2020/08/20
 *
 */
public class FsCheck
{
	public static int sum = 0 ;
	static long l1, l2;
	public static long begin(String sysPath, String finalPath, String exportPath)
	{

		System.out.println("佛山 - 校验开始");
		try {
			l1 = System.currentTimeMillis();
			SurgReadExcel surgReadExcel = new SurgReadExcel("surg_version_20200506.xlsx");//
			DiagReadExcel diagReadExcel = new DiagReadExcel("diag_version_20200506.xlsx");//

			//标准表的读取
			ReadMarkExcel readMarkExcel = new ReadMarkExcel("佛山标记表.xlsx");
			
			try {
				ReadExcel2 readExcel = new ReadExcel2(finalPath);   //文件2的读取
			}catch(Exception e)
			{
				CSVRead.list = null;
				CSVRead.map2 = null;
				
			}
			

			if(exportPath.length() <= 0)
				exportPath = "D:\\";   //默认在D盘根目录
			
			ReadExcel readExcel2 = new ReadExcel(sysPath, exportPath);   //文件1的读取

			l2 = System.currentTimeMillis();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("文件取错了，兄弟~");
		}
		
		System.out.println("佛山 - 校验结束，总耗时: " + (l2 - l1));
		return (l2 - l1);
	}
}
