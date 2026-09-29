package com.bababansiwalanew.CommissionSlab.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class CommissionSlabResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<CommissionSlabObject> Commission;

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

    public ArrayList<CommissionSlabObject> getCommission() {
        return Commission;
    }

    public void setCommission(ArrayList<CommissionSlabObject> commission) {
        Commission = commission;
    }
}
