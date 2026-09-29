package com.bababansiwalanew.Util.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 19-04-2017.
 */

public class ServicesResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<ServicesObject> APPSERVICES;


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

    public ArrayList<ServicesObject> getAPPSERVICES() {
        return APPSERVICES;
    }

    public void setAPPSERVICES(ArrayList<ServicesObject> APPSERVICES) {
        this.APPSERVICES = APPSERVICES;
    }
}
