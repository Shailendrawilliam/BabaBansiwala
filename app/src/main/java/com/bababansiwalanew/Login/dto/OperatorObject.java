package com.bababansiwalanew.Login.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lalit on 08-04-2017.
 */

public class OperatorObject {

    private String OPID;
    private String OPNAME;
    private String OPTYPE;
    private String Roffercode;

    @SerializedName(value = "icon",alternate = "Column1")
    @Expose
    private String Column1;
    private String Displayalue1;
    private String Displayalue2;
    private String Displayalue3;
    private String Displayalue4;
    @SerializedName(value = "charge",alternate = "Charge")
    @Expose
    double charge;

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public boolean isChargeAmtType() {
        return chargeAmtType;
    }

    public void setChargeAmtType(boolean chargeAmtType) {
        this.chargeAmtType = chargeAmtType;
    }

    boolean chargeAmtType;
    public String getDisplayalue1() {
        return Displayalue1;
    }

    public void setDisplayalue1(String displayalue1) {
        Displayalue1 = displayalue1;
    }

    public String getDisplayalue2() {
        return Displayalue2;
    }

    public void setDisplayalue2(String displayalue2) {
        Displayalue2 = displayalue2;
    }

    public String getDisplayalue3() {
        return Displayalue3;
    }

    public void setDisplayalue3(String displayalue3) {
        Displayalue3 = displayalue3;
    }

    public String getDisplayalue4() {
        return Displayalue4;
    }

    public void setDisplayalue4(String displayalue4) {
        Displayalue4 = displayalue4;
    }

    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public String getOPID() {
        return OPID;
    }

    public void setOPID(String OPID) {
        this.OPID = OPID;
    }

    public String getOPNAME() {
        return OPNAME;
    }

    public void setOPNAME(String OPNAME) {
        this.OPNAME = OPNAME;
    }

    public String getOPTYPE() {
        return OPTYPE;
    }

    public void setOPTYPE(String OPTYPE) {
        this.OPTYPE = OPTYPE;
    }

    public String getRoffercode() {
        return Roffercode;
    }

    public void setRoffercode(String roffercode) {
        Roffercode = roffercode;
    }
}
