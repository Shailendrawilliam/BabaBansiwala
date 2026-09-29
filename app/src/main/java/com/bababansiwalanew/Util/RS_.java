package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RS_ {
@SerializedName("OneMonth")
@Expose
private String oneMonth;
@SerializedName("ThreeMonth")
@Expose
private String threeMonth;
@SerializedName("SixMonth")
@Expose
private String sixMonth;
@SerializedName("NineMonth")
@Expose
private String nineMonth;
@SerializedName("OneYear")
@Expose
private String oneYear;
@SerializedName("FiveYear")
@Expose
private String fiveYear;

public String getOneMonth() {
        return oneMonth;
        }

public void setOneMonth(String oneMonth) {
        this.oneMonth = oneMonth;
        }

public String getThreeMonth() {
        return threeMonth;
        }

public void setThreeMonth(String threeMonth) {
        this.threeMonth = threeMonth;
        }

public String getSixMonth() {
        return sixMonth;
        }

public void setSixMonth(String sixMonth) {
        this.sixMonth = sixMonth;
        }

public String getNineMonth() {
        return nineMonth;
        }

public void setNineMonth(String nineMonth) {
        this.nineMonth = nineMonth;
        }

public String getOneYear() {
        return oneYear;
        }

public void setOneYear(String oneYear) {
        this.oneYear = oneYear;
        }

public String getFiveYear() {
        return fiveYear;
        }

public void setFiveYear(String fiveYear) {
        this.fiveYear = fiveYear;
        }
        }

