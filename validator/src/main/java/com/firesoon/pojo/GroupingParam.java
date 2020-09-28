package com.firesoon.pojo;

import com.alibaba.fastjson.JSON;
import netscape.javascript.JSObject;

/**
 * 分组参数表格
 */
public class GroupingParam {
    private String month;
    private String pid;
    private String billDra;
    private String sysDrg;
    private String itemStr;
    private Item item;
    private String source;
    private StringBuilder errorReason = new StringBuilder();

    public GroupingParam()
    {
    }

    public GroupingParam(String month, String pid, String billDra, String sysDrg, String itemStr, String source)
    {
        this.month = month;
        this.pid = pid;
        this.billDra = billDra;
        this.sysDrg = sysDrg;
        this.itemStr = itemStr;
        this.item = JSON.parseObject(itemStr,Item.class);
        this.source = source;
    }



    public StringBuilder getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(StringBuilder errorReason) {
        this.errorReason = errorReason;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBillDra() {
        return billDra;
    }

    public void setBillDra(String billDra) {
        this.billDra = billDra;
    }

    public String getSysDrg() {
        return sysDrg;
    }

    public void setSysDrg(String sysDrg) {
        this.sysDrg = sysDrg;
    }

    public String getItemStr() {
        return itemStr;
    }

    public void setItemStr(String itemStr) {
        this.itemStr = itemStr;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
