package com.bababansiwalanew.Api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.bababansiwalanew.Api.Object.Banners;
import com.bababansiwalanew.Api.Object.ChildRoles;
import com.bababansiwalanew.Api.Object.FundRequestForApproval;
import com.bababansiwalanew.Api.Object.NewsContent;
import com.bababansiwalanew.Api.Object.NotificationData;
import com.bababansiwalanew.Api.Object.PGModelForApp;
import com.bababansiwalanew.Api.Object.PaymentGatewayType;
import com.bababansiwalanew.Api.Object.RefundLog;
import com.bababansiwalanew.Api.Object.UserDaybookReport;
import com.bababansiwalanew.Api.Object.UserList;

import java.util.ArrayList;
import java.util.List;

public class AppUserListResponse {


    @SerializedName("pGModelForApp")
    @Expose
    public PGModelForApp pGModelForApp = null;

    @SerializedName("pGs")
    @Expose
    public ArrayList<PaymentGatewayType> pGs = null;


    @SerializedName("notifications")
    @Expose
    public List<NotificationData> notifications = null;
    @SerializedName("userDaybookReport")
    @Expose
    public List<UserDaybookReport> userDaybookReport = null;
    @SerializedName("refundLog")
    @Expose
    public List<RefundLog> refundLog = null;
    @SerializedName("rechargeReport")
    @Expose
    public Object rechargeReport;
    @SerializedName("dmtReport")
    @Expose
    public Object dmtReport;
    @SerializedName("ledgerReport")
    @Expose
    public Object ledgerReport;
    @SerializedName("fundDCReport")
    @Expose
    public Object fundDCReport;
    @SerializedName("fundOrderReport")
    @Expose
    public Object fundOrderReport;
    @SerializedName("slabCommissions")
    @Expose
    public Object slabCommissions;
    @SerializedName("userList")
    @Expose
    public List<UserList> userList = null;
    @SerializedName("childRoles")
    @Expose
    public List<ChildRoles> childRoles = null;
    @SerializedName("fundRequestForApproval")
    @Expose
    public List<FundRequestForApproval> fundRequestForApproval = null;
    @SerializedName("banners")
    @Expose
    public List<Banners> banners = null;

    @SerializedName("newsContent")
    @Expose
    public NewsContent newsContent = null;

    @SerializedName("statuscode")
    @Expose
    public Integer statuscode;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("isVersionValid")
    @Expose
    public Boolean isVersionValid;
    @SerializedName("isAppValid")
    @Expose
    public Boolean isAppValid;


    public PGModelForApp getpGModelForApp() {
        return pGModelForApp;
    }

    public void setpGModelForApp(PGModelForApp pGModelForApp) {
        this.pGModelForApp = pGModelForApp;
    }

    public ArrayList<PaymentGatewayType> getpGs() {
        return pGs;
    }

    public Object getRechargeReport() {
        return rechargeReport;
    }

    public Object getDmtReport() {
        return dmtReport;
    }

    public Object getLedgerReport() {
        return ledgerReport;
    }

    public Object getFundDCReport() {
        return fundDCReport;
    }

    public Object getFundOrderReport() {
        return fundOrderReport;
    }

    public Object getSlabCommissions() {
        return slabCommissions;
    }

    public List<UserList> getUserList() {
        return userList;
    }

    public List<ChildRoles> getChildRoles() {
        return childRoles;
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

    public List<FundRequestForApproval> getFundRequestForApproval() {
        return fundRequestForApproval;
    }

    public List<RefundLog> getRefundLog() {
        return refundLog;
    }

    public List<UserDaybookReport> getUserDaybookReport() {
        return userDaybookReport;
    }

    public List<Banners> getBanners() {
        return banners;
    }

    public NewsContent getNewsContent() {
        return newsContent;
    }

    public Boolean getAppValid() {
        return isAppValid;
    }

    public List<NotificationData> getNotifications() {
        return notifications;
    }
}
