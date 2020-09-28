package com.firesoon.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author leohuang
 */
public class Item implements Serializable {

    // zhu zhenduan 主诊断
    private String zdcode;
    // zhu shoushu  主手术
    private String sscode;
    // ci zhenduan 次诊断
    private ArrayList<String> czdcode;
    // ci shoushu  次手术
    private ArrayList<String> csscode;
    // 年龄
    private Double age;
    // 住院天数
    private Double daynum;
    // 性别
    private String sex;
    // 出院转归
    private int cyzg;
    // 输尿管鞘
    private int us;
    // 康复时间
    private int kfsj;
    // 呼吸机使用时间
    private Double vetime;
    //康复费用
    private Double kffy;
    //icu时间
    private int icu;
    // ERCP
    private int ercp;
    //碎石
    private int breakstone;
    //血液透析
    private int dialysis;
    // 新生儿体重
    private Double xsetz;
    // 离院方式
    private int lyfs;
    //乳房切除
    private int rfqc;
    // 乳房切除单侧
    private int singlerfqc;

    private int doublerfqc;

    private int csinglethyroi;

    private int cdoublethyroid;

    private int singlethyroi;

    private int doublethyroid;

    public int getSinglerfqc() {
        return singlerfqc;
    }

    public void setSinglerfqc(int singlerfqc) {
        this.singlerfqc = singlerfqc;
    }

    public int getDoublerfqc() {
        return doublerfqc;
    }

    public void setDoublerfqc(int doublerfqc) {
        this.doublerfqc = doublerfqc;
    }

    public int getCsinglethyroi() {
        return csinglethyroi;
    }

    public void setCsinglethyroi(int csinglethyroi) {
        this.csinglethyroi = csinglethyroi;
    }

    public int getCdoublethyroid() {
        return cdoublethyroid;
    }

    public void setCdoublethyroid(int cdoublethyroid) {
        this.cdoublethyroid = cdoublethyroid;
    }

    public int getSinglethyroi() {
        return singlethyroi;
    }

    public void setSinglethyroi(int singlethyroi) {
        this.singlethyroi = singlethyroi;
    }

    public int getDoublethyroid() {
        return doublethyroid;
    }

    public void setDoublethyroid(int doublethyroid) {
        this.doublethyroid = doublethyroid;
    }

    // 乳房旋切
    private int rfxq;
    private String cc;
    private Double ylzfy;

    //佛山参数 人工肝次数

    private int ALSSTime;
    //佛山参数 连续血液净化时间

    private int CBPTime;
    //佛山参数 体外膜肺氧合时间

    private int ECMOTime;
    //佛山参数 主动脉内球囊反搏时间

    private int IABPTime;
    //佛山 人工血管

    private int ABY;

    // 佛山 阿替普酶药物
    private int alteplase;


    private String czdStr;

    private String cssStr;

    private String usedMainDiag;

    private String usedMainOpera;

    private String system;

    public Item() {
        this.singlerfqc = 0;
        this.doublerfqc = 0;
        this.csinglethyroi = 0;
        this.cdoublethyroid = 0;
        this.singlethyroi = 0;
        this.doublethyroid = 0;
        this.alteplase = 0;
        this.ylzfy = 0.0;
        this.vetime = 0.0;
        this.daynum = 0.0;
        this.sex = "";
        this.age = 0.0;
        this.cyzg = 0;
        this.us = 0;
        this.icu = 0;
        this.ercp = 0;
        this.breakstone = 0;
        this.dialysis = 0;
        this.xsetz = 0.00;
        this.lyfs = 0;
        this.rfqc = 0;
        this.rfxq = 0;
        this.setKfsj(-1);
        this.setKffy(-1.0);
        this.ALSSTime = 0;
        this.CBPTime = 0;
        this.ECMOTime = 0;
        this.IABPTime = 0;
        this.ABY = 0;
    }



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


}

