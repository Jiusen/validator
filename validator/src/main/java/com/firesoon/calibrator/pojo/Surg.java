package com.firesoon.calibrator.pojo;

public class Surg
{
	private String surg_code;
	private String surg_name;
	private String type;
	private String version;
	public String getSurg_code()
	{
		return surg_code;
	}
	public void setSurg_code(String surg_code)
	{
		this.surg_code = surg_code;
	}
	public String getSurg_name()
	{
		return surg_name;
	}
	public void setSurg_name(String surg_name)
	{
		this.surg_name = surg_name;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	@Override
	public String toString()
	{
		return "Surg [surg_code=" + surg_code + ", surg_name=" + surg_name + ", type=" + type + ", version=" + version
				+ "]";
	}
}
