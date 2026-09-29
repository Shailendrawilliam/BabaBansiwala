package com.bababansiwalanew.Util;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataM {
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Time")
    @Expose
    private Object time;
    @SerializedName("Records")
    @Expose
    private Records records;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }
}
