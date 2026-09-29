package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PG {

    @SerializedName("ID")
    @Expose
    private Long iD;
    @SerializedName("PG")
    @Expose
    private String pG;
    @SerializedName("PGType")
    @Expose
    private Long pGType;

    public Long getID() {
        return iD;
    }

    public void setID(Long iD) {
        this.iD = iD;
    }

    public String getPG() {
        return pG;
    }

    public void setPG(String pG) {
        this.pG = pG;
    }

    public Long getPGType() {
        return pGType;
    }

    public void setPGType(Long pGType) {
        this.pGType = pGType;
    }

}
