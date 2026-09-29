package com.bababansiwalanew.DMR.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 12-04-2017.
 */

public class TABLE {
    private String RESPONSESTATUS;
    private String MESSAGE;
    //////////////////////////////////
    private String KYC;
    private String NAME;
    private String CURRENCY;
    private String REMAINING;
    private String USED;
    //////////////////////////////////
    private ArrayList<LIMIT> LIMIT;
    private ArrayList<BENEFICIARY> BENEFICIARY;
    //////////////////////////////////
    private String ApiBAL;
    //////////////////////////////////
    private String RECIPIENTID;
    private String RECIPIENTNAME;

    /////////////////////////////////
    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public ArrayList<BENEFICIARY> getBENEFICIARY() {
        return BENEFICIARY;
    }

    public void setBENEFICIARY(ArrayList<BENEFICIARY> BENEFICIARY) {
        this.BENEFICIARY = BENEFICIARY;
    }

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }


    public String getKYC() {
        return KYC;
    }

    public void setKYC(String KYC) {
        this.KYC = KYC;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCURRENCY() {
        return CURRENCY;
    }

    public void setCURRENCY(String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    public String getREMAINING() {
        return REMAINING;
    }

    public void setREMAINING(String REMAINING) {
        this.REMAINING = REMAINING;
    }

    public String getUSED() {
        return USED;
    }

    public void setUSED(String USED) {
        this.USED = USED;
    }

    public ArrayList<LIMIT> getLIMIT() {
        return LIMIT;
    }

    public void setLIMIT(ArrayList<LIMIT> LIMIT) {
        this.LIMIT = LIMIT;
    }

    public String getApiBAL() {
        return ApiBAL;
    }

    public void setApiBAL(String apiBAL) {
        ApiBAL = apiBAL;
    }

    public String getRECIPIENTID() {
        return RECIPIENTID;
    }

    public void setRECIPIENTID(String RECIPIENTID) {
        this.RECIPIENTID = RECIPIENTID;
    }

    public String getRECIPIENTNAME() {
        return RECIPIENTNAME;
    }

    public void setRECIPIENTNAME(String RECIPIENTNAME) {
        this.RECIPIENTNAME = RECIPIENTNAME;
    }
}
