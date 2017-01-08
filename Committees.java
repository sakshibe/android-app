package com.example.pavan.tryapp;

import java.io.Serializable;


public class Committees implements Serializable{
    private String comId,comNm,chamber,parCom,cnct,ofc;

    public Committees(String comId, String comNm, String chamber, String parCom, String cnct,String ofc) {
        this.comId = comId;
        this.comNm = comNm;
        this.chamber = chamber;
        this.parCom = parCom;
        this.cnct = cnct;
        this.ofc = ofc;
    }

    public String getParCom() {
        return parCom;
    }

    public void setParCom(String parCom) {
        this.parCom = parCom;
    }

    public String getCnct() {
        return cnct;
    }

    public void setCnct(String cnct) {
        this.cnct = cnct;
    }

    public String getOfc() {
        return ofc;
    }

    public void setOfc(String ofc) {
        this.ofc = ofc;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComNm() {
        return comNm;
    }

    public void setComNm(String comNm) {
        this.comNm = comNm;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }
}
