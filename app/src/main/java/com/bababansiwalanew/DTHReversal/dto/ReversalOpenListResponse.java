package com.bababansiwalanew.DTHReversal.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 25-04-2017.
 */

public class ReversalOpenListResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<OpenDetailObject> Detail;
    private ArrayList<DataByTransactionId> Transaction;

    public ArrayList<DataByTransactionId> getTransaction() {
        return Transaction;
    }

    public void setTransaction(ArrayList<DataByTransactionId> transaction) {
        Transaction = transaction;
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

    public ArrayList<OpenDetailObject> getDetail() {
        return Detail;
    }

    public void setDetail(ArrayList<OpenDetailObject> detail) {
        Detail = detail;
    }
}
