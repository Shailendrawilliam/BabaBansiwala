package com.bababansiwalanew.KhataBook.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KhataBookCommonResponse implements Serializable {

    @SerializedName(value = "RESPONSESTATUS", alternate = {"Responsestatus", "ResponseStatus", "statuscode", "Statuscode", "status", "Status"})
    @Expose
    private String responseStatus;

    @SerializedName(value = "message", alternate = {"Message", "msg", "Msg", "DESCRIPTION", "Description"})
    @Expose
    private String message;

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

    public boolean isSuccess() {
        return "1".equalsIgnoreCase(responseStatus) || "success".equalsIgnoreCase(responseStatus) || "true".equalsIgnoreCase(responseStatus);
    }
}
