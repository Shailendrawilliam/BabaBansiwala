package com.bababansiwalanew.DMRReport.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 18-04-2017.
 */

public class DMRReportResponse {

    private String RESPONSESTATUS;
    private String status;
    private String message;
    private ArrayList<DMRTransactions> DMRTransactions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public ArrayList<DMRTransactions> getDMRTransactions() {
        return DMRTransactions;
    }

    public void setDMRTransactions(ArrayList<DMRTransactions> DMRTransactions) {
        this.DMRTransactions = DMRTransactions;
    }
}
