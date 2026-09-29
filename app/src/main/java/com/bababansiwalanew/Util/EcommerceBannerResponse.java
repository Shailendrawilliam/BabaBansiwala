package com.bababansiwalanew.Util;

import com.bababansiwalanew.Activities.EcommerceBannerModel;

import java.util.ArrayList;

public class EcommerceBannerResponse {

    private String RESPONSESTATUS;

    private String message;

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public ArrayList<EcommerceBannerModel> getListEcommerceBanner() {
        return listEcommerceBanner;
    }

    public void setListEcommerceBanner(ArrayList<EcommerceBannerModel> listEcommerceBanner) {
        this.listEcommerceBanner = listEcommerceBanner;
    }

    private ArrayList<EcommerceBannerModel> listEcommerceBanner = null;

    public String getResponsestatus() {
        return RESPONSESTATUS;
    }

    public void setResponsestatus(String responsestatus) {
        this.RESPONSESTATUS = responsestatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
