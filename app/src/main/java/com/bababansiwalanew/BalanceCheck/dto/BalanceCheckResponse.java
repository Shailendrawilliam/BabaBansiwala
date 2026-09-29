package com.bababansiwalanew.BalanceCheck.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Lalit on 16-04-2017.
 */

public class BalanceCheckResponse {

    private String RESPONSESTATUS;
    private String message;
    private String PrepaidWallet;
    private String UtilityWallet;
    private String IsLogin;
    private ArrayList<ChildBalance> ChildBalance;
    private ArrayList<ChildBalance> DATA;
    private ArrayList<ChildBalance> Notification ;

    public ArrayList<com.bababansiwalanew.BalanceCheck.dto.ChildBalance> getNotification() {
        return Notification;
    }

    public void setNotification(ArrayList<com.bababansiwalanew.BalanceCheck.dto.ChildBalance> notification) {
        Notification = notification;
    }

    public ArrayList<com.bababansiwalanew.BalanceCheck.dto.ChildBalance> getDATA() {
        return DATA;
    }

    public void setDATA(ArrayList<com.bababansiwalanew.BalanceCheck.dto.ChildBalance> DATA) {
        this.DATA = DATA;
    }

    public ArrayList<com.bababansiwalanew.BalanceCheck.dto.ChildBalance> getChildBalance() {
        return ChildBalance;
    }

    public void setChildBalance(ArrayList<com.bababansiwalanew.BalanceCheck.dto.ChildBalance> childBalance) {
        ChildBalance = childBalance;
    }

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

    public String getIsLogin() {
        return IsLogin;
    }

    public void setIsLogin(String isLogin) {
        IsLogin = isLogin;
    }



    public static BalanceCheckResponse convertServerStringToJson(String serverResult){
        Gson gson= new Gson();
        Type type;
        type = new TypeToken<BalanceCheckResponse>(){}.getType();
        return gson.fromJson(serverResult,BalanceCheckResponse.class);
    }
}
