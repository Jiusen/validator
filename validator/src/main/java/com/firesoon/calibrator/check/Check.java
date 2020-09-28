package com.firesoon.calibrator.check;

import java.util.ArrayList;
import java.util.List;

import com.firesoon.calibrator.excel.CSVRead;
import com.firesoon.calibrator.excel.DiagExcelParser;
import com.firesoon.calibrator.excel.ReadMarkExcel;
import com.firesoon.calibrator.excel.SurgExcelParser;
import com.firesoon.calibrator.place.PlaceCost;
import com.firesoon.calibrator.pojo.Diag;
import com.firesoon.calibrator.pojo.FinalStatement;
import com.firesoon.calibrator.pojo.Item;
import com.firesoon.calibrator.pojo.Rate;
import com.firesoon.calibrator.pojo.Result;
import com.firesoon.calibrator.pojo.Surg;

/**
 * 
 * @author Jisen.Guo
 * 
 */
public class Check
{
	public static List<String> reason = new ArrayList<>();
	
	public static void finalNotid(Result result, Rate rate)
	{
		Item item = result.item;
		if((FinalStatement)CSVRead.map2.get(item.getPid()) == null)
			reason.add("\n结算单内无该记录: " + item.getPid());
	}
	//情况1：缺失1
	public static void lostCheck1(Result result, Rate rate)
	{
		Item item = result.item;
		String rea = "\n病案缺失: ";
		
		//主诊断缺失
		if(item.getZdcode().length() <= 0)
		{
			rea = rea + "[主诊断缺失]";
			reason.add(rea);
			rate.zzdsum++; //主诊断缺失+1
		}
		
		//主手术缺失
		if(item.getSscode().length() <= 0 || item.getSscode() == null)  //病案无主手术
		{

			FinalStatement finalStatement = (FinalStatement)CSVRead.map2.get(item.getPid());

			if(finalStatement != null)
			{		
				if(finalStatement.getZsscode().length() > 0)   //对应结算单有主手术
				{
					rea = rea + "【主手术缺失】";
					reason.add(rea);  //添加到总原因里面
					rate.zsssum++; //主手术+1
				}
			}		
		}
		
		//次诊断缺失
		if(item.getCzdcode().size() <= 0)  //病案无主手术
		{
			rate.czdsum++;//次诊断缺失
		}
	}
	
	//情况1：缺失2(费用项参数个数不一致)
	public static void lostCheck2(Result result, Rate rate)
	{
		String rea = "\n[费用项参数个数不一致: ";
		String rea2 = "\n[费用项参数个数不一致: ";
		
		PlaceCost.plaCost();  //收费项信息
		
		String request_json = result.REQUEST_JSON;
		
		for(int i=0; i<PlaceCost.costStrs.length; i++)
		{
			if(request_json.contains(PlaceCost.costStrs[i]))
			{
				continue;
			}
			rate.cost[i]++;  //对应的费用项参数+1；
			rea = rea + "[" + PlaceCost.costStrs[i] +"]缺失, ";
		}
		
		if(!rea.equals(rea2))
		{
			rate.fyxqssum++;  //费用参数缺失+1
			reason.add(rea + "]");
		}
			
	}
	
	//情况1：缺失3(基本信息缺失)
	public static void lostCheck3(Result result, Rate rate)
	{
		String rea = "\n基本信息缺失: ";
		Item item = result.item;
		
		if(item.getSex().length() <= 0)
		{
			rea = rea + "[性别不能为空]";
			reason.add(rea);
			rate.sexsum++;  //性别缺失+1
		}
		if(item.getAge() <= 0)
		{
			rea = rea + "[年龄不能为空] ";
			reason.add(rea);
			rate.agesum++; //age缺失+1
		}
		if(item.getAge() > 0 && item.getAge() < 1.0 && item.getXsetz() <= 0)
		{
			rea = rea + "[年龄小于1必填新生儿体重 ]";
			reason.add(rea);
			rate.weightSum++;//体重缺失+1
		}
	}

