package com.firesoon.calibrator.pojo;

public class Rate
{
	//记录错误案例的个数
	public int zzdsum;//主诊断缺失
	public int zsssum;//1.2主手术缺失
	public int czdsum;//1.3次诊断缺失
	public int fyxqssum;//2.1费用项参数个数不一致
	public int sexsum;//1.1性别
	public int agesum;//1.2年龄
	public int weightSum; //3.新生儿体重
	public int zzdqcsum;//1.1主诊断取错
	public int zssqcsum;//1.2主手术取错
	public int fyxqc; //2费用项取错
	public int ynzxwh;//2院内自行维护编码
	public int ADRG1;//1.1应该是外科操作结果入内科
	public int ADRG2;//1.2应该是内科结果入外科操作
	public int ADRG3;//1.3都是内科，不同MDC
	public int ADRG4;//1.4都是外科操作，不同MDC
	public int hbzqc;//合并症分错
	public int hbzfc1;
	public int hbzfc2;
	public int hbzfc3;
	public int hbzfc4;
	public int hbzfc5;
	public int hbzfc6;
	public int hbzfc7;
	public int special;//2.病组特殊条件没满足
	public double[] cost; //费用项参数
	
	@Override
	public String toString()
	{
		return "Rate [zzdsum=" + zzdsum + ", zsssum=" + zsssum + ", czdsum=" + czdsum + ", fyxqssum=" + fyxqssum
				+ ", sexsum=" + sexsum + ", agesum=" + agesum + ", weightSum=" + weightSum + ", zzdqcsum=" + zzdqcsum
				+ ", zssqcsum=" + zssqcsum + ", fyxqc=" + fyxqc + ", ynzxwh=" + ynzxwh + ", ADRG1=" + ADRG1 + ", ADRG2="
				+ ADRG2 + ", ADRG3=" + ADRG3 + ", ADRG4=" + ADRG4 + ", hbzqc=" + hbzqc + ", special=" + special + "]";
	}
	
}
