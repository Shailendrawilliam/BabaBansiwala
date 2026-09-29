package com.bababansiwalanew.Api.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicResponse {
    @SerializedName(value = "statuscode",alternate = "Statuscode")
    @Expose
    public Integer statuscode;

    @SerializedName(value = "msg",alternate = "Msg")
    @Expose
    public String msg;
    @SerializedName("isVersionValid")
    @Expose
    public Boolean isVersionValid;
    @SerializedName("isAppValid")
    @Expose
    public Boolean isAppValid;
    @SerializedName("Status")
    @Expose
    public String Status;

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setVersionValid(Boolean versionValid) {
        isVersionValid = versionValid;
    }

    public void setAppValid(Boolean appValid) {
        isAppValid = appValid;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getVersionValid() {
        return isVersionValid;
    }

    public Boolean getAppValid() {
        return isAppValid;
    }
}
