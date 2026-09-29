package com.bababansiwalanew.Util.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 15-02-2017.
 */

public class NumberListResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<NumberList> NumberList;

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

    public ArrayList<NumberList> getNumberList() {
        return NumberList;
    }

    public void setNumberList(ArrayList<NumberList> numberList) {
        NumberList = numberList;
    }
}
