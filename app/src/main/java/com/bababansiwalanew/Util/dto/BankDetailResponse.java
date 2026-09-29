package com.bababansiwalanew.Util.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 18-04-2017.
 */

public class BankDetailResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<BankDetail> Banks;

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

    public ArrayList<BankDetail> getBanks() {
        return Banks;
    }

    public void setBanks(ArrayList<BankDetail> banks) {
        Banks = banks;
    }
}
