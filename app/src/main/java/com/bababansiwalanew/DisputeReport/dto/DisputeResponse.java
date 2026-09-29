package com.bababansiwalanew.DisputeReport.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lalit on 10-04-2017.
 */

public class DisputeResponse {

    private String RESPONSESTATUS;
    private String message;
    @SerializedName(value = "Dispute",alternate = "List")
    @Expose
    private ArrayList<Dispute> Dispute;

    public ArrayList<Dispute> getDispute() {
        return Dispute;
    }

    public void setDispute(ArrayList<Dispute> dispute) {
        Dispute = dispute;
    }

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
}
