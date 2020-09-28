package com.firesoon.calibrator.excel;

import com.firesoon.calibrator.pojo.Item;
import com.firesoon.calibrator.pojo.Result;
import com.firesoon.calibrator.util.Json2Pojo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVFenZuRead
{
	private static Result result;
	private static List<Result> list = new ArrayList<>();	//用于存储所有记录
	public static Map<String, Object> map1 = new HashMap<>();  //用于存储每一条记录
	public static Map<String, List<Result>> placeMap1 = new HashMap<>();  //根据医院将所有记录进行分类
	public static Map<String, Double> placeNumMap = new HashMap<>();  //根据医院将所有记录进行分类,记录每一个分类的数量

	public static double cyzdNum;  //计算次要诊断的数量
	public static double sum;  //计总人数
	
	 public static void readCsvFile(String fileName) throws IOException 
	 {
		 if(fileName.length() > 0)
		 {
		 	int i = 1;


			 DataInputStream in = new DataInputStream(new FileInputStream(new File(fileName)));
		        BufferedReader br= new BufferedReader(new InputStreamReader(in,"utf-8"));
		        Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(br);
		        //Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fileReader);


		        for (CSVRecord record : records) 
		        {
					result = new Result();
		        	result.MONTH = record.get("MONTH");

		        	result.PID = record.get("PID");

		        	result.BILL_DRGCODE = record.get("BILL_DRGCODE");

		        	result.SYS_DRGCODE = record.get("SYS_DRGCODE");

		        	result.REQUEST_JSON = record.get("REQUEST_JSON");

					//json格式转化为pojo格式
					Item item = (Item) Json2Pojo.json2pojo(result.REQUEST_JSON, Item.class);
					result.item = item;

					result.source = record.get("SOURCE");

					//所有医院分别对应的数量总数
					if(placeNumMap.containsKey(result.source)) //如果已经包含了某个key，就把这条记录插进对应的key中
					{
						Double placeNum = placeNumMap.get(result.source);  //对应此key里面的数据+1；
						placeNum ++;
						placeNumMap.put(result.source, placeNum); //添加对应key的对应结果
					}else //不存在某个key的情况
					{
						placeNumMap.put(result.source, 1.0); //添加对应key的对应结果
					}

					//添加到总数据管理
					list.add(result);
					//添加到总map1中
					map1.put(result.getPID(), result);

					//各个医院分别添加
					if(placeMap1.containsKey(result.source)) //如果已经包含了某个key，就把这条记录插进对应的key中
					{
						List lists = placeMap1.get(result.source);
						lists.add(result);
					}else //不存在某个key的情况
					{
						List<Result> lists = new ArrayList<>();
						lists.add(result);
						placeMap1.put(result.source, lists); //添加对应key的对应结果
					}
		        }
	        
	      }
	 }
}
