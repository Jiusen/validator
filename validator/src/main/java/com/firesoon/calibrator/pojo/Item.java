package com.firesoon.calibrator.pojo;

import java.util.ArrayList;

/**
 * @author Jiusen
 */
public class Item
{
    private String id;		//住院号
    
    private String pid;		//病人Id
    
    private String zdcode;	//主诊断
    
    private String sscode;  	//手术
    
    private String hoscode;   //医院码[js]
    
    private ArrayList<String> czdcode;	//次诊断
    
    private ArrayList<String> csscode;	//次手术
    
    private Double age;// 年龄
    
    private Double daynum;// 住院天数
    
    private String sex;// 性别
    
    private int cyzg;// 出院转归
    
    private int us;// 输尿管鞘
    
    private int kfsj;// 康复时间
    
    private Double vetime;// 呼吸机使用时间
    
    private Double kffy;//康复费用
    
    private int icu;//icu时间
    
    private int ercp;// ERCP
   
    private int breakstone; //碎石
    
    private int dialysis;//血液透析
    
    private Double xsetz;// 新生儿体重
    
    private int lyfs;// 离院方式
    
    private int rfqc;//乳房切除
    
    private int singlerfqc;// 乳房切除单侧

    private int doublerfqc;

    private int csinglethyroi;

    private int cdoublethyroid;

    private int singlethyroi;

    private int doublethyroid;

    private int rfxq;// 乳房旋切
    
    private String cc;
    
    private Double ylzfy;

    private int ALSSTime; //人工肝

    private int CBPTime;  //连续血液净化

    private int ECMOTime; //体外膜肺氧合

    private int IABPTime;  //主动脉内球囊反搏

    private int ABY;  //人工血管

    private int alteplase;  //阿替普酶药物

    private String czdStr;

    private String cssStr;

    private String usedMainDiag;

    private String usedMainOpera;

    private String system;

    public int getAlteplase() {
        return alteplase;
    }

    public void setAlteplase(int alteplase) {
        this.alteplase = alteplase;
    }

    public Double getYlzfy() {
        return ylzfy;
    }

    public void setYlzfy(Double ylzfy) {
        this.ylzfy = ylzfy;
    }

    public String getPid() {
        return pid;
    }


    public void setPid(String pid) {
        this.pid = pid;
    }


    public String getZdcode() {
        return zdcode;
    }


    public void setZdcode(String zdcode) {
        this.zdcode = zdcode;
    }


    public String getSscode() {
        return sscode;
    }


    public void setSscode(String sscode) {
        this.sscode = sscode;
    }


    public ArrayList<String> getCzdcode() {
        return czdcode;
    }


    public void setCzdcode(ArrayList<String> czdcode) {
        this.czdcode = czdcode;
    }


    public ArrayList<String> getCsscode() {
        return csscode;
    }


