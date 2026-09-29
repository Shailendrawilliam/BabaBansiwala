package com.bababansiwalanew.DMR.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeneficiaryResponse
{

    @SerializedName("response_status_id")
    @Expose
    private Integer responseStatusId;
    @SerializedName("data")
    @Expose
    private BeneData data;
    @SerializedName("response_type_id")
    @Expose
    private Integer responseTypeId;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("SenderMemberID")
    @Expose
    private Object senderMemberID;

    public Integer getResponseStatusId() {
        return responseStatusId;
    }

    public void setResponseStatusId(Integer responseStatusId) {
        this.responseStatusId = responseStatusId;
    }

    public BeneData getData() {
        return data;
    }

    public void setData(BeneData data) {
        this.data = data;
    }

    public Integer getResponseTypeId() {
        return responseTypeId;
    }

    public void setResponseTypeId(Integer responseTypeId) {
        this.responseTypeId = responseTypeId;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getSenderMemberID() {
        return senderMemberID;
    }

    public void setSenderMemberID(Object senderMemberID) {
        this.senderMemberID = senderMemberID;
    }

}
