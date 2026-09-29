package com.bababansiwalanew.Dashboard.ui;

public class UserRole {
    private String RESPONSESTATUS;
    private String Role_Name;
    private String Name;
    private String MobileNo;
    private Integer Role_ID;
    private Double Balance_Amount;
    private Double Balance;

    public Double getTotalTeamBusiness() {
        return TotalTeamBusiness;
    }

    public void setTotalTeamBusiness(Double totalTeamBusiness) {
        TotalTeamBusiness = totalTeamBusiness;
    }

    public Double getTotalSelfBusiness() {
        return TotalSelfBusiness;
    }

    public void setTotalSelfBusiness(Double totalSelfBusiness) {
        TotalSelfBusiness = totalSelfBusiness;
    }

    private Double TotalTeamBusiness ;
    private Double TotalSelfBusiness;
    private String Created_Date;
    private String Remark;
    private String CreateDate;
    private Integer IdNo;
    private String LVL;

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public Integer getColumn1() {
        return Column1;
    }

    public void setColumn1(Integer column1) {
        Column1 = column1;
    }

    private Integer ID,Id;
    private Integer Column1;
    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public String getRole_Name() {
        return Role_Name;
    }

    public void setRole_Name(String role_Name) {
        Role_Name = role_Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public Integer getRole_ID() {
        return Role_ID;
    }

    public void setRole_ID(Integer role_ID) {
        Role_ID = role_ID;
    }

    public Double getBalance_Amount() {
        return Balance_Amount;
    }

    public void setBalance_Amount(Double balance_Amount) {
        Balance_Amount = balance_Amount;
    }

    public String getCreated_Date() {
        return Created_Date;
    }

    public void setCreated_Date(String created_Date) {
        Created_Date = created_Date;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Integer getIdNo() {
        return IdNo;
    }

    public void setIdNo(Integer idNo) {
        IdNo = idNo;
    }

    public String getLVL() {
        return LVL;
    }

    public void setLVL(String LVL) {
        this.LVL = LVL;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }


}
