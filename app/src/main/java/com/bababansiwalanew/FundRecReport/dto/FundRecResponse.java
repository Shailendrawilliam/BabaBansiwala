package com.bababansiwalanew.FundRecReport.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lalit on 15-04-2017.
 */

public class FundRecResponse {

    private String RESPONSESTATUS;
    private String message;
    @SerializedName(value = "FundReceive",alternate = "ListTransfer")
    @Expose
    private ArrayList<FundRecObject> FundReceive ;
    private ArrayList<FundRecObject> FundTransfer;

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<FundRecObject> getFundReceive() {
        return FundReceive;
    }

    public void setFundReceive(ArrayList<FundRecObject> fundReceive) {
        FundReceive = fundReceive;
    }

    public ArrayList<FundRecObject> getFundTransfer() {
        return FundTransfer;
    }

    public void setFundTransfer(ArrayList<FundRecObject> fundTransfer) {
        FundTransfer = fundTransfer;
    }
}
