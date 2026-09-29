package com.bababansiwalanew.RechargeReport.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lalit on 10-04-2017.
 */

public class RechargeReportResponse {

    private String RESPONSESTATUS;
    private String message;

    @SerializedName(value = "RechargeStatus",alternate = "List")
    @Expose
    private ArrayList<RechargeStatus>  RechargeStatus;

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

    public ArrayList<com.bababansiwalanew.RechargeReport.dto.RechargeStatus> getRechargeStatus() {
        return RechargeStatus;
    }

    public void setRechargeStatus(ArrayList<com.bababansiwalanew.RechargeReport.dto.RechargeStatus> rechargeStatus) {
        RechargeStatus = rechargeStatus;
    }
}
