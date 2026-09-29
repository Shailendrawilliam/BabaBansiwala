package com.bababansiwalanew.CommissionSlab.dto;

/**
 * Created by Lalit on 26-04-2017.
 */

public class CommissionSlabObject {

    private String Column1;
    private String ID;
    private String OperatorID;
    private String OpratedName;
    private String comtype;
    private String changeType;
    private String RC;
    private String Commission;

    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOperatorID() {
        return OperatorID;
    }

    public void setOperatorID(String operatorID) {
        OperatorID = operatorID;
    }

    public String getOpratedName() {
        return OpratedName;
    }

    public void setOpratedName(String opratedName) {
        OpratedName = opratedName;
    }

    public String getComtype() {
        return comtype;
    }

    public void setComtype(String comtype) {
        this.comtype = comtype;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getRC() {
        return RC;
    }

    public void setRC(String RC) {
        this.RC = RC;
    }

    public String getCommission() {
        return Commission;
    }

    public void setCommission(String commission) {
        Commission = commission;
    }
}
