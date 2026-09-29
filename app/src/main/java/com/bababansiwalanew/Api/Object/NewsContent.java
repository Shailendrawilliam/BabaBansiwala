package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsContent {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("newsDetail")
    @Expose
    public String newsDetail;
    @SerializedName("roleId")
    @Expose
    public Integer roleId;
    @SerializedName("createDate")
    @Expose
    public String createDate;
    @SerializedName("roleName")
    @Expose
    public String roleName;
    @SerializedName("roleSlab")
    @Expose
    public String roleSlab;
    @SerializedName("role")
    @Expose
    public String role;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleSlab() {
        return roleSlab;
    }

    public String getRole() {
        return role;
    }
}
