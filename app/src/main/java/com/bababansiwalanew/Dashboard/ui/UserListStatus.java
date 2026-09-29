package com.bababansiwalanew.Dashboard.ui;

public class UserListStatus {

    private String Column1;
    private String PermanentAddress;
    private String Email;
    private String ID;
    private String Name;
    private String outletname;
    private String utility_balance;
    private String loginId;
    private String balance_amount;
    private String RoleId;
    private String PinPassword;
    private String Status;
    private String mobileno;

    public String getPermanentAddress() {
        return PermanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        PermanentAddress = permanentAddress;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOutletname() {
        return outletname;
    }

    public void setOutletname(String outletname) {
        this.outletname = outletname;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getPinPassword() {
        return PinPassword;
    }

    public void setPinPassword(String pinPassword) {
        PinPassword = pinPassword;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }



    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getBalance_amount() {
        return balance_amount;
    }

    public void setBalance_amount(String balance_amount) {
        this.balance_amount = balance_amount;
    }

    public String getUtility_balance() {
        return utility_balance;
    }

    public void setUtility_balance(String utility_balance) {
        this.utility_balance = utility_balance;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    //private String balance_amount;

    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getMobileNo() {
        return loginId;
    }

    public void setMobileNo(String mobileNo) {
        loginId = mobileNo;
    }

    public String getBalance_Amount() {
        return balance_amount;
    }

    public void setBalance_Amount(String balance_Amount) {
        this.balance_amount = balance_Amount;
    }

    public String getUtility_Balance() {
        return utility_balance;
    }

    public void setUtility_Balance(String utility_Balance) {
        utility_balance = utility_Balance;
    }
}
//package com.skrechargenew.Dashboard.ui;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class UserListStatus {
//
//    private String Column1;
//    private String PermanentAddress;
//    private String Email;
//    private String RoleId;
//    private String PinPassword;
//    private String Status;
//    private String mobileno;
//    private String outletname;
//    private String utility_balance;
//    @SerializedName(value = "MobileNo",alternate = "loginId")
//    @Expose
//    private String loginId;
//    private String balance_amount;
//    @SerializedName(value = "Name",alternate = "Name")
//    @Expose
//    private String Name;
//    @SerializedName(value = "Role_ID",alternate = "RoleId")
//    @Expose
//    private Integer roleID;
//    @SerializedName("Balance_Amount")
//    @Expose
//    private Integer balanceAmount;
//    @SerializedName("Role_Name")
//    @Expose
//    private String roleName;
//    @SerializedName("Created_Date")
//    @Expose
//    private String createdDate;
//    @SerializedName(value = "ID",alternate = "Id")
//    @Expose
//    private String ID;
//    @SerializedName("Remark")
//    @Expose
//    private String remark;
//    @SerializedName("IdNo")
//    @Expose
//    private Integer idNo;
//    @SerializedName("LVL")
//    @Expose
//    private String lVL;
//    public Integer getRoleID() {
//        return roleID;
//    }
//
//    public void setRoleID(Integer roleID) {
//        this.roleID = roleID;
//    }
//
//    public Integer getBalanceAmount() {
//        return balanceAmount;
//    }
//
//    public void setBalanceAmount(Integer balanceAmount) {
//        this.balanceAmount = balanceAmount;
//    }
//
//    public String getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(String createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public String getlVL() {
//        return lVL;
//    }
//
//    public void setlVL(String lVL) {
//        this.lVL = lVL;
//    }
//
//
//
//
//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//
//    public Integer getIdNo() {
//        return idNo;
//    }
//
//    public void setIdNo(Integer idNo) {
//        this.idNo = idNo;
//    }
//
//    public String getLVL() {
//        return lVL;
//    }
//
//    public void setLVL(String lVL) {
//        this.lVL = lVL;
//    }
//
//    public String getPermanentAddress() {
//        return PermanentAddress;
//    }
//
//    public void setPermanentAddress(String permanentAddress) {
//        PermanentAddress = permanentAddress;
//    }
//
//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String email) {
//        Email = email;
//    }
//
//    public String getID() {
//        return ID;
//    }
//
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//
//    public String getOutletname() {
//        return outletname;
//    }
//
//    public void setOutletname(String outletname) {
//        this.outletname = outletname;
//    }
//
//    public String getRoleId() {
//        return RoleId;
//    }
//
//    public void setRoleId(String roleId) {
//        RoleId = roleId;
//    }
//
//    public String getPinPassword() {
//        return PinPassword;
//    }
//
//    public void setPinPassword(String pinPassword) {
//        PinPassword = pinPassword;
//    }
//
//    public String getStatus() {
//        return Status;
//    }
//
//    public void setStatus(String status) {
//        Status = status;
//    }
//
//
//
//    public String getLoginId() {
//        return loginId;
//    }
//
//    public void setLoginId(String loginId) {
//        this.loginId = loginId;
//    }
//
//    public String getBalance_amount() {
//        return balance_amount;
//    }
//
//    public void setBalance_amount(String balance_amount) {
//        this.balance_amount = balance_amount;
//    }
//
//    public String getUtility_balance() {
//        return utility_balance;
//    }
//
//    public void setUtility_balance(String utility_balance) {
//        this.utility_balance = utility_balance;
//    }
//
//    public String getMobileno() {
//        return mobileno;
//    }
//
//    public void setMobileno(String mobileno) {
//        this.mobileno = mobileno;
//    }
//
//    //private String balance_amount;
//
//    public String getColumn1() {
//        return Column1;
//    }
//
//    public void setColumn1(String column1) {
//        Column1 = column1;
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        this.Name = name;
//    }
//
//    public String getMobileNo() {
//        return loginId;
//    }
//
//    public void setMobileNo(String mobileNo) {
//        loginId = mobileNo;
//    }
//
//    public String getBalance_Amount() {
//        return balance_amount;
//    }
//
//    public void setBalance_Amount(String balance_Amount) {
//        this.balance_amount = balance_Amount;
//    }
//
//    public String getUtility_Balance() {
//        return utility_balance;
//    }
//
//    public void setUtility_Balance(String utility_Balance) {
//        utility_balance = utility_Balance;
//    }
//}