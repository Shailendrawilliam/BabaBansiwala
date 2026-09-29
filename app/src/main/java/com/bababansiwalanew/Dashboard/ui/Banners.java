package com.bababansiwalanew.Dashboard.ui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banners {
    @SerializedName(value = "resourceUrl",alternate = "ImageUrl")
    @Expose
    public String resourceUrl;
    @SerializedName("fileName")
    @Expose
    public String fileName;
    @SerializedName("Entrydate")
    @Expose
    public String Entrydate;

    public String getEntrydate() {
        return Entrydate;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getFileName() {
        return fileName;
    }
}
