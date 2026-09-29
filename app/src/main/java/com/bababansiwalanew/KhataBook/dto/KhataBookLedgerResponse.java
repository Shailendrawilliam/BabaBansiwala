package com.bababansiwalanew.KhataBook.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class KhataBookLedgerResponse implements Serializable {

    @SerializedName(value = "RESPONSESTATUS", alternate = {"Responsestatus", "ResponseStatus", "statuscode", "Statuscode", "status", "Status"})
    @Expose
    private String responseStatus;

    @SerializedName(value = "message", alternate = {"Message", "msg", "Msg"})
    @Expose
    private String message;

    @SerializedName(value = "Data", alternate = {"Ledger", "Table", "List", "data", "transactions", "KhataBookLedger"})
    @Expose
    private ArrayList<KhataLedgerItem> ledgerList;

    public String getResponseStatus() {
        return responseStatus != null ? responseStatus : "";
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<KhataLedgerItem> getLedgerList() {
        if (ledgerList == null) {
            ledgerList = new ArrayList<>();
        }
        return ledgerList;
    }

    public void setLedgerList(ArrayList<KhataLedgerItem> ledgerList) {
        this.ledgerList = ledgerList;
    }

    public boolean isSuccess() {
        return "1".equalsIgnoreCase(responseStatus) || "success".equalsIgnoreCase(responseStatus) || "true".equalsIgnoreCase(responseStatus);
    }
}
