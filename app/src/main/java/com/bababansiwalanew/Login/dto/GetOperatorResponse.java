package com.bababansiwalanew.Login.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lalit on 08-04-2017.
 */

public class GetOperatorResponse {



    private String RESPONSESTATUS;
    @SerializedName(value = "message",alternate = "Message")
    @Expose
    private String message;
    private String status;
    private int OPID;
    @SerializedName(value = "operator",alternate = "Operator")
    @Expose
    private String operator;
    private String opcode;
    private String circle;
    private ArrayList<OperatorObject> prepaidOperator;
    private ArrayList<OperatorObject> postpaidOperator;
    private ArrayList<OperatorObject> dthOperator;
    private ArrayList<OperatorObject> landlineOperator;
    private ArrayList<OperatorObject> electricityOperator;
    private ArrayList<OperatorObject> GasOperator;
    private ArrayList<SaleReportObject> SaleReport;
    private ArrayList<OperatorObject> InsuranceOperator;
    private ArrayList<OperatorObject> WaterOperator;
    private ArrayList<OperatorObject> BroadBandOperator;

    private ArrayList<OperatorObject> PaymentOperator;

    public ArrayList<OperatorObject> getPaymentOperator() {
        return PaymentOperator;
    }

    public void setPaymentOperator(ArrayList<OperatorObject> paymentOperator) {
        PaymentOperator = paymentOperator;
    }




    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOPID() {
        return OPID;
    }

    public void setOPID(int OPID) {
        this.OPID = OPID;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }



    public ArrayList<OperatorObject> getBroadBandOperator() {
        return BroadBandOperator;
    }

    public void setBroadBandOperator(ArrayList<OperatorObject> broadBandOperator) {
        BroadBandOperator = broadBandOperator;
    }

    public ArrayList<OperatorObject> getGasOperator() {
        return GasOperator;
    }

    public void setGasOperator(ArrayList<OperatorObject> gasOperator) {
        GasOperator = gasOperator;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public ArrayList<OperatorObject> getPrepaidOperator() {
        return prepaidOperator;
    }

    public void setPrepaidOperator(ArrayList<OperatorObject> prepaidOperator) {
        this.prepaidOperator = prepaidOperator;
    }
    public ArrayList<OperatorObject> getInsuranceOperator() {
        return InsuranceOperator;
    }

    public void setInsuranceOperator(ArrayList<OperatorObject> insuranceOperator) {
        InsuranceOperator = insuranceOperator;
    }

    public ArrayList<OperatorObject> getWaterOperator() {
        return WaterOperator;
    }

    public void setWaterOperator(ArrayList<OperatorObject> waterOperator) {
        WaterOperator = waterOperator;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<OperatorObject> getDthOperator() {
        return dthOperator;
    }

    public void setDthOperator(ArrayList<OperatorObject> dthOperator) {
        this.dthOperator = dthOperator;
    }

    public ArrayList<OperatorObject> getPostpaidOperator() {
        return postpaidOperator;
    }

    public void setPostpaidOperator(ArrayList<OperatorObject> postpaidOperator) {
        this.postpaidOperator = postpaidOperator;
    }

    public ArrayList<OperatorObject> getLandlineOperator() {
        return landlineOperator;
    }

    public void setLandlineOperator(ArrayList<OperatorObject> landlineOperator) {
        this.landlineOperator = landlineOperator;
    }

    public ArrayList<OperatorObject> getElectricityOperator() {
        return electricityOperator;
    }

    public void setElectricityOperator(ArrayList<OperatorObject> electricityOperator) {
        this.electricityOperator = electricityOperator;
    }

//    public ArrayList<OperatorObject> getGasOperator() {
//        return gasOperator;
//    }
//
//    public void setGasOperator(ArrayList<OperatorObject> gasOperator) {
//        this.gasOperator = gasOperator;
//    }

    public ArrayList<SaleReportObject> getSaleReport() {
        return SaleReport;
    }

    public void setSaleReport(ArrayList<SaleReportObject> saleReport) {
        SaleReport = saleReport;
    }
}
