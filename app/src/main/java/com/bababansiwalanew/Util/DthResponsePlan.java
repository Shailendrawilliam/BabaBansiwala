package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DthResponsePlan {

    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("records")
    @Expose
    private List<Recordobject> records = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<Recordobject> getRecords() {
        return records;
    }

    public void setRecords(List<Recordobject> records) {
        this.records = records;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}