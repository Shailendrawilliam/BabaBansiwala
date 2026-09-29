package com.bababansiwalanew.Login.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lalit on 08-03-2017.
 */

public class LoginResponse {

    private String RESPONSESTATUS;
    private String message;


    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;

    public String getdESCRIPTION() {
        return dESCRIPTION;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    private ArrayList<LoginData> data;
  //  private ArrayList<Banners> Table;
  private ArrayList<LoginData> Table;
  private ArrayList<LoginData> List;

    public ArrayList<LoginData> getList() {
        return List;
    }

    public void setList(ArrayList<LoginData> list) {
        List = list;
    }

    public ArrayList<LoginData> getTable() {
        return Table;
    }

    public void setTable(ArrayList<LoginData> table) {
        Table = table;
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

    public ArrayList<LoginData> getData() {
        return data;
    }

    public void setData(ArrayList<LoginData> data) {
        this.data = data;
    }
}
