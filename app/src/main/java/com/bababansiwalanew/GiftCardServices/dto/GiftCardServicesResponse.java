package com.bababansiwalanew.GiftCardServices.dto;

import java.util.ArrayList;

public  class GiftCardServicesResponse {

     private String statuscode;
     private String RESPONSESTATUS;
     private String message;

    private String status;
    private ArrayList<GiftCardServicesResponsedata> data;
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


    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<GiftCardServicesResponsedata> getData() {
        return data;
    }

    public void setData(ArrayList<GiftCardServicesResponsedata> data) {
        this.data = data;
    }
}
