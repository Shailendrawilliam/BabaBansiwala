package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChildRoles {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("ind")
    @Expose
    public int ind;

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public int getInd() {
        return ind;
    }
}
