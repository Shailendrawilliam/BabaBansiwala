package com.bababansiwalanew.UserDayBook.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 15-04-2017.
 */

public class UserDayBookResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<UserDayBookSummary> Summary;
    private ArrayList<UserDayBookLedger> Ledger;

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

    public ArrayList<UserDayBookSummary> getSummary() {
        return Summary;
    }

    public void setSummary(ArrayList<UserDayBookSummary> summary) {
        Summary = summary;
    }

    public ArrayList<UserDayBookLedger> getLedger() {
        return Ledger;
    }

    public void setLedger(ArrayList<UserDayBookLedger> ledger) {
        Ledger = ledger;
    }
}
