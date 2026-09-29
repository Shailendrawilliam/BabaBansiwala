package com.bababansiwalanew.DthBook.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 08-04-2017.
 */

public class GetDthBookingResponse {

    private String RESPONSESTATUS;
    private String message;

    private ArrayList<OperatorObjectdth> DTHConnectionName;
    private ArrayList<Objectpack> Pack;
    private ArrayList<ObjectLanguage> Language;
    private ArrayList<ObjectPackage> Package;


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

    public ArrayList<OperatorObjectdth> getDTHConnectionName() {
        return DTHConnectionName;
    }

    public void setDTHConnectionName(ArrayList<OperatorObjectdth> DTHConnectionName) {
        this.DTHConnectionName = DTHConnectionName;
    }

    public ArrayList<Objectpack> getPack() {
        return Pack;
    }

    public void setPack(ArrayList<Objectpack> pack) {
        Pack = pack;
    }

    public ArrayList<ObjectLanguage> getLanguage() {
        return Language;
    }

    public void setLanguage(ArrayList<ObjectLanguage> language) {
        Language = language;
    }

    public ArrayList<ObjectPackage> getPackage() {
        return Package;
    }

    public void setPackage(ArrayList<ObjectPackage> aPackage) {
        Package = aPackage;
    }
}
