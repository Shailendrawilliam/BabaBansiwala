package com.bababansiwalanew.Dashboard.ui;

import java.util.ArrayList;

public class UserListReportResponse {

    private String RESPONSESTATUS;
    private String message,Message;



    private ArrayList<UserListStatus> UserList;
    private ArrayList<UserRole> RoleLIst;
    private ArrayList<UserRole> User;

    public ArrayList<UserRole> getRoleLIst() {
        return RoleLIst;
    }

    public void setRoleLIst(ArrayList<UserRole> roleLIst) {
        RoleLIst = roleLIst;
    }

//    public void setUser(ArrayList<UserRole> user) {
//        User = user;
//    }

    public ArrayList<UserListStatus> getUser() {
        return UserList;
    }

    public void setUser(ArrayList<UserListStatus> user) {
        UserList = user;
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
    public ArrayList<UserListStatus> getUserList() {
        return UserList;
    }

    public void setUserList(ArrayList<UserListStatus> userList) {
        UserList = userList;
    }
// @SerializedName("RoleLIst")
//    @Expose
//    private ArrayList<UserListStatus> roleLIst = null;
//    @SerializedName("User")
//    @Expose
//    private ArrayList<UserListStatus> User = null;
}
