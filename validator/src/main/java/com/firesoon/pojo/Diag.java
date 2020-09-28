package com.firesoon.pojo;

/**
 * diag-version :《国标版GBT14396-2016》
 */
public class Diag {

    private String diagName;
    private String version;
    private String diagCode;
    private String mainDiagCode;
    private String otherDiagCode;

    public String getDiagName() {
        return diagName;
    }

    public void setDiagName(String diagName) {
        this.diagName = diagName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getMainDiagCode() {
        return mainDiagCode;
    }

    public void setMainDiagCode(String mainDiagCode) {
        this.mainDiagCode = mainDiagCode;
    }

    public String getOtherDiagCode() {
        return otherDiagCode;
    }

    public void setOtherDiagCode(String otherDiagCode) {
        this.otherDiagCode = otherDiagCode;
    }
}