    public void setCsscode(ArrayList<String> csscode) {
        this.csscode = csscode;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getDaynum() {
        return daynum;
    }


    public void setDaynum(Double daynum) {
        this.daynum = daynum;
    }


    public String getSex() {
        return sex;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }


    public int getCyzg() {
        return cyzg;
    }


    public void setCyzg(int cyzg) {
        this.cyzg = cyzg;
    }


    public int getIcu() {
        return icu;
    }


    public void setIcu(int icu) {
        this.icu = icu;
    }


    public int getErcp() {
        return ercp;
    }


    public void setErcp(int ercp) {
        this.ercp = ercp;
    }


    public int getBreakstone() {
        return breakstone;
    }


    public void setBreakstone(int breakstone) {
        this.breakstone = breakstone;
    }


    public int getDialysis() {
        return dialysis;
    }


    public void setDialysis(int dialysis) {
        this.dialysis = dialysis;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }


    public int getUs() {
        return us;
    }


    public void setUs(int us) {
        this.us = us;
    }


    public Double getVetime() {
        return vetime;
    }


    public void setVetime(Double vetime) {
        this.vetime = vetime;
    }


    public String getSystem() {
        return system;
    }


    public void setSystem(String system) {
        this.system = system;
    }

    public Double getXsetz() {
        return xsetz;
    }


    public void setXsetz(Double xsetz) {
        this.xsetz = xsetz;
    }


    public int getLyfs() {
        return lyfs;
    }


    public void setLyfs(int lyfs) {
        this.lyfs = lyfs;
    }


    public int getRfqc() {
        return rfqc;
    }


    public void setRfqc(int rfqc) {
        this.rfqc = rfqc;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public int getALSSTime() {
        return ALSSTime;
    }


    public void setALSSTime(int aLSSTime) {
        ALSSTime = aLSSTime;
    }


    public int getCBPTime() {
        return CBPTime;
    }


    public void setCBPTime(int CBPTime) {
        this.CBPTime = CBPTime;
    }

    public int getECMOTime() {
        return ECMOTime;
    }


    public void setECMOTime(int eCMOTime) {
        ECMOTime = eCMOTime;
    }


    public int getIABPTime() {
        return IABPTime;
    }


    public void setIABPTime(int iABPTime) {
        IABPTime = iABPTime;
    }


    public int getABY() {
        return ABY;
    }


    public void setABY(int aBY) {
        ABY = aBY;
    }


    public int getRfxq() {
        return rfxq;
    }


    public void setRfxq(int rfxq) {
        this.rfxq = rfxq;
    }


    public int getKfsj() {
        return kfsj;
    }


    public void setKfsj(int kfsj) {
        this.kfsj = kfsj;
    }


    public Double getKffy() {
        return kffy;
    }


    public void setKffy(Double kffy) {
        this.kffy = kffy;
    }

    public String getCzdStr() {
        return czdStr;
    }

    public String getCssStr() {
        return cssStr;
    }

    public void setCssStr(String cssStr) {
        this.cssStr = cssStr;
    }

    public void setCzdStr(String czdStr) {
        this.czdStr = czdStr;
    }

    public String getUsedMainDiag() {
        return usedMainDiag;
    }

    public String getUsedMainOpera() {
        return usedMainOpera;
    }

    public void setUsedMainDiag(String usedMainDiag) {
        this.usedMainDiag = usedMainDiag;
    }

    public void setUsedMainOpera(String usedMainOpera) {
        this.usedMainOpera = usedMainOpera;
    }
    @Override
    public String toString()
    {
        return "Item{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", zdcode='" + zdcode + '\'' +
                ", sscode='" + sscode + '\'' +
                ", hoscode='" + hoscode + '\'' +
                ", czdcode=" + czdcode +
                ", csscode=" + csscode +
                ", age=" + age +
                ", daynum=" + daynum +
                ", sex='" + sex + '\'' +
                ", cyzg=" + cyzg +
                ", us=" + us +
                ", kfsj=" + kfsj +
                ", vetime=" + vetime +
                ", kffy=" + kffy +
                ", icu=" + icu +
                ", ercp=" + ercp +
                ", breakstone=" + breakstone +
                ", dialysis=" + dialysis +
                ", xsetz=" + xsetz +
                ", lyfs=" + lyfs +
                ", rfqc=" + rfqc +
                ", singlerfqc=" + singlerfqc +
                ", doublerfqc=" + doublerfqc +
                ", csinglethyroi=" + csinglethyroi +
                ", cdoublethyroid=" + cdoublethyroid +
                ", singlethyroi=" + singlethyroi +
                ", doublethyroid=" + doublethyroid +
                ", rfxq=" + rfxq +
                ", cc='" + cc + '\'' +
                ", ylzfy=" + ylzfy +
                ", ALSSTime=" + ALSSTime +
                ", CBPTime=" + CBPTime +
                ", ECMOTime=" + ECMOTime +
                ", IABPTime=" + IABPTime +
                ", ABY=" + ABY +
                ", alteplase=" + alteplase +
                ", czdStr='" + czdStr + '\'' +
                ", cssStr='" + cssStr + '\'' +
                ", usedMainDiag='" + usedMainDiag + '\'' +
                ", usedMainOpera='" + usedMainOpera + '\'' +
                ", system='" + system + '\'' +
                '}';
    } 
}

