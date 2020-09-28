package com.firesoon.calibrator.pojo;

import com.firesoon.calibrator.util.IdAndPid;

public class FinalStatement
{
	private String no;         //序号---
	private String id;		   //住院号---
	private String pId;        //对应结算表中的PId---
	private String fzcode;    //分组编码
	private String fzname;	  //分组名称
	private String fztzReason; //分组调整原因
	private String zsscode;    //主手术编码----
	private String zssname;   //主手术名称---
	private String zdcode;    //主诊断编码----
	private String zdname;    //主诊断名称---
	private String source;    //来源
	private String reason;    //来源
	
	public FinalStatement()
	{
		
	}
	
		public FinalStatement(String no, String zsscode,
			String zssname, String zdcode, String zdname, String fztzReason, String reason)
	{
		super();
		this.no = no;
		this.id = id;
		this.pId = IdAndPid.id2Pid(id, source);
		this.fztzReason = fztzReason;
		this.zsscode = zsscode;
		this.zssname = zssname;
		this.zdcode = zdcode;
		this.zdname = zdname;
		this.reason = reason;
	}
	public String getNo()
	{
		return no;
	}
	public void setNo(String no)
	{
		this.no = no;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getpId()
	{
		return pId;
	}
	public void setpId(String pId)
	{
		this.pId = pId;
	}
	public String getFzcode()
	{
		return fzcode;
	}
	public void setFzcode(String fzcode)
	{
		this.fzcode = fzcode;
	}
	public String getFzname()
	{
		return fzname;
	}
	public void setFzname(String fzname)
	{
		this.fzname = fzname;
	}
	public String getFztzReason()
	{
		return fztzReason;
	}
	public void setFztzReason(String fztzReason)
	{
		this.fztzReason = fztzReason;
	}
	public String getZsscode()
	{
		return zsscode;
	}
	public void setZsscode(String zsscode)
	{
		this.zsscode = zsscode;
	}
	public String getZssname()
	{
		return zssname;
	}
	public void setZssname(String zssname)
	{
		this.zssname = zssname;
	}
	public String getZdcode()
	{
		return zdcode;
	}
	public void setZdcode(String zdcode)
	{
		this.zdcode = zdcode;
	}
	public String getZdname()
	{
		return zdname;
	}
	public void setZdname(String zdname)
	{
		this.zdname = zdname;
	}
	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source = source;
	}
	
    @Override
    public String toString()
    {
        return "FinalStatement{" +
        		"no='" + no + '\'' +
                ", id='" + id + '\'' +
                ", pId='" + pId + '\'' +
                ", fzcode='" + fzcode + '\'' +
                ", fzname='" + fzname + '\'' +
                ", fztzReason='" + fztzReason + '\'' +
                ", zsscode='" + zsscode + '\'' +
                ", zssname='" + zssname + '\'' +
                ", zdcode='" + zdcode + '\'' +
                ", zdname='" + zdname + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
