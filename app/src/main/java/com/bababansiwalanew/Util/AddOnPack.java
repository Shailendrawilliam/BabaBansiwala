package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddOnPack {
    @SerializedName("Desc")
    @Expose
    private String desc;
    @SerializedName("LastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("PlanName")
    @Expose
    private String planName;
    @SerializedName("RS")
    @Expose
    private RS_ rS;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public RS_ getRS() {
        return rS;
    }

    public void setRS(RS_ rS) {
        this.rS = rS;
    }
}
