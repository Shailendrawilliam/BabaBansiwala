package com.bababansiwalanew.DTHReversal.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class DTHTicketResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<DataByTransactionId> Detail;

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

    public ArrayList<DataByTransactionId> getDetail() {
        return Detail;
    }

    public void setDetail(ArrayList<DataByTransactionId> detail) {
        Detail = detail;
    }
}