	//情况2：取错
	public static void takeWrongCheck(Result result, Rate rate)
	{
		String rea = "";
		Item item = result.item;
		String pid = result.getPID();
		FinalStatement finalStatement = (FinalStatement) CSVRead.map2.get(pid);
		
		  if(finalStatement != null && finalStatement.getZdcode().split("\\+")[0].length() >= 0 && item.getZdcode().split("\\+")[0].length() > 0)
		    {
		      Diag diag = DiagExcelParser.map1.get(item.getZdcode().split("\\+")[0].toLowerCase());
		      if(!(item.getZdcode().toLowerCase().split("\\+")[0].equals(finalStatement.getZdcode().toLowerCase().split("\\+")[0]) ||
		          (diag!=null && diag.getDiag_name().equals(finalStatement.getZdname())) ||
		          (item.getZdcode().length()<=0 && finalStatement.getZdcode().length()<=0)))
		      {
		        rea = rea + "\n[主诊断取错]";
		        reason.add(rea);
		        rate.zzdqcsum++; //主诊断取错+1
		        rea = "";
		      }
		    }
		    
		    if(finalStatement != null && finalStatement.getZsscode().split("\\+")[0].length() >= 0 && item.getSscode().split("\\+")[0].length() >= 0)
		    {
		      Surg surg = SurgExcelParser.map1.get(item.getSscode().split("\\+")[0].toLowerCase());
		      if(!(item.getSscode().toLowerCase().split("\\+")[0].equalsIgnoreCase(finalStatement.getZsscode().toLowerCase().split("\\+")[0])||
		          ( surg!=null &&surg.getSurg_name().equals(finalStatement.getZssname()))))
		      {
		        rea = rea + "\n[主手术取错]";
		        reason.add(rea);
		        rate.zssqcsum++;
		        rea = "";
		      }
		    }
		
		//费用项中费用序号遗漏或多余
		String costStr = Cost22.T22(result);
		if(costStr.length() > 1)
		{
			rea = rea + costStr;
			rate.fyxqc++; //2.费用项取错
			reason.add(rea);
			rea = "";
		}
	}
	
	
	
	//情况3：编码
	public static void codingCheck(Result result, Rate rate)
	{
		boolean flagZzd = false;
		boolean flagCzd = false;
		boolean flagZss = false;
		boolean flagCss = false;

		String rea = "";
		
		Item item = result.getItem();
		rea = "\n疑似院内自行维护编码: 主诊断编码: ";
		String rea2 = "\n疑似院内自行维护编码: 主诊断编码: ";
		if ( !(item.getZdcode().equals("") || item.getZdcode().length()<=0 || item.getZdcode() == null))
		{
			if(DiagExcelParser.map1.get(item.getZdcode().split("\\+")[0].toLowerCase()) == null)
			{
					flagZzd= true;
					rea = rea + item.getZdcode();
					reason.add(rea);
					rea = "";

			}
		}
		if(!rea.equals(rea2))
		{
			reason.add(rea);
		}

		//所有次诊断
		List<String> czds = item.getCzdcode();
		List<String> czdYS = new ArrayList<>();  //存储疑似的次诊断
		rea = "\n疑似院内自行维护编码: 次诊断编码: ";
		rea2 = "\n疑似院内自行维护编码: 次诊断编码: ";
		if (czds != null && czds.size()>0)
		{
			for (String czd : czds)
			{
				if((DiagExcelParser.map1.get(czd.split("\\+")[0].toLowerCase()) == null))
				{
					rea = rea + czd + " ";
					flagCzd = true;
					break;
				}
			}
		}

		if(!rea.equals(rea2))
		{
			reason.add(rea);
		}

		//主手术编码
		rea = "\n疑似院内自行维护编码: 主手术编码: ";
		if (item.getSscode() != null && !item.getSscode().equals("") && item.getSscode().length()>0)
		{
			if(SurgExcelParser.map1.get(item.getSscode().split("\\+")[0].toLowerCase()) == null)
			{
					flagZss = true;
					rea = rea + item.getSscode();
					reason.add(rea);
					rea = "";
			}
		}

		//次手术编码
		List<String> csss = item.getCsscode();
		List<String> cssYS = new ArrayList<>();  //存储疑似的次诊断
		rea = "疑似院内自行维护编码: 次手术编码: ";
		String rea3 = "疑似院内自行维护编码: 次手术编码: ";

		if (csss != null && csss.size()>0)
		{
			for (String css : csss) {
				if (SurgExcelParser.map1.get(css.split("\\+")[0].toLowerCase()) == null) {
					rea = rea + css + " ";
					flagCss = true;
					break;
				}
			}
		}

		if(!rea.equals(rea3))
		{
			reason.add(rea);
		}
		
		if(flagZzd || flagCzd || flagZss || flagCss) {
			rate.ynzxwh++;
		}
	}
	
	//情况4：格式
	public static void formatCheck(Result result, Rate rate)
	{
		
	}
	
