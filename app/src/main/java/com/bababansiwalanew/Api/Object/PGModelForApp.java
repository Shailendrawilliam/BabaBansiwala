package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PGModelForApp {

    @SerializedName("Statuscode")
    @Expose
    private String statuscode;
    @SerializedName("Msg")
    @Expose
    private String msg;
    @SerializedName("PGID")
    @Expose
    private String pGID;
    @SerializedName("TID")
    @Expose
    private String tID;
    @SerializedName("TransactionID")
    @Expose
    private String transactionID;
    @SerializedName("Token")
    @Expose
    private String Token;
    @SerializedName("requestPTM")
    @Expose
    private RequestPTM requestPTM;
    @SerializedName("RPayRequest")
    @Expose
    private RPayRequest rPayRequest;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public RPayRequest getrPayRequest() {
        return rPayRequest;
    }

    public void setrPayRequest(RPayRequest rPayRequest) {
        this.rPayRequest = rPayRequest;
    }

    public RPayRequest getRPayRequest() {
        return rPayRequest;
    }

    public void setRPayRequest(RPayRequest rPayRequest) {
        this.rPayRequest = rPayRequest;
    }

    public String getpGID() {
        return pGID;
    }

    public void setpGID(String pGID) {
        this.pGID = pGID;
    }

    public String gettID() {
        return tID;
    }

    public void settID(String tID) {
        this.tID = tID;
    }


    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPGID() {
        return pGID;
    }

    public void setPGID(String pGID) {
        this.pGID = pGID;
    }

    public String getTID() {
        return tID;
    }

    public void setTID(String tID) {
        this.tID = tID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public RequestPTM getRequestPTM() {
        return requestPTM;
    }

    public void setRequestPTM(RequestPTM requestPTM) {
        this.requestPTM = requestPTM;
    }

}