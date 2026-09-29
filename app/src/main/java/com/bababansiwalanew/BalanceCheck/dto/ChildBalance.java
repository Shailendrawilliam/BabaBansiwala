package com.bababansiwalanew.BalanceCheck.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lalit on 17-04-2017.
 */

public class ChildBalance {

    private String ID;
    private String Name;
    private String PrepaidWallet;
    private String UtilityWallet;
    private String ImageURl;
    private String Content;
    private String Headings;
    private String SetTime;
    private String popSts;

    @SerializedName("Msg")
    @Expose
    private String msg;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }




    public String getImageURl() {
        return ImageURl;
    }

    public void setImageURl(String imageURl) {
        ImageURl = imageURl;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getHeadings() {
        return Headings;
    }

    public void setHeadings(String headings) {
        Headings = headings;
    }

    public String getPopSts() {
        return popSts;
    }

    public void setPopSts(String popSts) {
        this.popSts = popSts;
    }

    public String getSetTime() {
        return SetTime;
    }

    public void setSetTime(String setTime) {
        SetTime = setTime;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrepaidWallet() {
        return PrepaidWallet;
    }

    public void setPrepaidWallet(String prepaidWallet) {
        PrepaidWallet = prepaidWallet;
    }

    public String getUtilityWallet() {
        return UtilityWallet;
    }

    public void setUtilityWallet(String utilityWallet) {
        UtilityWallet = utilityWallet;
    }
}
