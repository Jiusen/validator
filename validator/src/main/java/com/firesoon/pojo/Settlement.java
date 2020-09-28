package com.firesoon.pojo;

/**
 * 结算单表格
 */
public class Settlement {
    private String id;
    // 住院id
    private String hosId;
    // 分组编码
    private String drgCode;
    // 分组名称
    private String drgName;
    // 分组调整原因
    private String drgReason;
    // 主手术编码
    private String ssCode;
    // 主手术名称
    private String ssName;
    // 主诊断编码
    private String zdCode;
    // 主诊断名称
    private String zdName;
    // source来源
    private String source;

    public Settlement() {
    }

    public Settlement(String id, String hosId,String ssCode, String ssName, String zdCode, String zdName,String drgReason) {
        this.id = id;
        this.hosId = hosId;

        this.drgReason = drgReason;
        this.ssCode = ssCode;
        this.ssName = ssName;
        this.zdCode = zdCode;
        this.zdName = zdName;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getDrgCode() {
        return drgCode;
    }

    public void setDrgCode(String drgCode) {
        this.drgCode = drgCode;
    }

    public String getDrgName() {
        return drgName;
    }

    public void setDrgName(String drgName) {
        this.drgName = drgName;
    }

    public String getDrgReason() {
        return drgReason;
    }

    public void setDrgReason(String drgReason) {
        this.drgReason = drgReason;
    }

    public String getSsCode() {
        return ssCode;
    }

    public void setSsCode(String ssCode) {
        this.ssCode = ssCode;
    }

    public String getSsName() {
        return ssName;
    }

    public void setSsName(String ssName) {
        this.ssName = ssName;
    }

    public String getZdCode() {
        return zdCode;
    }

    public void setZdCode(String zdCode) {
        this.zdCode = zdCode;
    }

    public String getZdName() {
        return zdName;
    }

    public void setZdName(String zdName) {
        this.zdName = zdName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
