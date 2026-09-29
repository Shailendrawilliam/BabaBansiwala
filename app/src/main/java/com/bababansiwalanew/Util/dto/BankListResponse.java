package com.bababansiwalanew.Util.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lalit on 12-04-2017.
 */

public class BankListResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<BankListObject> Banks;

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public ArrayList<BankListObject> getBanks() {
        return Banks;
    }

    public void setBanks(ArrayList<BankListObject> banks) {
        Banks = banks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @SerializedName("status")
    @Expose
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
