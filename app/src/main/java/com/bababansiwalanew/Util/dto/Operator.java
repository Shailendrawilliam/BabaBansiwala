package com.bababansiwalanew.Util.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Operator {

    private int OPID;
    private String OPNAME;
    private int OPTYPE;
    @SerializedName(value = "OPIMG")
    @Expose
    private String OPIMG;
    private String PLANCODE;
    private String Roffercode;

    private String Column1;

    private String Displayalue1;
    private String Displayalue2;
    private String Displayalue3;
    private String Displayalue4;
    @SerializedName(value = "charge",alternate = "Charge")
    @Expose
    double charge;
    boolean chargeAmtType;

    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    @SerializedName(value = "icon")
    @Expose
    private String icon;


    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public boolean isChargeAmtType() {
        return chargeAmtType;
    }

    public void setChargeAmtType(boolean chargeAmtType) {
        this.chargeAmtType = chargeAmtType;
    }


    public Operator(int OPID, String OPNAME, String Column1, int OPTYPE, String OPIMG, String PLANCODE, String Displayalue1, String Displayalue2,
                    String Displayalue3, String Displayalue4, String icon) {

        this.OPID = OPID;
        this.OPNAME = OPNAME;
        this.OPTYPE = OPTYPE;
        this.OPIMG = OPIMG;
        this.PLANCODE = PLANCODE;
        this.icon = icon;
        this.Column1 = Column1;

        this.Displayalue1 = Displayalue1;
        this.Displayalue2 = Displayalue2;
        this.Displayalue3 = Displayalue3;
        this.Displayalue4 = Displayalue4;
    }


    public String getRoffercode() {
        return Roffercode;
    }

    public void setRoffercode(String roffercode) {
        Roffercode = roffercode;
    }

    public String getPLANCODE() {
        return PLANCODE;
    }

    public void setPLANCODE(String PLANCODE) {
        this.PLANCODE = PLANCODE;
    }

    public String getDisplayalue1() {
        return Displayalue1;
    }

    public void setDisplayalue1(String displayalue1) {
        Displayalue1 = displayalue1;
    }

    public String getDisplayalue2() {
        return Displayalue2;
    }

    public void setDisplayalue2(String displayalue2) {
        Displayalue2 = displayalue2;
    }

    public String getDisplayalue3() {
        return Displayalue3;
    }

    public void setDisplayalue3(String displayalue3) {
        Displayalue3 = displayalue3;
    }

    public String getDisplayalue4() {
        return Displayalue4;
    }

    public void setDisplayalue4(String displayalue4) {
        Displayalue4 = displayalue4;
    }

    public int getOPID() {
        return OPID;
    }

    public void setOPID(int OPID) {
        this.OPID = OPID;
    }

    public String getOPNAME() {
        return OPNAME;
    }

    public void setOPNAME(String OPNAME) {
        this.OPNAME = OPNAME;
    }

    public int getOPTYPE() {
        return OPTYPE;
    }

    public void setOPTYPE(int OPTYPE) {
        this.OPTYPE = OPTYPE;
    }

    public String getOPIMG() {
        return OPIMG;
    }

    public void setOPIMG(String OPIMG) {
        this.OPIMG = OPIMG;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}







/*
package com.skrechargenew.Util.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

*/
/**
 * Created by Shubham on 31-08-2020.
 *//*


public class Operator {

    private int OPID;
    private String OPNAME;
    private int OPTYPE;
    private String Roffercode;
    private String OPIMG;
    private String PLANCODE;
    private String Displayalue1;
    private String Displayalue2;
    private String Displayalue3;
    private String Displayalue4;
    private String Column1;
    private String operator;
    private String opcode;
    private String circle;
    private String OpIcon;

    double charge;
    boolean chargeAmtType;



    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public boolean isChargeAmtType() {
        return chargeAmtType;
    }

    public void setChargeAmtType(boolean chargeAmtType) {
        this.chargeAmtType = chargeAmtType;
    }



    public String getOpIcon() {
        return OpIcon;
    }

    public void setOpIcon(String opIcon) {
        OpIcon = opIcon;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public Operator(int OPID, String OPNAME,String Column1, int OPTYPE, String Roffercode, String OPIMG, String PLANCODE, String Displayalue1, String Displayalue2,
                    String Displayalue3, String Displayalue4) {

        this.OPID = OPID;
        this.OPNAME = OPNAME;
        this.OPTYPE = OPTYPE;
        this.Roffercode = Roffercode;
        this.OPIMG = OPIMG;
        this.Column1 = Column1;
        this.PLANCODE = PLANCODE;

        this.Displayalue1 = Displayalue1;
        this.Displayalue2 = Displayalue2;
        this.Displayalue3 = Displayalue3;
        this.Displayalue4 = Displayalue4;
    }

    public String getPLANCODE() {
        return PLANCODE;
    }

    public void setPLANCODE(String PLANCODE) {
        this.PLANCODE = PLANCODE;
    }

    public String getDisplayalue1() {
        return Displayalue1;
    }

    public void setDisplayalue1(String displayalue1) {
        Displayalue1 = displayalue1;
    }

    public String getDisplayalue2() {
        return Displayalue2;
    }

    public void setDisplayalue2(String displayalue2) {
        Displayalue2 = displayalue2;
    }

    public String getDisplayalue3() {
        return Displayalue3;
    }

    public void setDisplayalue3(String displayalue3) {
        Displayalue3 = displayalue3;
    }

    public String getDisplayalue4() {
        return Displayalue4;
    }

    public void setDisplayalue4(String displayalue4) {
        Displayalue4 = displayalue4;
    }

    public int getOPID() {
        return OPID;
    }

    public void setOPID(int OPID) {
        this.OPID = OPID;
    }

    public String getOPNAME() {
        return OPNAME;
    }

    public void setOPNAME(String OPNAME) {
        this.OPNAME = OPNAME;
    }

    public int getOPTYPE() {
        return OPTYPE;
    }

    public void setOPTYPE(int OPTYPE) {
        this.OPTYPE = OPTYPE;
    }

    public String getOPIMG() {
        return OPIMG;
    }

    public void setOPIMG(String OPIMG) {
        this.OPIMG = OPIMG;
    }

    public String getRoffercode() {
        return Roffercode;
    }

    public void setRoffercode(String roffercode) {
        Roffercode = roffercode;
    }
}
*/
