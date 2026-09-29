package com.bababansiwalanew.Util.dto;

/**
 * Created by Lalit on 15-02-2017.
 */

public class NumberList {

    private String Id;
    private String Number;
    private String Operator;
    private String Circle;
    private String OPSC;
    private String CSC;
    private String IReffOp;
    private String IReffCircle;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCircle() {
        return Circle;
    }

    public void setCircle(String circle) {
        Circle = circle;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public String getOPSC() {
        return OPSC;
    }

    public void setOPSC(String OPSC) {
        this.OPSC = OPSC;
    }

    public String getCSC() {
        return CSC;
    }

    public void setCSC(String CSC) {
        this.CSC = CSC;
    }

    public String getIReffOp() {
        return IReffOp;
    }

    public void setIReffOp(String IReffOp) {
        this.IReffOp = IReffOp;
    }

    public String getIReffCircle() {
        return IReffCircle;
    }

    public void setIReffCircle(String IReffCircle) {
        this.IReffCircle = IReffCircle;
    }
}