	//情况5：分组错误
	public static void groupWrongCheck(Result result, Rate rate)
	{
		String rea = "";
		
		Item item = result.item;
        String BILL_DRGCODE = result.getBILL_DRGCODE();
        String SYS_DRGCODE = result.getSYS_DRGCODE();
        int bill_sd = CheckSurgDiag.check(BILL_DRGCODE.toUpperCase().charAt(1));
        int sys_sd = -1;
        int sys_Mdc = -1;
        
        if(SYS_DRGCODE.length() > 0 && SYS_DRGCODE != null) //为空
        {
//        	System.out.println("**" + SYS_DRGCODE);
        	sys_sd = CheckSurgDiag.check(SYS_DRGCODE.toUpperCase().charAt(1));
        	sys_Mdc = SYS_DRGCODE.toUpperCase().charAt(0);
        }
        int bill_Mdc = BILL_DRGCODE.toUpperCase().charAt(0);
        
        if(bill_sd==0 || sys_sd==0)
        {
        	rea = rea + "DRG编码有误,";
        	reason.add(rea);
        	
        	rea = "";
        }
        
        rea = "\nADRG分组错误: ";
        String rea2 = "\nADRG分组错误: ";
        
        // ADRG分错
        if (bill_sd == 1 && sys_sd == 3){
            rea = rea + "内科分入外科";
            rate.ADRG1++; //1.1应该是外科操作结果入内科
        }
        if (bill_sd == 3 && sys_sd == 1){
        	rea = rea + "外科入内科";
        	rate.ADRG2++; //1.1应该是外科操作结果入内科
        }
        if(bill_sd == 3 && sys_sd == 3 && bill_Mdc!=sys_Mdc){
        	rea = rea + "都是内科，不同MDC";
        	rate.ADRG3++; //1.3都是内科，不同MDC
        }
        if (bill_sd == 1 && sys_sd == 1 && bill_Mdc!=sys_Mdc){
        	rea = rea + "都是外科操作，不同MDC";
        	rate.ADRG4++; //1.4都是外科操作，不同MDC
        }
        	
        if(!rea.equals(rea2))
        {
        	reason.add(rea);
        	
        }
        
        //合并症分错
        if(BILL_DRGCODE.length() > 0 && SYS_DRGCODE.length() > 0)
        {
        	String billCc = ReadMarkExcel.formatMapFS.get(BILL_DRGCODE);
        	String sysCc = ReadMarkExcel.formatMapFS.get(SYS_DRGCODE);
        	
        	if(billCc != null && sysCc != null)
        	{
	        	if(billCc.length() <= 0 || sysCc.length() <= 0)
	        	{
	        		rate.hbzfc7++;
	        		reason.add("\n分组错误: " + "合并症分错,2.7其他");
	        	}
	
	        	if(billCc.equals("2") && sysCc.equals("1"))
	        	{
	        		rate.hbzfc1++;
	        		reason.add("\n分组错误: " + "合并症分错,2.1应该是重要结果分到一般");
	        	}
	        	if(billCc.equals("2") && sysCc.equals("0"))
	        	{
	        		rate.hbzfc2++;
	        		reason.add("\n分组错误: " + "合并症分错,2.2应该是重要结果分到不伴");
	        	}
	        	if(billCc.equals("1") && sysCc.equals("2"))
	        	{
	        		rate.hbzfc3++;
	        		reason.add("\n分组错误: " + "合并症分错,2.3应该是一般结果是重要");
	        	}
	        	if(billCc.equals("0") && sysCc.equals("2"))
	        	{
	        		rate.hbzfc4++;
	        		reason.add("\n分组错误: " + "合并症分错,2.4应该是不伴结果是重要");
	        	}
	        	if(billCc.equals("1") && sysCc.equals("0"))
	        	{
	        		rate.hbzfc5++;
	        		reason.add("\n分组错误: " + "合并症分错,2.5应该是一般结果是不伴");
	        	}
	        	
	        	if(billCc.equals("0") && sysCc.equals("1"))
	        	{
	        		rate.hbzfc6++;
	        		reason.add("\n分组错误: " + "合并症分错,2.6应该是不伴结果是一般");
	        	}
        	}
        
        }
	}
	
	//情况6：未分组
	public static void ungroupedCheck(Result result, Rate rate)
	{
		if(!CheckSpecialGroup.Check(result))
		{
			rate.special++;//2.病组特殊条件没满足
			reason.add("\n病组特殊条件没满足");
		}
		reason.add("\n");
	}
	
}