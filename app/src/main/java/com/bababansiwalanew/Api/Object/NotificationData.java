package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationData {
    @SerializedName("isSeen")
    @Expose
    public boolean isSeen;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("imageUrl")
    @Expose
    public String imageUrl;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("response")
    @Expose
    public String response;
    @SerializedName("entryDate")
    @Expose
    public String entryDate;
    @SerializedName("wid")
    @Expose
    public Integer wid;
    @SerializedName("loginID")
    @Expose
    public Integer loginID;
    @SerializedName("lt")
    @Expose
    public Integer lt;
    @SerializedName("file")
    @Expose
    public Object file;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public String getResponse() {
        return response;
    }

    public String getEntryDate() {
        return entryDate!=null&&!entryDate.isEmpty()?entryDate:"";
    }

    public Integer getWid() {
        return wid;
    }

    public Integer getLoginID() {
        return loginID;
    }

    public Integer getLt() {
        return lt;
    }

    public Object getFile() {
        return file;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
