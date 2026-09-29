package com.bababansiwalanew.ROffer.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 16-04-2017.
 */

public class ROfferResponse {

    private String tel;
    private String operator;
    private String status;
    private ArrayList<ROfferObject> records;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ROfferObject> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<ROfferObject> records) {
        this.records = records;
    }

}
