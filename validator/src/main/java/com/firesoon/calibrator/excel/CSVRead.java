package com.firesoon.calibrator.excel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.firesoon.calibrator.pojo.FinalStatement;

public class CSVRead
{
	static FinalStatement finalStatement;
	public static List<FinalStatement> list = new ArrayList<>();
	public static Map<String, Object> map2 = new HashMap<String, Object>();
	
	 public static void readCsvFile(String fileName) throws IOException 
	 {
		 if(fileName.length() > 0)
		 {
			 DataInputStream in = new DataInputStream(new FileInputStream(new File(fileName)));
		        BufferedReader br= new BufferedReader(new InputStreamReader(in,"utf-8"));
		        Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(br);
		        //Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fileReader);

		        for (CSVRecord record : records) 
		        {
		        	String ipStr = record.get("PID");
		        	String str = ipStr;
		        	if(ipStr.indexOf("I") >= 0)
		        	{
		        		str = ipStr.substring(ipStr.indexOf("I"));
		        	}
		        	
		        	finalStatement = new FinalStatement(record.get(0),str,record.get("JS_SURG_CODE"),record.get("JS_SURG_NAME"),record.get("JS_DIAG_CODE"),record.get("JS_DIAG_NAME"),
		                               record.get("REASON"));
		            list.add(finalStatement);
		            map2.put(finalStatement.getpId(), finalStatement);
		        }
	        
	      }   
	 }
}
