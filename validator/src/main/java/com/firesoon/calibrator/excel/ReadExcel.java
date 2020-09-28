package com.firesoon.calibrator.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.firesoon.calibrator.check.Check;
import com.firesoon.calibrator.pojo.Rate;
import com.firesoon.calibrator.pojo.Result;
import com.firesoon.calibrator.util.Write2Excel3;

public class ReadExcel
{
	public static Map<String, List> mapReason = new HashMap<>();
	public static Map<String, Rate> rateMap = new HashMap<>();  //记录每一个医院对应的rate
	
	public ReadExcel(String readPath, String exportPath) throws Exception
	{		
//
//		File file = new File(readPath);
//		ExcelParser excel = new ExcelParser();
//
//		// 从 Excel表要中提取数据
//		InputStream in = new FileInputStream(file);

		CSVFenZuRead.readCsvFile(readPath);

		
		//一系列的检查操作
		for(String s : CSVFenZuRead.placeMap1.keySet())
		{
			Rate rate = new Rate();
			rate.cost = new double[9];
			
    		for(int i=0; i<CSVFenZuRead.placeMap1.get(s).size(); i++)
    		{
				if(CSVFenZuRead.placeMap1.get(s).get(i).getBILL_DRGCODE().equalsIgnoreCase
						(CSVFenZuRead.placeMap1.get(s).get(i).getSYS_DRGCODE())){

					CSVFenZuRead.placeMap1.get(s).get(i).setReason(null);
					continue;
				}

    			Check.finalNotid(CSVFenZuRead.placeMap1.get(s).get(i), rate);
    			Check.lostCheck1(CSVFenZuRead.placeMap1.get(s).get(i), rate); //1.1
    			Check.lostCheck2(CSVFenZuRead.placeMap1.get(s).get(i), rate); //1.2
    			Check.lostCheck3(CSVFenZuRead.placeMap1.get(s).get(i), rate); //1.3
    			Check.takeWrongCheck(CSVFenZuRead.placeMap1.get(s).get(i), rate); //2.1
    			Check.codingCheck(CSVFenZuRead.placeMap1.get(s).get(i), rate);
    			Check.formatCheck(CSVFenZuRead.placeMap1.get(s).get(i), rate);
    			Check.groupWrongCheck(CSVFenZuRead.placeMap1.get(s).get(i), rate);
    			Check.ungroupedCheck(CSVFenZuRead.placeMap1.get(s).get(i), rate);

				CSVFenZuRead.placeMap1.get(s).get(i).setReason(Check.reason);
    			Check.reason = new ArrayList<>();  //重置
    		}
    		rateMap.put(s, rate);
		}
		writerResult(CSVFenZuRead.placeMap1, exportPath);  //写入所有医院list
	}
	
	//写入文件
	public static void writerResult(Map<String, List<Result>> placeMap1, String resFilePath)
	{
        // 写入数据到工作簿对象内
        Workbook workbook = Write2Excel3.exportData(placeMap1);
        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            String exportFilePath = resFilePath+"\\Fo-shan_errorResult.xlsx";
            File exportFile = new File(exportFilePath);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }

            fileOut = new FileOutputStream(exportFilePath);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            System.out.println("输出excel error:"+e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                System.out.println("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
        }
    }
}
