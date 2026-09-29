package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThreeGFourG {
    @SerializedName("Validity")
    @Expose
    private String validity;
    @SerializedName("LastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("RS")
    @Expose
    private String rS;
    @SerializedName("Desc")
    @Expose
    private String desc;

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getRS() {
        return rS;
    }

    public void setRS(String rS) {
        this.rS = rS;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
