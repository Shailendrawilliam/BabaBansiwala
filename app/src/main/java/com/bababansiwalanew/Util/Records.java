package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Records {
    @SerializedName("FullTT")
    @Expose
    private ArrayList<ThreeGFourG> fullTT;
    @SerializedName("ThreeGFourG")
    @Expose
    private ArrayList<ThreeGFourG> threeGFourG = null;
    @SerializedName("RateCutter")
    @Expose
    private ArrayList<ThreeGFourG> rateCutter = null;
    @SerializedName("TwoG")
    @Expose
    private ArrayList<ThreeGFourG> twoG = null;
    @SerializedName("SMS")
    @Expose
    private ArrayList<ThreeGFourG> sMS = null;
    @SerializedName("Roaming")
    @Expose
    private ArrayList<ThreeGFourG> roaming;
    @SerializedName("COMBO")
    @Expose
    private ArrayList<ThreeGFourG> cOMBO = null;
    @SerializedName("TOPUP")
    @Expose
    private ArrayList<ThreeGFourG> tOPUP = null;
    @SerializedName("RS")
    @Expose
    private Integer rS;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Plan")
    @Expose
    private ArrayList<AddOnPack> plan = null;

    public ArrayList<AddOnPack> getPlan() {
        return plan;
    }

    public void setPlan(ArrayList<AddOnPack> plan) {
        this.plan = plan;
    }

    public ArrayList<AddOnPack> getAddOnPack() {
        return addOnPack;
    }

    public void setAddOnPack(ArrayList<AddOnPack> addOnPack) {
        this.addOnPack = addOnPack;
    }

    @SerializedName("AddOnPack")
    @Expose
    private ArrayList<AddOnPack> addOnPack = null;

    public ArrayList<ThreeGFourG> getFullTT() {
        return fullTT;
    }

    public void setFullTT(ArrayList<ThreeGFourG> fullTT) {
        this.fullTT = fullTT;
    }

    public ArrayList<ThreeGFourG> getThreeGFourG() {
        return threeGFourG;
    }

    public void setThreeGFourG(ArrayList<ThreeGFourG> threeGFourG) {
        this.threeGFourG = threeGFourG;
    }

    public ArrayList<ThreeGFourG> getRateCutter() {
        return rateCutter;
    }

    public void setRateCutter(ArrayList<ThreeGFourG> rateCutter) {
        this.rateCutter = rateCutter;
    }

    public ArrayList<ThreeGFourG> getTwoG() {
        return twoG;
    }

    public void setTwoG(ArrayList<ThreeGFourG> twoG) {
        this.twoG = twoG;
    }

    public ArrayList<ThreeGFourG> getSMS() {
        return sMS;
    }

    public void setSMS(ArrayList<ThreeGFourG> sMS) {
        this.sMS = sMS;
    }

    public ArrayList<ThreeGFourG> getRoaming() {
        return roaming;
    }

    public void setRoaming(ArrayList<ThreeGFourG> roaming) {
        this.roaming = roaming;
    }

    public ArrayList<ThreeGFourG> getCOMBO() {
        return cOMBO;
    }

    public void setCOMBO(ArrayList<ThreeGFourG> cOMBO) {
        this.cOMBO = cOMBO;
    }

    public ArrayList<ThreeGFourG> getTOPUP() {
        return tOPUP;
    }

    public void setTOPUP(ArrayList<ThreeGFourG> tOPUP) {
        this.tOPUP = tOPUP;
    }

    public Integer getRS() {
        return rS;
    }

    public void setRS(Integer rS) {
        this.rS = rS;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
