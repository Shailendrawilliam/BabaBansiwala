package com.bababansiwalanew.KhataBook.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class KhataBookCustomerResponse implements Serializable {

    @SerializedName(value = "RESPONSESTATUS", alternate = {"Responsestatus", "ResponseStatus", "statuscode", "Statuscode", "status", "Status"})
    @Expose
    private String responseStatus;

    @SerializedName(value = "message", alternate = {"Message", "msg", "Msg"})
    @Expose
    private String message;

    @SerializedName(value = "Data", alternate = {"CustomerList", "customers", "Table", "List", "data", "Customer"})
    @Expose
    private ArrayList<KhataCustomer> customerList;

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

    public ArrayList<KhataCustomer> getCustomerList() {
        if (customerList == null) {
            customerList = new ArrayList<>();
        }
        return customerList;
    }

    public void setCustomerList(ArrayList<KhataCustomer> customerList) {
        this.customerList = customerList;
    }

    public boolean isSuccess() {
        return "1".equalsIgnoreCase(responseStatus) || "success".equalsIgnoreCase(responseStatus) || "true".equalsIgnoreCase(responseStatus);
    }
}
