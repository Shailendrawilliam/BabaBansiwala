package com.bababansiwalanew.Popup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopupObject {

    @SerializedName("RESPONSESTATUS")
    @Expose
    private String rESPONSESTATUS;
    @SerializedName("DATA")
    @Expose
    private List<DATum> dATA = null;

    public String getRESPONSESTATUS() {
        return rESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String rESPONSESTATUS) {
        this.rESPONSESTATUS = rESPONSESTATUS;
    }

    public List<DATum> getDATA() {
        return dATA;
    }

    public void setDATA(List<DATum> dATA) {
        this.dATA = dATA;
    }
}
