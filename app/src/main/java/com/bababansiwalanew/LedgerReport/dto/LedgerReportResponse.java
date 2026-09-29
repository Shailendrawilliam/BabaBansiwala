package com.bababansiwalanew.LedgerReport.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lalit on 10-04-2017.
 */

public class LedgerReportResponse {

    private String RESPONSESTATUS;
    private String message;
    @SerializedName(value = "Ledger",alternate = "List")
    @Expose
   private ArrayList<LedgerObject> Ledger;

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

    public ArrayList<LedgerObject> getLedger() {
        return Ledger;
    }

    public void setLedger(ArrayList<LedgerObject> ledger) {
        Ledger = ledger;
    }
}
