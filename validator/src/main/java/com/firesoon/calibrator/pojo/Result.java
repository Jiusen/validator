package com.firesoon.calibrator.pojo;

import java.util.ArrayList;
import java.util.List;

public class Result
{
	public String MONTH;
	public String PID;
	public String BILL_DRGCODE;
	public String SYS_DRGCODE;
	public String REQUEST_JSON;
	public Item item;
	public String source;
	public List<String> reason;  //原因
	
	public String getMONTH()
	{
		return MONTH;
	}
	public void setMONTH(String mONTH)
	{
		MONTH = mONTH;
	}
	public String getPID()
	{
		return PID;
	}
	public void setPID(String pID)
	{
		PID = pID;
	}
	public String getBILL_DRGCODE()
	{
		return BILL_DRGCODE;
	}
	public void setBILL_DRGCODE(String bILL_DRGCODE)
	{
		BILL_DRGCODE = bILL_DRGCODE;
	}
	public String getSYS_DRGCODE()
	{
		return SYS_DRGCODE;
	}
	public void setSYS_DRGCODE(String sYS_DRGCODE)
	{
		SYS_DRGCODE = sYS_DRGCODE;
	}
	public String getREQUEST_JSON()
	{
		return REQUEST_JSON;
	}
	public void setREQUEST_JSON(String rEQUEST_JSON)
	{
		REQUEST_JSON = rEQUEST_JSON;
	}
	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source = source;
	}
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<String> getReason() {
		return reason;
	}
	public void setReason(List<String> reason) {
		this.reason = reason;
	}
	@Override
	public String toString()
	{
		return "Result [MONTH=" + MONTH + ", PID=" + PID + ", BILL_DRGCODE=" + BILL_DRGCODE + ", SYS_DRGCODE="
				+ SYS_DRGCODE + ", REQUEST_JSON=" + REQUEST_JSON + ", item=" + item + "source=" + source +
				 ", reason=" + reason+ "]";
	}
	
	
}
